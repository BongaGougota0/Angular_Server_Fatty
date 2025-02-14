package za.co.burgerfatty.dto;

import lombok.Data;

@Data
public class LoginCredentialsDto {
    String username;
    String email;
    String password;
}
