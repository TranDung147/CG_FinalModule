package com.codegym.finalModule.controller;


import com.codegym.finalModule.dto.customer.CustomerDTO;
import com.codegym.finalModule.model.Customer;
import com.codegym.finalModule.service.impl.CustomerService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ModelAndView getAndSearchCustomers(@RequestParam(name = "searchField", required = false) String field,
                                              @RequestParam(name = "searchInput",
                                                      required = false,
                                                      defaultValue = "") String keyword,
                                              @RequestParam(name = "page", required = false, defaultValue = "1") int page,
                                              @RequestParam(name = "size", required = false, defaultValue = "10") int size) {
        ModelAndView modelAndView = new ModelAndView("admin/customer/customer-table");
        Page<Customer> customerPage;
        String filterKeyWord = keyword.trim();
        if (!filterKeyWord.isEmpty()) {
            customerPage = this.customerService.searchByFieldAndKey(field, filterKeyWord, page, size);
        } else {
            customerPage = this.customerService.getAllCustomers(page, size);
        }
        modelAndView.addObject("customers", customerPage);
        modelAndView.addObject("field", field);
        modelAndView.addObject("filterKeyWord", filterKeyWord);
        modelAndView.addObject("currentPage", page);
        modelAndView.addObject("totalPages", customerPage.getTotalPages());
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView createCustomerForm() {
        ModelAndView modelAndView = new ModelAndView("admin/customer/customer-add");
        modelAndView.addObject("customerDTO", new CustomerDTO());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView createCustomer(@Valid @ModelAttribute CustomerDTO customerDTO,
                                       BindingResult bindingResult,
                                       RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("admin/customer/customer-add");
        }
        this.customerService.saveCustomer(customerDTO);
        redirectAttributes.addFlashAttribute("successfulNotification",
                "Đã thêm khách hàng mới !");
        return new ModelAndView("redirect:/customers");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteCustomer(@PathVariable("id") int customerId,
                                       RedirectAttributes redirectAttributes) {
        this.customerService.deleteCustomer(customerId);
        redirectAttributes.addFlashAttribute("successfulNotification",
                "Đã xoá khách hàng có ID : " + customerId);
        return new ModelAndView("redirect:/customers");
    }

    @GetMapping("/update/{id}")
    public ModelAndView updateFormCustomer(@PathVariable("id") int customerId) {
        ModelAndView modelAndView = new ModelAndView("admin/customer/customer-edit");
        modelAndView.addObject("customerDTO", this.customerService.findCustomerDTOById(customerId));
        return modelAndView;
    }

    @PostMapping("/update")
    public ModelAndView updateCustomer( @Valid @ModelAttribute("customerDTO") CustomerDTO customerDTO,
                                       BindingResult bindingResult,
                                       RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return new ModelAndView("admin/customer/customer-edit");
        }
        this.customerService.updateCustomer(customerDTO , customerDTO.getId());
        redirectAttributes.addFlashAttribute("successfulNotification",
                "Đã cập nhật khách hàng !");
        return new ModelAndView("redirect:/customers");
    }

    @PostMapping("/deleteAll")
    public String deleteAllCustomers(@RequestParam(value = "customerIds", required = false) List<Integer> customerIds,
                                     RedirectAttributes redirectAttributes) {

        if (customerIds == null || customerIds.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Bạn cần chọn ít nhất một Khách Hàng !");
            return "redirect:/customers";
        }
        this.customerService.deleteAllCustomersById(customerIds);
        redirectAttributes.addFlashAttribute("successfulNotification", "Xóa thành công khách hàng !");
        return "redirect:/customers";
    }

}
