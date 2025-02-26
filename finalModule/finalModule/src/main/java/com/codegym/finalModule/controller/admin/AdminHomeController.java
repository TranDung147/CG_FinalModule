package com.codegym.finalModule.controller.admin;

import com.codegym.finalModule.model.Employee;
import com.codegym.finalModule.service.interfaces.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    public String showAdminHome() {

        return "admin/layout/layout";
    }


}
