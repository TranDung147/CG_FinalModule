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

@Controller
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;
   public CustomerController(CustomerService customerService) {
       this.customerService = customerService;
   }

    @GetMapping
    public ModelAndView getAndSearchCustomers(@RequestParam(name = "searchField" , required = false) String field ,
                                      @RequestParam(name = "searchInput",
                                                    required = false ,
                                                    defaultValue = "") String keyword ,
                                      @RequestParam(name = "page" , required = false , defaultValue = "1") int page ,
                                      @RequestParam(name = "size" , required = false , defaultValue = "5") int size) {
        ModelAndView modelAndView = new ModelAndView("customer-task/customer-manager");
        Page<Customer> customerPage;
        String filterKeyWord = keyword.trim();
        if (!filterKeyWord.isEmpty()) {
            customerPage = this.customerService.searchByFieldAndKey(field, filterKeyWord , page , size);
        }   else {
            customerPage = this.customerService.getAllCustomers(page, size);
        }
        modelAndView.addObject("customers" ,customerPage);
        modelAndView.addObject("field" , field);
        modelAndView.addObject("filterKeyWord" , filterKeyWord);
        modelAndView.addObject("currentPage" , page );
        modelAndView.addObject("totalPages" , customerPage.getTotalPages());
        return modelAndView ;
    }
    @GetMapping("/create")
    public ModelAndView createCustomerForm() {
        ModelAndView modelAndView = new ModelAndView("customer-task/customer-form");
        modelAndView.addObject("customerDTO" , new CustomerDTO());
        return modelAndView ;
    }
    @PostMapping("/create")
    public ModelAndView createCustomer(@Valid @ModelAttribute CustomerDTO customerDTO ,
                                       BindingResult bindingResult ,
                                       RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("customer-task/customer-form");
        }
        this.customerService.saveCustomer(customerDTO);
        redirectAttributes.addFlashAttribute("successfulNotification",
                "Đã thêm khách hàng mới !");
        return new ModelAndView("redirect:/customers");
    }
    @GetMapping("/delete/{id}")
    public ModelAndView deleteCustomer(@PathVariable("id") int customerId ,
                                       RedirectAttributes redirectAttributes) {
        this.customerService.deleteCustomer(customerId);
        redirectAttributes.addFlashAttribute("successfulNotification" ,
                "Đã xoá khách hàng có ID : " + customerId );
        return new ModelAndView("redirect:/customers");
    }
    @GetMapping("/updateF/{customerId}")
    public ModelAndView updateFormCustomer(@PathVariable("customerId") int customerId) {
       ModelAndView modelAndView = new ModelAndView("customer-task/customer-update");
       CustomerDTO customerDTO = CustomerDTO.builder()
               .fullName(this.customerService.getCustomerById(customerId).getCustomerName())
               .phone(this.customerService.getCustomerById(customerId).getPhoneNumber())
               .address(this.customerService.getCustomerById(customerId).getAddress())
               .birthDate(this.customerService.getCustomerById(customerId).getBirthDate())
               .build() ;
       modelAndView.addObject("customerDTO" , customerDTO);
       modelAndView.addObject("customerId" , customerId);
       return modelAndView ;
    }
    @PostMapping("/update/{id}")
    public ModelAndView updateCustomer(@ModelAttribute("customerDTO") CustomerDTO customerDTO ,
                                       @PathVariable("id") int customerId ,
                                       RedirectAttributes redirectAttributes ) {
       this.customerService.updateCustomer(customerDTO, customerId);
       redirectAttributes.addFlashAttribute("successfulNotification" ,
               "Đã cập nhật khách hàng !") ;

       return new ModelAndView("redirect:/customers");

    }
}
