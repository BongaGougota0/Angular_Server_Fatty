package za.co.burgerfatty.configuration;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import za.co.burgerfatty.models.BurgerUser;
import za.co.burgerfatty.models.Product;
import za.co.burgerfatty.models.ProductCategory;
import za.co.burgerfatty.repositories.BurgerUserRepo;
import za.co.burgerfatty.repositories.ProductCategoryRepo;
import za.co.burgerfatty.repositories.ProductRepo;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class DemoData {
    private final BurgerUserRepo usersService;
    private final ProductRepo productService;
    private final ProductCategoryRepo productCategory;
    private final BurgerUserRepo burgerUserRepo;
    Random randomNumGenerator = new Random(50L);
    @Autowired
    PasswordEncoder passwordEncoder;
    public static final String[] FOOD_CATEGORIES = new String[]{"COFFEE", "DRINKS", "SNACKS", "BURGER", "CONDIMENTS", "PIZZA"};
    public static final String[] PRODUCT_NAMES = new String[]{
            "Espresso Shot", "Cappuccino", "Latte", "Americano", "Mocha",
            "Cheese Fries", "Onion Rings", "Mozzarella Sticks", "Chicken Wings", "Nachos",
            "Classic Beef Burger", "Cheese Burger", "Bacon Deluxe", "Veggie Burger", "Spicy Chicken Burger",
            "Tomato Ketchup", "Mayo", "BBQ Sauce", "Mustard", "Hot Sauce",
            "Margherita", "Pepperoni", "Hawaiian", "Vegetarian", "Meat Feast"
    };

    public static final String[] PRODUCT_DESCRIPTIONS = new String[]{
            "Rich single shot of espresso", "Espresso with steamed milk and foam", "Espresso with extra steamed milk", "Espresso with hot water", "Espresso with chocolate and milk",
            "Crispy fries topped with melted cheese", "Deep-fried onion rings with dipping sauce", "Breaded and fried mozzarella with marinara sauce", "Spicy chicken wings with blue cheese dip", "Tortilla chips with cheese, salsa, and guacamole",
            "100% beef patty with lettuce, tomato, and onion", "Beef patty with melted cheese, lettuce, and tomato", "Beef patty with bacon, cheese, and special sauce", "Plant-based patty with fresh vegetables", "Fried chicken breast with spicy mayo and lettuce",
            "Classic tomato ketchup", "Creamy mayonnaise", "Sweet and smoky BBQ sauce", "Tangy yellow mustard", "Spicy hot sauce",
            "Classic pizza with tomato sauce, mozzarella and basil", "Pepperoni and cheese on tomato base", "Ham, pineapple and cheese pizza", "Mixed vegetables on tomato base", "Multiple meat toppings on tomato base"
    };

    public static final String[] IMAGE_PLACEHOLDERS = new String[]{
            "https://picsum.photos/300/200?random=1",
            "https://picsum.photos/300/200?random=2",
            "https://picsum.photos/300/200?random=3",
            "https://picsum.photos/300/200?random=4",
            "https://picsum.photos/300/200?random=5",
            "https://picsum.photos/300/200?random=6",
            "https://picsum.photos/300/200?random=7",
            "https://picsum.photos/300/200?random=8",
            "https://picsum.photos/300/200?random=9",
            "https://picsum.photos/300/200?random=10"
    };

    public DemoData(BurgerUserRepo burgerUserService, ProductRepo productService,
                    BurgerUserRepo burgerUserRepo, ProductCategoryRepo productCategory) {
        this.usersService = burgerUserService;
        this.productService = productService;
        this.burgerUserRepo = burgerUserRepo;
        this.productCategory = productCategory;
    }

    @PostConstruct
    public void addDemoData(){
        addProductCategories();
        addDemoUsers();
        addProducts();
    }

    private void addProductCategories(){
        for(String cateName : FOOD_CATEGORIES){
            ProductCategory category = new ProductCategory();
            category.setCategoryName(cateName);
            productCategory.save(category);
        }
    }

    private void addProducts(){
        ArrayList<Product> demoProducts = new ArrayList<>();
        int productsCount = randomNumGenerator.nextInt(20) + 1;
        List<ProductCategory> categories = productCategory.findAll();
        for(int i=0; i<productsCount; i++){
            Product product = new Product();
            int categoryIndex = randomNumGenerator.nextInt(categories.size());
            product.setCategory(categories.get(categoryIndex));
            int imageIndex = randomNumGenerator.nextInt(IMAGE_PLACEHOLDERS.length);
            product.setImageUrl(IMAGE_PLACEHOLDERS[imageIndex]);
            product.setDateCreated(LocalDateTime.now());
            int productIndex = randomNumGenerator.nextInt(PRODUCT_NAMES.length);
            product.setName(PRODUCT_NAMES[productIndex]);
            product.setDescription(PRODUCT_DESCRIPTIONS[productIndex]);
            double price = 5.0 + (randomNumGenerator.nextDouble() * 15.0);
            product.setUnitPrice(Math.round(price * 100.0) / 100.0);
            int stock = randomNumGenerator.nextInt(100) + 5;
            product.setUnitsInStock(stock);
            demoProducts.add(product);
        }
        productService.saveAll(demoProducts);
    }

    private void addDemoUsers(){
        BurgerUser user = new BurgerUser();
        user.setFirstName("Phumi");
        user.setLastName("Thabethe");
        user.setRole("USER");
        user.setEmail("phumi.thabethe@gmail.com");
        user.setPassword(passwordEncoder.encode("user1"));

        BurgerUser user2 = new BurgerUser();
        user2.setFirstName("Mpumi");
        user2.setLastName("Gongotha");
        user2.setRole("USER");
        user2.setEmail("mpumi.gongotha@gmail.com");
        user2.setPassword(passwordEncoder.encode("user2"));

        burgerUserRepo.save(user);
        burgerUserRepo.save(user2);
    }
}