package com.codegym.finalModule.controller;

import com.codegym.finalModule.model.Category;
import com.codegym.finalModule.service.Interface.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/Admin")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @GetMapping("/category-manager")
    public String showListCategory(@RequestParam(name = "keyword", required = false) String keyword, Model model) {
        List<Category> categories;
        if (keyword != null && !keyword.trim().isEmpty()) {
            categories = categoryService.findByNameContaining(keyword);
        } else {
            categories = categoryService.getAllCategories();
        }

        model.addAttribute("categories", categories);
        model.addAttribute("categorys", new Category());
        return "admin/category/listcategory";
    }
    @PostMapping("/add-categoryManager")
    public String addCategory(@ModelAttribute("category") Category category, Model model) {
        categoryService.saveCategory(category);
        return "redirect:/Admin/category-manager";
    }

}
