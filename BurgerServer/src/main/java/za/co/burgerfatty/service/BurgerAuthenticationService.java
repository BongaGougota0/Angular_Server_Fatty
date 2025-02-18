package za.co.burgerfatty.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import za.co.burgerfatty.dto.LoginCredentialsDto;
import za.co.burgerfatty.models.BurgerUser;

@Service
public class BurgerAuthenticationService {
    public static String USER_NOT_FOUND = "User with email %s not found";
    public final BurgerUsersService burgerUsersService;
    private final PasswordEncoder passwordEncoder;

    public BurgerAuthenticationService(BurgerUsersService burgerUsersService, PasswordEncoder passwordEncoder) {
        this.burgerUsersService = burgerUsersService;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean authenticate(LoginCredentialsDto loginCredentials) {
        BurgerUser user = burgerUsersService.getUserByEmail(loginCredentials.getEmail());
        if (user != null && passwordEncoder.matches(loginCredentials.getPassword(), user.getPassword())) {
            return true;
        }
        return false;
    }

}
