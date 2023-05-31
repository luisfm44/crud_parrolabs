package co.com.parrolabs.crud.services;

import co.com.parrolabs.crud.controller.dto.OrderResponse;
import co.com.parrolabs.crud.repository.CustomerRepository;
import co.com.parrolabs.crud.repository.OrderRepository;
import co.com.parrolabs.crud.repository.ProductRepository;
import co.com.parrolabs.crud.repository.ShippingAddressRepository;
import co.com.parrolabs.crud.repository.entity.Customer;
import co.com.parrolabs.crud.repository.entity.Order;
import co.com.parrolabs.crud.repository.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ShippingAddressRepository shippingAddressRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    public Optional<OrderResponse> addOrder(Order order) {
        Optional<Customer> customerOptional = customerRepository.findById(order.getCustomer().getCustomerId());
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            order.setCustomer(customer);
            Order orderUpdated = setPriceInEveryProductFromOrder(order);
            orderUpdated.setTotalOrderValue(getTotalValue(orderUpdated));
            Order savedOrder = orderRepository.save(orderUpdated);
            return Optional.of(OrderResponse.fromEntity(savedOrder));
        }
        return Optional.empty();
    }

    private Order setPriceInEveryProductFromOrder(Order order) {
        Order orderUpdated = order;
        orderUpdated.getProductsQuantities().entrySet().forEach(entry -> {
            Product product = entry.getKey();
            productRepository.findById(product.getProductId()).ifPresent(p -> {
                product.setPrice(p.getPrice());
            });
        });

        return orderUpdated;
    }

    public boolean deleteOrder(String orderNumber) {
        Optional<Order> orderOptional = Optional.ofNullable(orderRepository.findByOrderNumber(orderNumber));
        orderOptional.ifPresent(order -> orderRepository.deleteById(order.getId()));
        return orderOptional.isPresent();
    }
    public Optional<Order> getOrderById(String orderNumber) {
        return Optional.ofNullable(orderRepository.findByOrderNumber(orderNumber));
    }

    /**
     * Order’s total value is a sum of the order’s product/s prices
     * @param order
     * @return
     */
    private BigDecimal getTotalValue(Order order) {
        BigDecimal totalValue = BigDecimal.ZERO;
        for (Map.Entry<Product, Integer> entry : order.getProductsQuantities().entrySet()) {
            Product product = entry.getKey();
            Integer quantity = entry.getValue();
            BigDecimal productPrice = product.getPrice();
            BigDecimal productTotalValue = productPrice.multiply(BigDecimal.valueOf(quantity));
            totalValue = totalValue.add(productTotalValue);
        }
        return totalValue;
    }
}
