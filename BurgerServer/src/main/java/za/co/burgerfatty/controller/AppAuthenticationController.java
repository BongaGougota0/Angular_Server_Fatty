package za.co.burgerfatty.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.burgerfatty.dto.AuthenticationResponseDto;
import za.co.burgerfatty.dto.LoginCredentialsDto;
import za.co.burgerfatty.exception.UserNotFound;
import za.co.burgerfatty.security.JwtService;
import za.co.burgerfatty.service.BurgerAuthenticationService;

@RestController
@RequestMapping(path = "api/")
public class AppAuthenticationController {
    private final BurgerAuthenticationService burgerAuthenticationService;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    public AppAuthenticationController(BurgerAuthenticationService burgerAuthenticationService,
                                       JwtService jwtService, UserDetailsService userDetailsService) {
        this.burgerAuthenticationService = burgerAuthenticationService;
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("authenticate")
    public ResponseEntity<AuthenticationResponseDto> authenticateUser(@RequestBody LoginCredentialsDto loginCredentialsDto) {
        if(burgerAuthenticationService.authenticate(loginCredentialsDto)){
            String token = jwtService.generateToken(userDetailsService.loadUserByUsername(loginCredentialsDto.email()));
            String username = jwtService.extractUsername(token);
            String role = burgerAuthenticationService.burgerUsersService.getUserByEmail(username).getRole();
            return ResponseEntity.ok(new AuthenticationResponseDto(username,token, role));
        }
        throw new UserNotFound("Invalid credentials / User not found");
    }
}
