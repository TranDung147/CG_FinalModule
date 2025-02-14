package com.codegym.finalModule.controller;


import com.codegym.finalModule.service.interfaces.ICustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customers")
public class CustomerController {
    private final ICustomerService icustomerService;
    public CustomerController(ICustomerService icustomerService) {
        this.icustomerService = icustomerService;
    }

    @GetMapping
    public String showCustomers() {
        return "customers";
    }
}
