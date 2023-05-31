package co.com.parrolabs.crud.service;

import co.com.parrolabs.crud.controller.dto.OrderResponse;
import co.com.parrolabs.crud.repository.OrderRepository;
import co.com.parrolabs.crud.repository.entity.Order;
import co.com.parrolabs.crud.services.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class OrderServiceTest {
    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetOrderById() {
        Long orderId = 1L;
        Order order = new Order();
        order.setId(orderId);
        order.setOrderNumber("ORD-123");

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));

        Optional<Order> result = orderService.getOrderById(order.getOrderNumber());

        assertNotNull(result);
        assertEquals(orderId, result.get().getId());
        assertEquals("ORD-123", result.get().getOrderNumber());

        verify(orderRepository, times(1)).findById(orderId);
    }

    @Test
    public void testCreateOrder() {
        // Mock data
        Order order = new Order();
        order.setOrderNumber("ORD-123");

        when(orderRepository.save(order)).thenReturn(order);

        Optional<OrderResponse> result = orderService.addOrder(order);

        assertNotNull(result);
        assertEquals("ORD-123", result.get().getOrderNumber());

        verify(orderRepository, times(1)).save(order);
    }
}
