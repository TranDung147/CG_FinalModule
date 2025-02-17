package com.codegym.finalModule.controller;

import com.codegym.finalModule.model.Employee;
import com.codegym.finalModule.service.interfaces.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/Admin")
public class AdminHomeController {

    @Autowired
    private IEmployeeService iemployeeService;

    @GetMapping()
    public String showAdminHome() {
        return "admin/layout/layout";
    }

    @GetMapping("/employee-manager")
    public String EmployeeList(
            Model model,
            @RequestParam(name = "keyword", required = false, defaultValue = "") String keyword,
            @RequestParam(name = "type", required = false, defaultValue = "all") String type,
            @RequestParam(name = "page", required = false, defaultValue = "1") Integer pageNo) {
        Page<Employee> list = iemployeeService.searchUsers(keyword, type, pageNo);
        model.addAttribute("list", list.getContent());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", list.getTotalPages());
        model.addAttribute("keyword", keyword);
        model.addAttribute("type", type);

        return "admin/employee/listemployee";
    }
}
