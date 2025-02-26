package za.co.burgerfatty.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import za.co.burgerfatty.dto.*;
import za.co.burgerfatty.service.OrderService;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "api/orders", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderController {
    private final OrderService orderService;
    public final static String ORDER_PLACED = "ORDER_PLACED";
    public final static String ORDER_FAILED = "ORDER_FAILED";

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(value = "/order", method = RequestMethod.OPTIONS)
    public ResponseEntity<?> handleOptions() {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/order")
    public ResponseEntity<OrderResponseDto> createOrder(@RequestBody List<ProductDto> orderProducts,
                                                        Authentication authentication) {
        String email = authentication.getName();
        log.debug("on method post order request, post products size : {}", orderProducts.size());
        log.debug("Creating order for email: {}", email);
        OrderDto orderDto = orderService.saveNewOrder(orderProducts, email);
        if(orderDto == null) {
            OrderResponseDto response = new OrderResponseDto(ORDER_PLACED,
                    HttpStatus.CREATED.name(), LocalDateTime.now());
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        OrderResponseDto response = new OrderResponseDto(ORDER_FAILED,
                HttpStatus.EXPECTATION_FAILED.name(), LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/all")
    public ResponseEntity<List<OrderDto>> getOrderByUserEmail(@RequestParam("email") String email){
        return ResponseEntity.ok().body(orderService.getUserOrders(email));
    }
}
