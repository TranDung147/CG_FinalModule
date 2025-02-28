package com.codegym.finalModule.controller.admin;

import com.codegym.finalModule.model.Category;
import com.codegym.finalModule.service.interfaces.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
        return "admin/category/listCategory";
    }
    @PostMapping("/add-categoryManager")
    public String addCategory(@ModelAttribute("category") Category category, Model model) {
        categoryService.saveCategory(category);
        return "redirect:/Admin/category-manager";
    }

    @PostMapping("/category-manager/delete")
    public ResponseEntity<?> deleteCategories(@RequestBody List<Integer> categoryIds) {
        try {
            categoryService.deleteCategory(categoryIds);
            return ResponseEntity.ok().body("{\"success\": true, \"message\": \"Danh mục đã được xóa thành công!\"}");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("{\"success\": false, \"message\": \"Lỗi khi xóa danh mục!\"}");
        }
    }

}
