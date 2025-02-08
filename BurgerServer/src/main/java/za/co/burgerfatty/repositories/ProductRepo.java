package za.co.burgerfatty.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.burgerfatty.models.Product;


public interface ProductRepo extends JpaRepository<Product, Integer> {
}
