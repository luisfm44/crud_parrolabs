package co.com.parrolabs.crud.controller.dto;

import co.com.parrolabs.crud.repository.entity.Customer;
import lombok.Data;

import java.io.Serializable;

@Data
public class CustomerResponse implements Serializable {

    private Long id;
    private String name;
    private String phone;
    private String email;
    private ShippingAddressResponse primaryShippingAddress;

    public static CustomerResponse fromEntity(Customer customer) {
        CustomerResponse customerDTO = new CustomerResponse();
        customerDTO.setId(customer.getCustomerId());
        customerDTO.setName(customer.getName());
        customerDTO.setPhone(customer.getPhone());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setPrimaryShippingAddress(ShippingAddressResponse.fromEntity(customer.getShippingAddress()));
        return customerDTO;
    }

    public Customer toEntity() {
        Customer customer = new Customer();
        customer.setCustomerId(this.getId());
        customer.setName(this.getName());
        customer.setPhone(this.getPhone());
        customer.setEmail(this.getEmail());
        customer.setShippingAddress(this.getPrimaryShippingAddress().toEntity());
        return customer;
    }
}
