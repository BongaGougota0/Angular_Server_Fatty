package za.co.burgerfatty.service;

import org.springframework.stereotype.Service;
import za.co.burgerfatty.dto.ProductDto;
import za.co.burgerfatty.models.Product;
import za.co.burgerfatty.repositories.ProductRepo;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepo productRepo;

    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public ProductDto getProductById(Integer productId) {
        List<Product> product = productRepo.findProductByProductId(productId);
        return new ProductDto();
    }

    public List<ProductDto> getAllProducts() {
        return productRepo.findAll().stream().map(product -> new ProductDto())
                .collect(Collectors.toList());
    }
}
