package za.co.burgerfatty.service;

import org.springframework.stereotype.Service;
import za.co.burgerfatty.dto.CarouselItemDto;
import za.co.burgerfatty.dto.ProductDto;
import za.co.burgerfatty.exception.CategoryNotFound;
import za.co.burgerfatty.exception.ProductNotFound;
import za.co.burgerfatty.models.Product;
import za.co.burgerfatty.models.ProductCategory;
import za.co.burgerfatty.repositories.ProductRepo;
import za.co.burgerfatty.util.Util;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductService {
    public static String PRODUCT_NOT_FOUND = "Product with id %d not found";
    public static String INVALID_PRODUCT_CATEGORY = "Invalid product category name %s";
    private final ProductRepo productRepo;
    private final ProductCategoryService productCategoryService;

    public ProductService(ProductRepo productRepo, ProductCategoryService productCategoryService) {
        this.productRepo = productRepo;
        this.productCategoryService = productCategoryService;
    }

    public ProductDto newProduct(ProductDto productDto) {
        ProductCategory category = productCategoryService.getProductCategories()
                .stream().filter(productCategory ->
                        productCategory.getCategoryName().equalsIgnoreCase(productDto.getProductCategory()))
                .findFirst()
                .orElseThrow(() -> new CategoryNotFound("Product category not found"));
        Product product = new Product();
        product.setName(productDto.getProductName());
        product.setCategory(category);
        product.setSku("");
        product.setUnitPrice(productDto.getProductPrice());
        product.setDescription(productDto.getProductDescription());
        product.setImageUrl(productDto.getProductUrl());
        product.setDateCreated(LocalDateTime.now());
        product.setLastUpdated(LocalDateTime.now());
        productRepo.save(product);
        return createProduct(product);
    }

    public ProductDto getProductById(Integer productId) {
                Product product = productRepo.findById(productId)
                        .orElseThrow(() -> new ProductNotFound(String.format(PRODUCT_NOT_FOUND, productId)));
        return createProduct(product);
    }

    public List<ProductDto> getAllProducts() {
        return productRepo.findAll().stream().map(this::createProduct)
                .collect(Collectors.toList());
    }

    public List<ProductDto> getProductsByCategory(String category) {
        if(!isCategoryValid(category)){
            throw new CategoryNotFound(category);
        }
        return productRepo.findProductsByCategoryCategoryNameEqualsIgnoreCase(category)
                .stream()
                .map(this::createProduct)
                .collect(Collectors.toList());
    }

    public ProductDto updateProduct(ProductDto productDto) {
        Product product = productRepo.findById(productDto.getProductId())
                .map(existingProduct -> updateProduct(existingProduct, productDto))
                .orElseThrow(() -> new ProductNotFound(String.format(PRODUCT_NOT_FOUND, productDto.getProductId())));
        return createProduct(product);
    }

    public ProductDto deleteProductById(Integer productId) {
        Product product = productRepo.findById(productId).orElseThrow(
                () -> new ProductNotFound(String.format(PRODUCT_NOT_FOUND, productId)));
        productRepo.deleteById(productId);
        return createProduct(product);
    }

    public Map<String, List<CarouselItemDto>> getUITemplateData(){
        Map<String, List<CarouselItemDto>> uiData = new HashMap<>();
        uiData.put("section_one_data", productRepo.findAll().stream()
                .map(Util::createUIItem).limit(2).collect(Collectors.toList()));

        uiData.put("carousel_items", productRepo.findAll().stream()
                .map(Util::createUIItem).limit(3).collect(Collectors.toList()));
        return uiData;
    }

    public ProductDto createProduct(Product product) {
        return new ProductDto(product.getProductId(), product.getCategory().getCategoryName(),
                product.getName(), product.getDescription(), product.getUnitPrice(),
                product.getImageUrl(), product.getImageUrl());
    }

    private Product updateProduct(Product existingProduct, ProductDto productDto) {
        existingProduct.setName(productDto.getProductName());
        existingProduct.setUnitPrice(productDto.getProductPrice());
        existingProduct.setDescription(productDto.getProductDescription());
        existingProduct.setImageUrl(productDto.getProductUrl());
        existingProduct.setLastUpdated(LocalDateTime.now());
        return productRepo.save(existingProduct);
    }

    private boolean isCategoryValid(String category) {
        if(productCategoryService.getProductCategories() != null){
            productCategoryService.getProductCategories().stream()
                    .filter(productCategory -> productCategory.getCategoryName().equalsIgnoreCase(category))
                    .findFirst()
                    .orElseThrow(() -> new CategoryNotFound(String.format(INVALID_PRODUCT_CATEGORY, category)));
            return true;
        }
        return false;
    }
}
