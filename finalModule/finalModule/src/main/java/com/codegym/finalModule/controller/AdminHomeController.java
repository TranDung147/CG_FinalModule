package com.codegym.finalModule.controller;

import com.codegym.finalModule.model.Employee;
import com.codegym.finalModule.service.Interface.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/Admin")
public class AdminHomeController {
    @Autowired
    private IEmployeeService iemployeeService;
    //    @GetMapping("/Admin")
//    public String showAdminHome() {
//
//        return "admin/layout/layout";
//    }
    @GetMapping
    public String EmployeeList(
            Model model,
            @RequestParam(name = "keyword", required = false, defaultValue = "") String keyword,
            @RequestParam(name = "type", required = false, defaultValue = "all") String type,
            @RequestParam(name = "page", required = false, defaultValue = "1") Integer pageNo){
        Page<Employee> list = iemployeeService.searchUsers(keyword, type, pageNo);

        // Đưa dữ liệu vào model
        model.addAttribute("list", list.getContent());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", list.getTotalPages());
        model.addAttribute("keyword", keyword);
        model.addAttribute("type", type);

        return "admin/layout/layout"; // Trả về trang giao diện
    }

    //    @PostMapping("delete/{employeeId}")
//    public String deleteEmployee(@PathVariable("employeeId") Integer employeeId, RedirectAttributes redirectAttributes) {
//        try {
//            iemployeeService.deleteEmployeeByID(employeeId);
//            redirectAttributes.addFlashAttribute("message", "Xóa thành công khuyến mãi!");
//        } catch (RuntimeException e) {
//            redirectAttributes.addFlashAttribute("error", "Xóa thất bại. Có lỗi xảy ra: " + e.getMessage());
//        }
//        return "redirect:/Admin";
//    }
    @GetMapping("/Admin/delete")
    public String delete(@RequestParam("employeeId") Integer employeeId) {
        iemployeeService.deleteEmployeeByID(employeeId);
        return "redirect:/Admin";
    }


}

