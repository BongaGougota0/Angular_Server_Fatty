package za.co.burgerfatty.util;

import za.co.burgerfatty.dto.CarouselItemDto;
import za.co.burgerfatty.models.Product;

public class Util {
    public static CarouselItemDto createUIItem(Product product){
        CarouselItemDto c = new CarouselItemDto();
        c.setProductName(product.getName());
        c.setProductCategory(product.getCategory().getCategoryName());
        c.setProductDescription(product.getDescription());
        c.setProductImage(product.getImageUrl());
        return c;
    }
}
