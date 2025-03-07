package com.codegym.finalModule.exception;


import com.codegym.finalModule.DTO.customer.CustomerDTO;
import com.codegym.finalModule.exception.customer.CustomerException;
import com.codegym.finalModule.service.impl.CustomerService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final CustomerService customerService;
    public GlobalExceptionHandler(CustomerService customerService) {
        this.customerService = customerService;
    }
    @ExceptionHandler(NumberFormatException.class)
    public ModelAndView handleNumberFormatException(HttpServletRequest req) {
        ModelAndView modelAndView = new ModelAndView("admin/customer/listCustomer");
        String field = req.getParameter("searchField");
        String key = req.getParameter("searchInput");
        modelAndView.addObject("formatError",
                "Dữ liệu \"" + key + "\" không đúng định dạng \"" + field.toUpperCase() + "\" !");
        modelAndView.addObject("field" , field);
        modelAndView.addObject("filterKeyWord" , key);
        modelAndView.addObject("customers" ,this.customerService.getAllCustomers(1 , 5));
        return modelAndView;
    }

    @ExceptionHandler(CustomerException.class)
    public ModelAndView handleCustomerException(HttpServletRequest request, CustomerException ex) {
        ModelAndView modelAndView;
        Integer id = request.getParameter("customerId") != null
                ? Integer.parseInt(request.getParameter("customerId"))
                : null;

        CustomerDTO customerDTO = CustomerDTO.builder()
                .customerId(id)
                .customerName(request.getParameter("customerName"))
                .phoneNumber(request.getParameter("phoneNumber"))
                .address(request.getParameter("address"))
                .birthDate(LocalDate.parse(request.getParameter("birthDate")))
                .build();
        if (request.getRequestURI().startsWith("/Admin/customers/update")) {
            modelAndView = new ModelAndView("admin/customer/editCustomer");
        } else {
            modelAndView = new ModelAndView("admin/customer/listCustomer");
        }
        modelAndView.addObject("customerDTO" , customerDTO) ;
        modelAndView.addObject("error", ex.getMessage());
        return modelAndView;
    }


}
