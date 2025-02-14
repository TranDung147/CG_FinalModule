package com.codegym.finalModule.controller;

import com.codegym.finalModule.service.Interface.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class AdminHomeController {

    @Autowired
    private IEmployeeService employeeService;

    @GetMapping("/Admin")
    public String showAdminHome() {
        return "admin/layout/layout";
    }

    @PostMapping("/delete")
    public String deleteEmployees(@RequestParam(value = "employeeIds", required = false) List<Integer> employeeIds,
                                  RedirectAttributes redirectAttributes) {
        if (employeeIds != null && !employeeIds.isEmpty()) {
            List<String> deletedEmployees = employeeService.getEmployeeNamesByIds(employeeIds);
            employeeService.deleteEmployeeByID(employeeIds);

            String message = "Xóa nhân viên: " + String.join(", ", deletedEmployees) + " thành công!";
            redirectAttributes.addFlashAttribute("message", message);
        } else {
            redirectAttributes.addFlashAttribute("message", "Không có nhân viên nào được chọn để xóa!");
        }
        return "redirect:/employees";
    }
}

