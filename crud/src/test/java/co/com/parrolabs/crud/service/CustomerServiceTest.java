package co.com.parrolabs.crud.service;

import co.com.parrolabs.crud.repository.entity.Customer;
import co.com.parrolabs.crud.repository.CustomerRepository;
import co.com.parrolabs.crud.services.CustomerService;
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

public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetCustomerById() {
        Long customerId = 1L;
        Customer customer = new Customer();
        customer.setCustomerId(customerId);
        customer.setName("test");
        customer.setEmail("test@test.com");

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));

        Optional<Customer> result = customerService.getCustomerById(customerId);

        assertNotNull(result);
        assertEquals(customerId, result.get().getCustomerId());
        assertEquals("test", result.get().getName());
        assertEquals("test@test.com", result.get().getEmail());

        verify(customerRepository, times(1)).findById(customerId);
    }

    @Test
    public void testGetAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        Customer customer = new Customer();
        customer.setCustomerId(1L);
        customer.setName("test");
        customer.setEmail("test@test.com");
        Customer customer2 = new Customer();
        customer2.setCustomerId(2L);
        customer2.setName("test2");
        customer2.setEmail("test2@test.com");
        customers.add(customer2);
        customers.add(customer);

        when(customerRepository.findAll()).thenReturn(customers);

        List<Customer> result = customerService.getAllCustomers();

        assertNotNull(result);
        assertEquals(2, result.size());

        verify(customerRepository, times(1)).findAll();
    }

    @Test
    public void testCreateCustomer() {
        Customer customer = new Customer();
        customer.setName("test");
        customer.setEmail("test@test.com");

        when(customerRepository.save(customer)).thenReturn(customer);

        Optional<Customer> result = customerService.createCustomer(customer);

        assertNotNull(result);
        assertEquals("test", result.get().getName());
        assertEquals("test@test.com", result.get().getEmail());

        verify(customerRepository, times(1)).save(customer);
    }
}
