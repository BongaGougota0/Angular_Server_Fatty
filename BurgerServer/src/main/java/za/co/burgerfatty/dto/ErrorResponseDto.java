package za.co.burgerfatty.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ErrorResponseDto {
    String message;
    String statusCode;
    LocalDateTime timestamp;
}
