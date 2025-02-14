package za.co.burgerfatty.service;

import org.springframework.stereotype.Service;
import za.co.burgerfatty.dto.AuthenticationResponseDto;
import za.co.burgerfatty.dto.LoginCredentialsDto;
import za.co.burgerfatty.models.BurgerUser;
import za.co.burgerfatty.repositories.BurgerUserRepo;
import java.nio.file.attribute.UserPrincipalNotFoundException;

@Service
public class BurgerAuthenticationService {
    private final BurgerUserRepo burgerUserRepo;

    public BurgerAuthenticationService(BurgerUserRepo burgerUserRepo) {
        this.burgerUserRepo = burgerUserRepo;
    }

    public BurgerUser getUserbyEmail(String email) {
        try{
            return burgerUserRepo.findUserByEmail(email).orElseThrow(
                    () -> new UserPrincipalNotFoundException("")
            );
        }catch (Exception e) {
            throw new RuntimeException("");
        }
    }

}
