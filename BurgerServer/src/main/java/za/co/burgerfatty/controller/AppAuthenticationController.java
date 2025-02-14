package za.co.burgerfatty.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.burgerfatty.dto.LoginCredentialsDto;
import za.co.burgerfatty.models.BurgerUser;
import za.co.burgerfatty.service.BurgerAuthenticationService;

@RestController
@RequestMapping(path = "auth/")
public class AppAuthenticationController {
    private final BurgerAuthenticationService burgerAuthenticationService;

    public AppAuthenticationController(BurgerAuthenticationService burgerAuthenticationService) {
        this.burgerAuthenticationService = burgerAuthenticationService;
    }

    @PostMapping("sign-in")
    public ResponseEntity<BurgerUser> signin(@RequestBody LoginCredentialsDto loginCredentialsDto) {
        BurgerUser authenticationResponseDto = burgerAuthenticationService.getUserByEmail(loginCredentialsDto.getEmail());
        return ResponseEntity.ok(authenticationResponseDto);
    }
}
