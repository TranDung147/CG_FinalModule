package com.codegym.finalModule.exception;


import com.codegym.finalModule.DTO.customer.CustomerDTO;
import com.codegym.finalModule.DTO.transaction.InventoryTransactionDTO;
import com.codegym.finalModule.exception.customer.CustomerException;
import com.codegym.finalModule.exception.warehouse.WareHouseException;
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
        ModelAndView modelAndView = new ModelAndView("admin/customer/customer-table");
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
        Integer id = request.getParameter("id") != null
                ? Integer.parseInt(request.getParameter("id"))
                : null;

        CustomerDTO customerDTO = CustomerDTO.builder()
                .id(id)
                .fullName(request.getParameter("fullName"))
                .phone(request.getParameter("phone"))
                .address(request.getParameter("address"))
                .birthDate(LocalDate.parse(request.getParameter("birthDate")))
                .build();
        if (request.getRequestURI().startsWith("/customers/create")) {
            modelAndView = new ModelAndView("admin/customer/customer-add");
        } else if (request.getRequestURI().startsWith("/customers/update")) {
            modelAndView = new ModelAndView("admin/customer/customer-edit");
        } else {
            modelAndView = new ModelAndView("/admin/customer/customer-table");
        }
        modelAndView.addObject("customerDTO" , customerDTO) ;
        modelAndView.addObject("error", ex.getMessage());
        return modelAndView;
    }

    @ExceptionHandler(WareHouseException.class)
    public ModelAndView handleWareHouseException(WareHouseException ex , HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("admin/warehouse/form-export");
        String productName = request.getParameter("productName");
        String supplierName = request.getParameter("supplierName");
        InventoryTransactionDTO inventoryTransactionDTO = InventoryTransactionDTO.builder()
                .product_id(Integer.parseInt(request.getParameter("product_id")))
                .supplier_id(Integer.parseInt(request.getParameter("supplier_id")))
                .stockQuantity(Integer.parseInt(request.getParameter("stockQuantity")))
                .quantity(Integer.parseInt(request.getParameter("quantity")))
                .build();
        System.out.println(inventoryTransactionDTO);
        modelAndView.addObject("error", ex.getMessage());
        modelAndView.addObject("productName" , productName);
        modelAndView.addObject("supplierName" , supplierName);
        modelAndView.addObject("inventoryTransactionDTO" , inventoryTransactionDTO);
        return modelAndView;
    }


}
