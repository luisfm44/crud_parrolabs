package co.com.parrolabs.crud.repository.entity;

import lombok.Data;

import jakarta.persistence.*;

@Entity
@Table(name = "order_product")
@Data
public class OrderProduct {

    @EmbeddedId
    private OrderProductPK id;

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "quantity", nullable = false)
    private int quantity;
}
