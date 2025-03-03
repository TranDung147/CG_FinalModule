package com.codegym.finalModule.controller.admin;

import com.codegym.finalModule.DTO.customer.CustomerDTO;
import com.codegym.finalModule.DTO.order.OrderDTO;
import com.codegym.finalModule.DTO.order.ProductOrderDTO;
import com.codegym.finalModule.service.impl.CustomerService;
import com.codegym.finalModule.service.impl.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/Admin/order")
public class OrderController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private OrderService orderService;

    @GetMapping("")
    public String orderList() {
        return "admin/order/listOrder";
    }

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
        if(customerDTO.getId() == null) {
            //Add New Customer
            customerId =  customerService.addCustomerAndGetId(customerDTO);

        }else {
             customerId = customerDTO.getId();
        }
        orderDTO.setCustomerId(customerId);

        //Save Order
        orderService.saveOrder(orderDTO);


        modelAndView.setViewName("admin/order/listOrder");
        return modelAndView;

    }

}
