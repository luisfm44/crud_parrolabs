package co.com.parrolabs.crud.controller.dto;

import co.com.parrolabs.crud.repository.entity.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Data
public class OrderResponse implements Serializable {
    private Long id;
    private String orderNumber;
    private LocalDate date;
    private Long customerId;
    private Long shippingAddressId;
    private PaymentType paymentType;
    private Map<Long, Integer> productsQuantities;
    private BigDecimal totalOrderValue;


    public Order toEntity() {
        Order order = new Order();
        order.setId(this.id);
        order.setOrderNumber(orderNumber);
        order.setDate(date);

        Customer customer = new Customer();
        customer.setCustomerId(customerId);
        order.setCustomer(customer);

        ShippingAddress shippingAddress = new ShippingAddress();
        shippingAddress.setShippingAddressId(shippingAddressId);
        order.setShippingAddress(shippingAddress);

        order.setPaymentType(paymentType);
        order.setTotalOrderValue(totalOrderValue);

        Map<Product, Integer> entityProductsQuantities = new HashMap<>();
        for (Map.Entry<Long, Integer> entry : productsQuantities.entrySet()) {
            Long key = entry.getKey();
            Product product = new Product();
            product.setProductId(key);
            entityProductsQuantities.put(product, entry.getValue());
        }
        order.setProductsQuantities(entityProductsQuantities);

        return order;
    }

    public static OrderResponse fromEntity(Order order) {
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setId(order.getId());
        orderResponse.setOrderNumber(order.getOrderNumber());
        orderResponse.setDate(order.getDate());

        Optional<Customer> customer = Optional.ofNullable(order.getCustomer());
        customer.ifPresent(value -> orderResponse.setCustomerId(value.getCustomerId()));

        Optional<ShippingAddress> shippingAddress = Optional.ofNullable(order.getShippingAddress());
        shippingAddress.ifPresent(value -> orderResponse.setShippingAddressId(value.getShippingAddressId()));

        orderResponse.setPaymentType(order.getPaymentType());
        orderResponse.setTotalOrderValue(order.getTotalOrderValue());

        Map<Long, Integer> dtoProductsQuantities = new HashMap<>();
        for (Map.Entry<Product, Integer> entry : order.getProductsQuantities().entrySet()) {
            Product product = entry.getKey();
            ProductResponse productDTO = ProductResponse.fromEntity(product);
            dtoProductsQuantities.put(productDTO.getId(), entry.getValue());
        }
        orderResponse.setProductsQuantities(dtoProductsQuantities);

        return orderResponse;
    }
}
