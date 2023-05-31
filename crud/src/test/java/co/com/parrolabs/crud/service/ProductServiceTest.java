package co.com.parrolabs.crud.service;

import co.com.parrolabs.crud.repository.entity.Product;
import co.com.parrolabs.crud.repository.ProductRepository;
import co.com.parrolabs.crud.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetProductById() {
        // Mock data
        Long productId = 1L;
        Product product = new Product();
        product.setProductId(productId);
        product.setDescription("Product 1");

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        Optional<Product> result = productService.getProductById(productId);

        assertNotNull(result);
        assertEquals(productId, result.get().getProductId());
        assertEquals("Product 1", result.get().getDescription());

        verify(productRepository, times(1)).findById(productId);
    }

    @Test
    public void testGetAllProducts() {
        // Mock data
        List<Product> products = new ArrayList<>();
        Product product = new Product();
        product.setProductId(1L);
        product.setDescription("Product 1");
        products.add(product);
        Product product2 = new Product();
        product2.setProductId(2L);
        product2.setDescription("Product 2");
        products.add(product2);

        // Mock repository response
        when(productRepository.findAll()).thenReturn(products);

        // Call the service method
        List<Product> result = productService.getAllProducts();

        // Verify the result
        assertNotNull(result);
        assertEquals(2, result.size());

        // Verify that the repository method was called
        verify(productRepository, times(1)).findAll();
    }

    @Test
    public void testCreateProduct() {
        Product product = new Product();
        product.setDescription("Product 1");

        when(productRepository.save(product)).thenReturn(product);

        Optional<Product> result = productService.createProduct(product);

        assertNotNull(result);
        assertEquals("Product 1", result.get().getDescription());

        verify(productRepository, times(1)).save(product);
    }
}
