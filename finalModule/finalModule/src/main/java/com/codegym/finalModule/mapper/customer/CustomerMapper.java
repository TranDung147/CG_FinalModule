package com.codegym.finalModule.mapper.customer;


import com.codegym.finalModule.DTO.customer.CustomerDTO;
import com.codegym.finalModule.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public Customer convertToCustomer(CustomerDTO customerDTO) {
        return Customer.builder()
                .customerName(customerDTO.getCustomerName())
                .phoneNumber(customerDTO.getPhoneNumber())
                .address(customerDTO.getAddress())
                .birthDate(customerDTO.getBirthDate())
                .isDisabled(false)
                .build();
    }

    public CustomerDTO convertToCustomerDTO(Customer customer) {
        return CustomerDTO.builder()
                .customerId(customer.getCustomerId())
                .customerName(customer.getCustomerName())
                .phoneNumber(customer.getPhoneNumber())
                .address(customer.getAddress())
                .birthDate(customer.getBirthDate())
                .email(customer.getEmail())
//                .email(customer.getUser() != null ? customer.getUser().getEmail() : "Không có email")
                .build();
    }
}
