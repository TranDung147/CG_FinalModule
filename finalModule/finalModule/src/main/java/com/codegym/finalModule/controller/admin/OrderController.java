package com.codegym.finalModule.controller.admin;

import com.codegym.finalModule.DTO.customer.CustomerDTO;
import com.codegym.finalModule.DTO.order.OrderDTO;
import com.codegym.finalModule.DTO.order.ProductOrderDTO;
import com.codegym.finalModule.model.Customer;
import com.codegym.finalModule.service.impl.CustomerService;
import com.codegym.finalModule.service.impl.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/Admin/order")
public class OrderController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/add")
    public ModelAndView addOrder() {
        ModelAndView modelAndView = new ModelAndView();
        OrderDTO orderDTO = new OrderDTO();
        modelAndView.addObject("orderDTO", orderDTO);
        CustomerDTO customerDTO = new CustomerDTO();
        modelAndView.addObject("customerDTO", customerDTO);
        modelAndView.setViewName("admin/order/addOrder");
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView createOrder(@Valid @ModelAttribute("orderDTO") OrderDTO orderDTO,
                                   BindingResult bindingResult,
                                   RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return new ModelAndView("admin/order/addOrder");

        }
        ModelAndView modelAndView = new ModelAndView();

        //Check Customer
        CustomerDTO customerDTO = orderDTO.getCustomerDTO();
        Integer customerId = null;
        if(customerDTO.getCustomerId() == null) {
            //Add New Customer
            customerId =  customerService.addCustomerAndGetId(customerDTO);

        }else {
             customerId = customerDTO.getCustomerId();
        }
        orderDTO.setCustomerId(customerId);

        //Save Order
        orderService.saveOrder(orderDTO);


        modelAndView.setViewName("admin/order/listOrder");
        return modelAndView;

    }

    //Show list for customer in order
    @GetMapping("/showListCustomer")
    public String listCustomers(
            @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
            @RequestParam(value = "filter", required = false, defaultValue = "name") String filter,
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "size", required = false, defaultValue = "5") int size,
            Model model) {

        Page<CustomerDTO> customers = (keyword != null && !keyword.isEmpty())
                ? orderService.searchCustomers(keyword, filter, page, size)
                : orderService.getAllCustomersDTO(page, size);

        model.addAttribute("customerDTO", customers);
        model.addAttribute("customers", customers);
        model.addAttribute("keyword", keyword);
        model.addAttribute("filter", filter);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", customers.getTotalPages());
        model.addAttribute("pageSize", size);

        return "admin/order/OldCustomer";
    }
}
