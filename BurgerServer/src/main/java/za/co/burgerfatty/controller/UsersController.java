package za.co.burgerfatty.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.burgerfatty.dto.SuccessResponse;
import za.co.burgerfatty.models.BurgerUser;
import za.co.burgerfatty.service.BurgerUsersService;

import java.time.LocalDateTime;

@RestController
@RequestMapping(path = "api/users/", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsersController {

    private final BurgerUsersService burgerUsersService;

    public UsersController(BurgerUsersService burgerUsersService) {
        this.burgerUsersService = burgerUsersService;
    }

    public ResponseEntity<SuccessResponse> registerNewUser(@RequestBody BurgerUser newUser) {
        burgerUsersService.createNewUser(newUser);
        return ResponseEntity.ok(new SuccessResponse("New User created", LocalDateTime.now()));
    }
}
