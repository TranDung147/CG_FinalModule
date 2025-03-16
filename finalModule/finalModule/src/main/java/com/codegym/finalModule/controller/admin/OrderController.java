package com.codegym.finalModule.controller.admin;

import com.codegym.finalModule.DTO.customer.CustomerDTO;
import com.codegym.finalModule.DTO.order.OrderDTO;
import com.codegym.finalModule.DTO.order.ProductOrderDTO;
import com.codegym.finalModule.model.Customer;
import com.codegym.finalModule.service.common.PDFService;
import com.codegym.finalModule.service.impl.CustomerService;
import com.codegym.finalModule.service.impl.OrderService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/Admin/order")
public class OrderController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private PDFService pdfService;

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
    public Object createOrder(@Valid @ModelAttribute("orderDTO") OrderDTO orderDTO,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {
        // Nếu có lỗi, trả về ModelAndView để hiển thị lỗi trên trang
        if (bindingResult.hasErrors()) {
            return new ModelAndView("admin/order/addOrder");
        }

        Integer customerId = (orderDTO.getCustomerDTO().getCustomerId() == null) ?
                customerService.addCustomerAndGetId(orderDTO.getCustomerDTO()) :
                orderDTO.getCustomerDTO().getCustomerId();

        orderDTO.setCustomerId(customerId);
        Integer orderId = orderService.saveOrder(orderDTO);

        // Nếu cần in hóa đơn, trả về JSON để frontend xử lý tải file PDF
        if (orderDTO.getIsPrintInvoice()) {
            return ResponseEntity.ok().body("{\"orderId\": " + orderId + ", \"isPrintInvoice\": true}");
        }

        // Nếu không cần in hóa đơn, chuyển hướng về trang danh sách đơn hàng
        return ResponseEntity.ok().body("{\"isPrintInvoice\": false}");
    }

    @GetMapping("/downloadInvoicePdf")
    public void downloadInvoicePdf(@RequestParam Integer orderId, HttpServletResponse response) throws IOException {
        OrderDTO orderDTO = orderService.getOrderDTOById(orderId);
        byte[] pdf = pdfService.createInvoicePDF(orderDTO);

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=invoice.pdf");
        response.getOutputStream().write(pdf);
        response.getOutputStream().flush();
        response.getOutputStream().close();
    }




    //Show list for customer in order
//    @GetMapping("/showListCustomer")
//    public String listCustomers(
//            @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
//            @RequestParam(value = "filter", required = false, defaultValue = "name") String filter,
//            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
//            @RequestParam(value = "size", required = false, defaultValue = "5") int size,
//            Model model) {
//
//        Page<CustomerDTO> customers = (keyword != null && !keyword.isEmpty())
//                ? orderService.searchCustomers(keyword, filter, page, size)
//                : orderService.getAllCustomersDTO(page, size);
//
//        model.addAttribute("customerDTO", customers);
//        model.addAttribute("customers", customers);
//        model.addAttribute("keyword", keyword);
//        model.addAttribute("filter", filter);
//        model.addAttribute("currentPage", page);
//        model.addAttribute("totalPages", customers.getTotalPages());
//        model.addAttribute("pageSize", size);
//
//        return "admin/order/OldCustomer";
//    }
}
