package co.com.parrolabs.crud.controller.product;

import co.com.parrolabs.crud.controller.dto.ProductResponse;
import co.com.parrolabs.crud.facade.ProductFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductFacade productFacade;

    @Autowired
    public ProductController(ProductFacade productFacade) {
        this.productFacade = productFacade;
    }

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductResponse productResponse) {
        return productFacade.createProduct(productResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id) {
        return productFacade.getProductById(id);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        return productFacade.getAllProducts();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long id, @RequestBody ProductResponse productDTO) {
       return productFacade.updateProduct(id,productDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductResponse> deleteProduct(@PathVariable Long id) {
        return productFacade.deleteProduct(id);
    }
}
