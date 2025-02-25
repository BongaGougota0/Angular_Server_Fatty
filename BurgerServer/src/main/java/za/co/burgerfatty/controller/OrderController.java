package za.co.burgerfatty.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.burgerfatty.dto.OrderDto;
import za.co.burgerfatty.service.OrderService;
import java.util.List;

@RestController
@RequestMapping(name = "api/orders", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/order")
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto) {
        return ResponseEntity.ok().body(orderService.createNewOrder(orderDto));
    }

    @GetMapping("")
    public ResponseEntity<List<OrderDto>> getOrderByUserEmail(@RequestParam("email") String email){
        return ResponseEntity.ok().body(orderService.getUserOrders(email));
    }
}
