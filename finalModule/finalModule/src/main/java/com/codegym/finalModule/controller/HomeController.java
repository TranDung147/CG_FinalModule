package com.codegym.finalModule.controller;

import com.codegym.finalModule.model.User;
import com.codegym.finalModule.service.Interface.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    private IUserService iuserService;
    @GetMapping
    public String home() {
        return "customer/homePage";
    }

    @GetMapping("/listUser")
    public String listUser(Model model)   {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = iuserService.getUserByUserName(username);

        model.addAttribute("user",user);

        return "customer/list";
    }
}
