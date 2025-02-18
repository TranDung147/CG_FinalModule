package com.codegym.finalModule.mapper.customer;


import com.codegym.finalModule.dto.customer.CustomerDTO;
import com.codegym.finalModule.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public Customer convertToCustomerToCustomer(CustomerDTO customerDTO) {
        return Customer.builder()
                .customerName(customerDTO.getFullName())
                .phoneNumber(customerDTO.getPhone())
                .address(customerDTO.getAddress())
                .birthDate(customerDTO.getBirthDate())
                .build();
    }
}
