package za.co.burgerfatty.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import za.co.burgerfatty.dto.ErrorResponseDto;
import za.co.burgerfatty.exception.ProductNotFound;

@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler({ProductNotFound.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponseDto> productNotFound(Exception exception) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
