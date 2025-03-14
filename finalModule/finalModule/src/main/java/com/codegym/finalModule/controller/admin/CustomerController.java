package com.codegym.finalModule.controller.admin;


import com.codegym.finalModule.DTO.customer.CustomerDTO;
import com.codegym.finalModule.model.Customer;
import com.codegym.finalModule.service.impl.CustomerService;
import com.codegym.finalModule.service.interfaces.ICustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/Admin/customers")
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
                                              @RequestParam(name = "size", required = false, defaultValue = "5") int size) {
        ModelAndView modelAndView = new ModelAndView("admin/customer/listCustomer");
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

    @GetMapping("/update/{id}")
    public ModelAndView updateFormCustomer(@PathVariable("id") int customerId) {
        ModelAndView modelAndView = new ModelAndView("admin/customer/listCustomer");
        modelAndView.addObject("customerDTO", this.customerService.findCustomerDTOById(customerId));
        return modelAndView;
    }

    @PostMapping("/update")
    public ModelAndView updateCustomer( @Valid @ModelAttribute("customerDTO") CustomerDTO customerDTO,
                                       BindingResult bindingResult,
                                       RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return new ModelAndView("admin/customer/listCustomer");
        }
        this.customerService.updateCustomer(customerDTO , customerDTO.getCustomerId());
        redirectAttributes.addFlashAttribute("successfulNotification",
                "Đã cập nhật khách hàng !");
        return new ModelAndView("redirect:/Admin/customers");
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteCustomer(@RequestBody List<Integer> customerIds) {
        try {
            customerService.deleteCustomer(customerIds);
            return ResponseEntity.ok().body("{\"success\": true, \"message\": \"Danh mục đã được xóa thành công!\"}");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("{\"success\": false, \"message\": \"Lỗi khi xóa danh mục!\"}");
        }
    }
}
