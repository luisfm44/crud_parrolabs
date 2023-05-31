package co.com.parrolabs.crud.facade;

import co.com.parrolabs.crud.controller.dto.ProductResponse;
import co.com.parrolabs.crud.repository.entity.Product;
import co.com.parrolabs.crud.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ProductFacade {

    private final ProductService productService;
    @Autowired
    public ProductFacade(ProductService productService) {
        this.productService = productService;
    }

    public ResponseEntity<ProductResponse> createProduct(ProductResponse productResponse) {
        Optional<Product> createdProduct = productService.createProduct(productResponse.toEntity());
        return createdProduct.map(p -> ResponseEntity.ok(productResponse.fromEntity(p)))
                .orElse(ResponseEntity.noContent().build());
    }

    public ResponseEntity<ProductResponse> getProductById(Long id) {
        Optional<Product> product = productService.getProductById(id);
        return product.map(p -> ResponseEntity.ok(ProductResponse.fromEntity(p)))
                .orElse(ResponseEntity.noContent().build());
    }

    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        List<ProductResponse> customerDTOs = products.stream()
                .map(ProductResponse::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(customerDTOs);
    }

    public ResponseEntity<ProductResponse> updateProduct(Long id, ProductResponse products) {
        Optional<Product> updatedProduct = productService.updateProduct(id, products.toEntity());

        return updatedProduct.map(p -> ResponseEntity.ok(ProductResponse.fromEntity(p)))
                .orElse(ResponseEntity.noContent().build());
    }

    public ResponseEntity<ProductResponse> deleteProduct(Long id) {
        if (productService.deleteProduct(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
