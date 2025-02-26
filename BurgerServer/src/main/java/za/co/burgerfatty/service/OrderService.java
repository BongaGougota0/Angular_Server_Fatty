package za.co.burgerfatty.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import za.co.burgerfatty.dto.OrderDto;
import za.co.burgerfatty.dto.ProductDto;
import za.co.burgerfatty.models.BurgerUser;
import za.co.burgerfatty.models.Order;
import za.co.burgerfatty.repositories.BurgerUserRepo;
import za.co.burgerfatty.repositories.OrderRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final BurgerUserRepo burgerUserRepo;

    public OrderDto saveNewOrder(List<ProductDto> products, String userEmail){
        Order o = new Order();
        o.setOrderDate(LocalDateTime.now());
        o.setOrderTotal(products.stream()
                .mapToDouble(productDto ->
                        productDto.getProductPrice() * productDto.getProductCount()).sum());
        o.setOrderItems(products.stream()
                .map(p -> p.getProductName().concat(" Qty")
                        .concat(String.valueOf(p.getProductCount())))
                .collect(Collectors.joining(", ")));
        Order placedOrder = orderRepository.save(o);
        BurgerUser user = burgerUserRepo.findUserByEmail(userEmail).get();
        placedOrder.setUser(user);
        return createOrderDto(placedOrder);
    }

    public List<OrderDto> getUserOrders(String userEmail){
        return orderRepository.findAll()
                .stream()
                .filter(order ->  order.getUser().getEmail().equalsIgnoreCase(userEmail))
                .map(this::createOrderDto)
                .collect(Collectors.toList());
    }

    private Order createOrderEntity(Order order, OrderDto orderDto){
        order.setOrderDate(LocalDateTime.now());
        order.setOrderItems(orderDto.orderItems());
        order.setOrderTotal(orderDto.orderTotal());
        order.setUser(burgerUserRepo.findUserByEmail(orderDto.userEmail()).get());
        return order;
    }

    private OrderDto createOrderDto(Order order){
        return new OrderDto(order.getOrderId(), order.getOrderTotal(),
                order.getOrderDate(), order.getOrderItems(), order.getUser().getEmail());
    }
}
