package co.com.parrolabs.crud.controller.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class OrderProductResponse implements Serializable {
    private Long productId;
    private String description;
    private BigDecimal price;
    private int quantity;
}
