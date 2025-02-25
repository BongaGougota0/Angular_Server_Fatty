package za.co.burgerfatty.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.burgerfatty.models.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
