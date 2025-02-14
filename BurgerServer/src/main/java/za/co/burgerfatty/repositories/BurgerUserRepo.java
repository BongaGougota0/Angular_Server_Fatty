package za.co.burgerfatty.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.burgerfatty.models.BurgerUser;

import java.util.Optional;

public interface BurgerUserRepo extends JpaRepository<BurgerUser, Integer> {
    BurgerUser findBurgerUserByEmail(String email);

    Optional<BurgerUser> findUserByEmail(String email);
}
