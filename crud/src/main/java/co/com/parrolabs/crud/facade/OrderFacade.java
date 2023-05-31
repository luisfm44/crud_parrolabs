package co.com.parrolabs.crud.facade;

import co.com.parrolabs.crud.controller.dto.OrderResponse;
import co.com.parrolabs.crud.repository.entity.Order;
import co.com.parrolabs.crud.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class OrderFacade {
    private final OrderService orderService;
    @Autowired
    public OrderFacade(OrderService orderService) {
        this.orderService = orderService;
    }

    public ResponseEntity<OrderResponse> getOrderById(String id) {
        Optional<Order> order = orderService.getOrderById(id);
        return order.map(c -> ResponseEntity.ok(OrderResponse.fromEntity(c))).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<OrderResponse> createOrder(OrderResponse orderResponse) {
        Optional<OrderResponse> addedOrder = orderService.addOrder(orderResponse.toEntity());
        return addedOrder.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());

    }

    public ResponseEntity<Void> deleteOrder(String orderNumber) {
        boolean deleted = orderService.deleteOrder(orderNumber);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
