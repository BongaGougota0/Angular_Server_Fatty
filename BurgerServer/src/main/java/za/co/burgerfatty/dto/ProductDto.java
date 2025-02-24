package za.co.burgerfatty.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ProductDto {
    Integer productId;
    String productCategory;
    String productName;
    String productDescription;
    double productPrice;
    String productImage;
    String productUrl;
    int productCount;
}
