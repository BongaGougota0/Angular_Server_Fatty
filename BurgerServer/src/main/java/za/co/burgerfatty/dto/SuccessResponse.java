package za.co.burgerfatty.dto;

import java.time.LocalDateTime;

public record SuccessResponse(String message, LocalDateTime dateTime) {
}
