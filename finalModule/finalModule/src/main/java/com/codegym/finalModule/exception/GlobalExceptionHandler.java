package com.codegym.finalModule.exception;


import com.codegym.finalModule.service.impl.CustomerService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

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
}
