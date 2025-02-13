package com.codegym.finalModule.controller;

import com.codegym.finalModule.service.Class.EmployeeService;
import com.codegym.finalModule.service.Interface.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class EmployeeController {
    @Autowired
    private IEmployeeService employeeService;

    @PostMapping("delete/{id}")
    public String deleteEmployee(@PathVariable("employeeID") Integer employeeID, RedirectAttributes redirectAttributes) {
        try {
            employeeService.deleteEmployeeByID(employeeID);
            redirectAttributes.addFlashAttribute("message", "Xóa thành công khuyến mãi!");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", "Xóa thất bại. Có lỗi xảy ra: " + e.getMessage());
        }
        return "redirect:/";
    }
}
