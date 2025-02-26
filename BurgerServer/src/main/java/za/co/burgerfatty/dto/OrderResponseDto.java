package za.co.burgerfatty.dto;

import java.time.LocalDateTime;

public record OrderResponseDto(String message, String statusCode, LocalDateTime time) {
}
