package controllers.cg_finalmodule.exception;


import controllers.cg_finalmodule.exception.customer.CustomerException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomerException.class)
    public String handleCustomerException(CustomerException e) {
        return "Chờ giao diện" ;
    }
}
