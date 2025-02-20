package com.codegym.finalModule.mapper.customer;


import com.codegym.finalModule.DTO.customer.CustomerDTO;
import com.codegym.finalModule.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public Customer convertToCustomer(CustomerDTO customerDTO) {
        return Customer.builder()
                .customerName(customerDTO.getFullName())
                .phoneNumber(customerDTO.getPhone())
                .address(customerDTO.getAddress())
                .birthDate(customerDTO.getBirthDate())
                .isDisabled(false)
                .build();
    }

    public CustomerDTO convertToCustomerDTO(Customer customer) {
        return CustomerDTO.builder()
                .id(customer.getCustomerId())
                .fullName(customer.getCustomerName())
                .phone(customer.getPhoneNumber())
                .address(customer.getAddress())
                .birthDate(customer.getBirthDate())
                .email(customer.getUser().getEmail())
                .build();
    }
}
