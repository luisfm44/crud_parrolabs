package co.com.parrolabs.crud.controller.dto;

import co.com.parrolabs.crud.repository.entity.Product;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Data
public class ProductResponse implements Serializable {

    private Long id;
    private String description;
    private BigDecimal price;
    private BigDecimal weight;

    public Product toEntity() {
        Product product = new Product();
        product.setProductId(this.id);
        product.setDescription(this.description);
        product.setPrice(this.price);
        product.setWeight(this.weight);
        return product;
    }

    public static ProductResponse fromEntity(Product product) {
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(product.getProductId());
        productResponse.setDescription(product.getDescription());
        productResponse.setPrice(product.getPrice());
        productResponse.setWeight(product.getWeight());
        return productResponse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductResponse that = (ProductResponse) o;
        return Objects.equals(id, that.id) && Objects.equals(description, that.description) && Objects.equals(price, that.price) && Objects.equals(weight, that.weight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, price, weight);
    }
}
