package za.co.burgerfatty.models;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Table
@Entity(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "order_total")
    private double orderTotal;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @Column(name ="order_items")
    private String orderItems;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_user_id", referencedColumnName = "user_id")
    private BurgerUser user;
}
