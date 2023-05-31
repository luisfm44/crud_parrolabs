package co.com.parrolabs.crud.services;

import co.com.parrolabs.crud.exception.CustomerNotFoundException;
import co.com.parrolabs.crud.repository.entity.Customer;
import co.com.parrolabs.crud.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CustomerService {
    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Optional<Customer> createCustomer(Customer customer) {
        return Optional.ofNullable(customerRepository.save(customer));
    }

    public Optional<Customer> getCustomerById(Long customerId) {
        return Optional.ofNullable(customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found")));
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public boolean deleteCustomer(Long customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if (customer.isPresent()) {
            customerRepository.delete(customer.get());
            return true;
        }
        return false;
    }

    public List<Customer> searchCustomers(String name, String phone, String email) {
        return customerRepository.searchCustomers(name, phone, email);
    }

    public Optional<Customer> updateCustomer(Long id, Customer updatedCustomer) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            updatedCustomer.setCustomerId(id);
            return Optional.ofNullable(customerRepository.save(updatedCustomer));
        }
        return Optional.empty();
    }
}

