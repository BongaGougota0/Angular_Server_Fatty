package za.co.burgerfatty.service;

import org.springframework.stereotype.Service;
import za.co.burgerfatty.dto.CarouselItemDto;
import za.co.burgerfatty.dto.ProductDto;
import za.co.burgerfatty.exception.ProductNotFound;
import za.co.burgerfatty.models.Product;
import za.co.burgerfatty.repositories.ProductRepo;
import za.co.burgerfatty.util.Util;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepo productRepo;

    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public ProductDto newProduct(ProductDto productDto) {
        Product product = new Product();
        product.setName(product.getName());
        product.setCategory(product.getCategory());
        product.setSku(product.getSku());
        product.setUnitPrice(product.getUnitPrice());
        product.setDescription(product.getDescription());
        product.setDateCreated(LocalDateTime.now());
        product.setLastUpdated(LocalDateTime.now());
        productRepo.save(product);
        return createProduct(product);
    }

    public ProductDto getProductById(Integer productId) {
        try{
            Product product = productRepo.findById(productId).orElseThrow(ProductNotFound::new);
            return createProduct(product);
        } catch (RuntimeException | ProductNotFound e) {
            throw new RuntimeException(e);
        }
    }

    public List<ProductDto> getAllProducts() {
        return productRepo.findAll().stream().map(this::createProduct)
                .collect(Collectors.toList());
    }

    public ProductDto updateProduct(ProductDto productDto) {
        try{
            Product product = productRepo.findById(productDto.getProductId())
                    .orElseThrow(ProductNotFound::new);
            product.setName(product.getName());
            product.setUnitPrice(productDto.getProductPrice());
            product.setDescription(productDto.getProductDescription());
            product.setLastUpdated(LocalDateTime.now());
            productRepo.save(product);
            return createProduct(product);
        } catch (ProductNotFound productNotFound) {
            throw new RuntimeException(productNotFound.getMessage());
        }
    }

    public ProductDto deleteProductById(Integer productId) {
        try {
            Product product = productRepo.findById(productId).orElseThrow(ProductNotFound::new);
            productRepo.deleteById(productId);
            return createProduct(product);
        } catch (RuntimeException | ProductNotFound e) {
                throw new RuntimeException(e);
        }
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
}
