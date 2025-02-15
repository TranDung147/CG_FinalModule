package com.codegym.finalModule.controller;

import com.codegym.finalModule.model.Category;
import com.codegym.finalModule.service.Class.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/Admin")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/category-manager")
    public String showListCategory(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "admin/category/listcategory";
    }
}
