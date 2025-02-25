package za.co.burgerfatty.dto;

import java.time.LocalDateTime;

public record OrderDto(long orderId, double orderTotal, LocalDateTime orderDate, String orderItems, String userEmail) {
}
