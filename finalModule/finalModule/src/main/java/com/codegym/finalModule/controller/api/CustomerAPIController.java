package com.codegym.finalModule.controller.api;

import com.codegym.finalModule.DTO.customer.CustomerDTO;
import com.codegym.finalModule.model.Customer;
import com.codegym.finalModule.service.impl.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerAPIController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/select/{keyword}")
    public List<CustomerDTO> getCustomersByKeyword(@PathVariable String keyword) {
        return this.customerService.getCustomersByKeyword(keyword);
    }
}
