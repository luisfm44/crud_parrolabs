package co.com.parrolabs.crud.services;

import co.com.parrolabs.crud.exception.ProductNotFoundException;
import co.com.parrolabs.crud.repository.entity.Product;
import co.com.parrolabs.crud.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public Optional<Product> createProduct(Product product) {
        Product savedProduct = productRepository.save(product);
        return Optional.ofNullable(savedProduct);
    }

    public Optional<Product> getProductById(Long productId) {
        return Optional.ofNullable(productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found")));
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Transactional
    public boolean deleteProduct(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            productRepository.delete(product.get());
            return true;
        } else {
            return false;
        }
    }

    public Optional<Product> updateProduct(Long id, Product updatedProduct) {
        Optional<Product> existingProduct = productRepository.findById(id);

        if (existingProduct.isPresent()) {
            updatedProduct.setProductId(id);
            Product savedProduct = productRepository.save(updatedProduct);
            return Optional.of(savedProduct);
        }

        return Optional.empty();
    }
}

