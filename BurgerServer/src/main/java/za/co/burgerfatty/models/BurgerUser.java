package za.co.burgerfatty.models;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Set;

@Data
@Table(name = "users")
@Entity
public class BurgerUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private String phone;
    @Column(name = "role")
    private String role = "USER";
    @Column(name = "user_password")
    private String password;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = Address.class)
    @JoinColumn(name = "fk_address_id",  referencedColumnName = "address_id")
    private Address address;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, targetEntity = Order.class)
    private Set<Order> orders;

}
