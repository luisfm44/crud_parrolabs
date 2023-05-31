package co.com.parrolabs.crud.controller.dto;

import co.com.parrolabs.crud.repository.entity.ShippingAddress;
import lombok.Data;

import java.io.Serializable;

@Data
public class ShippingAddressResponse implements Serializable {

    private Long id;
    private String street;
    private String city;
    private String state;
    private String zipCode;
    private String country;
    public static ShippingAddressResponse fromEntity(ShippingAddress shippingAddress) {
        ShippingAddressResponse shippingAddressResponse = new ShippingAddressResponse();
        shippingAddressResponse.setId(shippingAddress.getShippingAddressId());
        shippingAddressResponse.setStreet(shippingAddress.getStreet());
        shippingAddressResponse.setCity(shippingAddress.getCity());
        shippingAddressResponse.setState(shippingAddress.getState());
        shippingAddressResponse.setZipCode(shippingAddress.getZipCode());
        shippingAddressResponse.setCountry(shippingAddress.getCountry());
        return shippingAddressResponse;
    }

    public ShippingAddress toEntity() {
        ShippingAddress shippingAddress = new ShippingAddress();
        shippingAddress.setShippingAddressId(this.getId());
        shippingAddress.setStreet(this.getStreet());
        shippingAddress.setCity(this.getCity());
        shippingAddress.setState(this.getState());
        shippingAddress.setZipCode(this.getZipCode());
        shippingAddress.setCountry(this.getCountry());
        return shippingAddress;
    }
}
