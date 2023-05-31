package co.com.parrolabs.crud.controller.customer;

import co.com.parrolabs.crud.controller.dto.CustomerResponse;
import co.com.parrolabs.crud.facade.CustomerFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerFacade customerFacade;

    @PostMapping
    public ResponseEntity<CustomerResponse> createCustomer(@RequestBody CustomerResponse customerResponse) {
        return customerFacade.createCustomer(customerResponse);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable Long customerId) {
        return customerFacade.getCustomerById(customerId);
    }

    @GetMapping
    public List<CustomerResponse> getAllCustomers() {
        return customerFacade.getAllCustomers();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponse> updateCustomer(@PathVariable Long id, @RequestBody CustomerResponse updatedCustomerResponse) {
        return customerFacade.updateCustomer(id,updatedCustomerResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        return customerFacade.deleteCustomer(id);
    }
}

