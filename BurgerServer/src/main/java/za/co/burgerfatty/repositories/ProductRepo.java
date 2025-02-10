package za.co.burgerfatty.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.burgerfatty.models.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {
}
