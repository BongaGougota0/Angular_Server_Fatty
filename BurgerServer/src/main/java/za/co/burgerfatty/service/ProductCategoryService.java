package za.co.burgerfatty.service;

import org.springframework.stereotype.Service;
import za.co.burgerfatty.models.ProductCategory;
import za.co.burgerfatty.repositories.ProductCategoryRepo;
import java.util.List;

@Service
public class ProductCategoryService
{
    private final ProductCategoryRepo productCategoryRepo;
    public ProductCategoryService(ProductCategoryRepo productCategoryRepo){
        this.productCategoryRepo = productCategoryRepo;
    }

    public List<ProductCategory> getProductCategories(){
        return productCategoryRepo.findAll();
    }
}
