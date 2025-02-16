package com.codegym.finalModule.exception;


import com.codegym.finalModule.dto.customer.CustomerDTO;
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
        ModelAndView modelAndView = new ModelAndView("customer-task/customer-manager");
        String field = req.getParameter("searchField");
        String key = req.getParameter("searchInput");
        modelAndView.addObject("formatError",
                "Dữ liệu \"" + key + "\" không đúng định dạng \"" + field + "\"");
        modelAndView.addObject("field" , field);
        modelAndView.addObject("filterKeyWord" , key);
        modelAndView.addObject("customers" ,this.customerService.getAllCustomers(1 , 5));
        return modelAndView;
    }
    @ExceptionHandler(CustomerException.class)
    public ModelAndView handleCustomerException(CustomerException ex , HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("customer-task/customer-update");
        String path = request.getRequestURI();
        int customerId = Integer.parseInt(path.substring(path.lastIndexOf("/") + 1));
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFullName(request.getParameter("fullName"));
        customerDTO.setPhone(request.getParameter("phone"));
        customerDTO.setAddress(request.getParameter("address"));
        customerDTO.setBirthDate(LocalDate.parse(request.getParameter("birthDate")));
        modelAndView.addObject("customerDTO" , customerDTO);
        modelAndView.addObject("invalidPhone" , ex.getMessage());
        modelAndView.addObject("customerId" , customerId);
        return modelAndView;
        }

}
