package co.com.parrolabs.crud.facade;

import co.com.parrolabs.crud.controller.dto.CustomerResponse;
import co.com.parrolabs.crud.repository.entity.Customer;
import co.com.parrolabs.crud.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CustomerFacade {
    private final CustomerService customerService;

    @Autowired
    public CustomerFacade(CustomerService customerService) {
        this.customerService = customerService;
    }

    public ResponseEntity<CustomerResponse> getCustomerById(Long id) {
        Optional<Customer> customer = customerService.getCustomerById(id);
        return customer.map(c -> ResponseEntity.ok(CustomerResponse.fromEntity(c))).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<CustomerResponse> createCustomer(CustomerResponse customerResponse) {
        Customer customer = customerResponse.toEntity();
        Optional<Customer> createdCustomer = customerService.createCustomer(customer);
        return createdCustomer.map(c -> ResponseEntity.created(URI.create("/customers/" + c.getCustomerId())).body(CustomerResponse.fromEntity(c)))
                .orElse(ResponseEntity.badRequest().build());
    }

    public List<CustomerResponse> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return customers.stream().map(CustomerResponse::fromEntity).collect(Collectors.toList());
    }

    public ResponseEntity<CustomerResponse> updateCustomer(Long id, CustomerResponse updatedCustomerResponse) {
        Customer updatedCustomer = updatedCustomerResponse.toEntity();
        Optional<Customer> updatedCustomerOpt = customerService.updateCustomer(id, updatedCustomer);
        return updatedCustomerOpt.map(c -> ResponseEntity.ok(CustomerResponse.fromEntity(c))).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Void> deleteCustomer(Long id) {
        boolean deleted = customerService.deleteCustomer(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
