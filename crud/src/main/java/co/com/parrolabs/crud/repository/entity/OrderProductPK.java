package co.com.parrolabs.crud.repository.entity;

import lombok.Data;

import jakarta.persistence.*;
import java.io.Serializable;
@Embeddable
@Data
public class OrderProductPK implements Serializable {
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "product_id")
    private Long productId;
}
