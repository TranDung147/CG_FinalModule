package com.codegym.finalModule.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminHomeController {

    @GetMapping("/Admin")
    public String showAdminHome() {
        return "admin/layout/layout";
    }
}

