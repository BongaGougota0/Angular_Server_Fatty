package za.co.burgerfatty.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.burgerfatty.models.BurgerUser;

public interface BurgerUserRepo extends JpaRepository<BurgerUser, Integer> {
}
