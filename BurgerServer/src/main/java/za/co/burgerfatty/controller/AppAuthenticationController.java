package za.co.burgerfatty.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.burgerfatty.dto.AuthenticationResponseDto;
import za.co.burgerfatty.dto.LoginCredentialsDto;
import za.co.burgerfatty.service.BurgerAuthenticationService;

@RestController
@RequestMapping(path = "auth/")
public class BurgerAuthenticationController {
    private final BurgerAuthenticationService burgerAuthenticationService;

    public BurgerAuthenticationController(BurgerAuthenticationService burgerAuthenticationService) {
        this.burgerAuthenticationService = burgerAuthenticationService;
    }

    @PostMapping("sign-in")
    public ResponseEntity<AuthenticationResponseDto> signin(@RequestBody LoginCredentialsDto loginCredentialsDto) {
        AuthenticationResponseDto authenticationResponseDto = burgerAuthenticationService.signIn(loginCredentialsDto);
        return ResponseEntity.ok(authenticationResponseDto);
    }
}
