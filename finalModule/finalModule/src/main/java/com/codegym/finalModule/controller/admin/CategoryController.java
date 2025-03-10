package com.codegym.finalModule.controller.admin;

import com.codegym.finalModule.model.Category;
import com.codegym.finalModule.service.interfaces.ICategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/Admin/category-manager")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @GetMapping()
    public String showListCategory(@RequestParam(name = "keyword", required = false) String keyword, Model model) {
        List<Category> categories;
        if (keyword != null && !keyword.trim().isEmpty()) {
            categories = categoryService.findByNameContaining(keyword);
        } else {
            categories = categoryService.getAllCategories();
        }
        model.addAttribute("categories", categories);
        model.addAttribute("categorys", new Category());
        return "admin/product_brand_category/listCategory";
    }
    @PostMapping("/add")
    public String addCategory(@ModelAttribute("category") Category category, Model model) {
        categoryService.saveCategory(category);
        return "redirect:/Admin/category-manager";
    }

    @GetMapping("/edit/{id}")
    public String showEditCategoryForm(@PathVariable Integer id, Model model) {
        Optional<Category> category = categoryService.getCategoryById(id);
        if (category.isPresent()) {
            model.addAttribute("category", category.get());
            return "admin/product_brand_category/listCategory";
        } else {
            return "redirect:/Admin/categorry-manager?error=CategoryNotFound";
        }
    }

    @PostMapping("/edit")
    public String updateCategory(@Valid Category category,
                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/product_brand_category/listCategory";
        }
        Optional<Category> existingCategory = categoryService.getCategoryById(category.getCategoryID());
        if (existingCategory.isPresent()) {
            category.setCreateAt(existingCategory.get().getCreateAt());
        }
        category.setUpdateAt(LocalDateTime.now());

        categoryService.saveCategory(category);
        return "redirect:/Admin/category-manager?success=CategoryUpdated";
    }
    @PostMapping("/delete")
    public ResponseEntity<?> deleteCategories(@RequestBody List<Integer> categoryIds) {
        try {
            categoryService.deleteCategory(categoryIds);
            return ResponseEntity.ok().body("{\"success\": true, \"message\": \"Danh mục đã được xóa thành công!\"}");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("{\"success\": false, \"message\": \"Lỗi khi xóa danh mục!\"}");
        }
    }

}
