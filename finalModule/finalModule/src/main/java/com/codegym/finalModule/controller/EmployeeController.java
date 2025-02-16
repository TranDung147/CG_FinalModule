package com.codegym.finalModule.controller;

import com.codegym.finalModule.model.Employee;
import com.codegym.finalModule.service.Interface.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/Admin")
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    @GetMapping("/employee-manager/create")
    public String showAddEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "admin/employee/addemployee";
    }

    @PostMapping("/employee-manager/create")
    public String addEmployee(@ModelAttribute("employee") Employee employee, RedirectAttributes redirectAttributes) {
        if (employeeService.existsByEmail(employee.getEmployeeEmail())) {
            redirectAttributes.addFlashAttribute("error", "Email này đã tồn tại, vui lòng nhập email khác!");
            return "redirect:/Admin/employee-manager/create";
        }
        employee.setEmployeeId(null);
        employeeService.save(employee);
        redirectAttributes.addFlashAttribute("message", "Thêm nhân viên thành công!");
        return "redirect:/Admin/employee-manager";
    }
}
