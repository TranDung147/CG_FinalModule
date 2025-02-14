package com.codegym.finalModule.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Admin")
public class CategoryController {
    @GetMapping("/category-manager")
    public String showListCategory() {
        return "admin/layout/layout";
    }
}
