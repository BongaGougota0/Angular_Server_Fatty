package za.co.burgerfatty.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name = "users")
@Entity
public class BurgerUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer userId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String password;
}
