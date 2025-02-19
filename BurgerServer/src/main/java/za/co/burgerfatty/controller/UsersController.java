package za.co.burgerfatty.controller;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping("register")
    public ResponseEntity<SuccessResponse> registerNewUser(@RequestBody BurgerUser newUser) {
        if(burgerUsersService.createNewUser(newUser)){
            return new ResponseEntity(new SuccessResponse("New User created", LocalDateTime.now()),
                    HttpStatusCode.valueOf(200));
        }else{
            return new ResponseEntity(new SuccessResponse("User already exists", LocalDateTime.now()),
                    HttpStatusCode.valueOf(403));
        }
    }
}
