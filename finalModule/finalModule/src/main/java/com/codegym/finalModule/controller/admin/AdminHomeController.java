package com.codegym.finalModule.controller.admin;

import com.codegym.finalModule.model.Employee;
import com.codegym.finalModule.service.interfaces.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/Admin")
public class AdminHomeController {

    @Autowired
    private IEmployeeService iemployeeService;

    @GetMapping()
    public String showAdminHome(Authentication authentication, Model model) {
        String username = authentication.getName();
        model.addAttribute("username", username);
        return "admin/layout/layout";
    }

//    @GetMapping("/employee-manager")
//    public String EmployeeList(
//            Model model,
//            @RequestParam(name = "keyword", required = false, defaultValue = "") String keyword,
//            @RequestParam(name = "type", required = false, defaultValue = "all") String type,
//            @RequestParam(name = "page", required = false, defaultValue = "1") Integer pageNo) {
//        Page<Employee> list = iemployeeService.searchByFieldAndKeyword(keyword, type, pageNo);
//        model.addAttribute("list", list.getContent());
//        model.addAttribute("currentPage", pageNo);
//        model.addAttribute("totalPages", list.getTotalPages());
//        model.addAttribute("keyword", keyword);
//        model.addAttribute("type", type);
//
//        return "admin/employee/listEmployee";
//    }
}
