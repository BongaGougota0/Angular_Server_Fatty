package za.co.burgerfatty.service;

import org.springframework.stereotype.Service;
import za.co.burgerfatty.exception.UserNotFound;
import za.co.burgerfatty.models.BurgerUser;
import za.co.burgerfatty.repositories.BurgerUserRepo;

@Service
public class BurgerAuthenticationService {
    public static String USER_NOT_FOUND = "User with email %s not found";
    private final BurgerUserRepo burgerUserRepo;

    public BurgerAuthenticationService(BurgerUserRepo burgerUserRepo) {
        this.burgerUserRepo = burgerUserRepo;
    }

    public BurgerUser getUserByEmail(String email) {
            return burgerUserRepo.findUserByEmail(email).orElseThrow(
                    () -> new UserNotFound(String.format(USER_NOT_FOUND, email)));
    }

}
