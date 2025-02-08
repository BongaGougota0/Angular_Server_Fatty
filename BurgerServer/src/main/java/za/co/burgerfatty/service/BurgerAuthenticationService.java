package za.co.burgerfatty.service;

import org.springframework.stereotype.Service;
import za.co.burgerfatty.dto.AuthenticationResponseDto;
import za.co.burgerfatty.dto.LoginCredentialsDto;
import za.co.burgerfatty.repositories.BurgerUserRepo;

@Service
public class BurgerAuthenticationService {
    private final BurgerUserRepo burgerUserRepo;

    public BurgerAuthenticationService(BurgerUserRepo burgerUserRepo) {
        this.burgerUserRepo = burgerUserRepo;
    }

    public AuthenticationResponseDto signIn(LoginCredentialsDto loginCredentials) {
        return new AuthenticationResponseDto();
    }
}
