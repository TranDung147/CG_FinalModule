package com.codegym.finalModule.controller.admin;

import com.codegym.finalModule.DTO.category.CategoryDTO;
import com.codegym.finalModule.mapper.category.CategoryMapper;
import com.codegym.finalModule.model.Category;
import com.codegym.finalModule.service.interfaces.ICategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/Admin/category-manager")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private CategoryMapper categoryMapper;

    @GetMapping()
    public String showListCategory(
            @RequestParam(name = "keyword", required = false) String keyword,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size,
            Model model) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Category> categoryPage;

        if (keyword != null && !keyword.trim().isEmpty()) {
            categoryPage = categoryService.findByNameContainingPaged(keyword, pageable);
        } else {
            categoryPage = categoryService.getAllCategoriesPaged(pageable);
        }

        model.addAttribute("categories", categoryPage.getContent());
        model.addAttribute("categoryPage", categoryPage);
        model.addAttribute("currentPage", page + 1);
        model.addAttribute("totalPages", categoryPage.getTotalPages());
        model.addAttribute("keyword", keyword);

        // Adding a new empty CategoryDTO for the add form
        if (!model.containsAttribute("categorys")) {
            model.addAttribute("categorys", new CategoryDTO());
        }

        return "admin/product_brand_category/listCategory";
    }

    @PostMapping("/add")
    public String addCategory(@Valid @ModelAttribute("categorys") CategoryDTO categoryDTO,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes,
                              Model model) {
        if (categoryService.existsByName(categoryDTO.getName())) {
            bindingResult.rejectValue("name", "error.category", "Tên danh mục đã tồn tại");
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.categorys", bindingResult);
            redirectAttributes.addFlashAttribute("categorys", categoryDTO);
            redirectAttributes.addFlashAttribute("showModal", true);
            return "redirect:/Admin/category-manager";
        }
        Category category = categoryMapper.toEntity(categoryDTO);
        categoryService.saveCategory(category);

        redirectAttributes.addFlashAttribute("successMessage", "Thêm danh mục thành công!");
        return "redirect:/Admin/category-manager";
    }

    @PostMapping("/edit")
    public String updateCategory(@Valid @ModelAttribute("category") CategoryDTO categoryDTO,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes) {

        if (categoryService.existsByNameAndNotId(categoryDTO.getName(), categoryDTO.getCategoryID())) {
            bindingResult.rejectValue("name", "error.category", "Tên danh mục đã tồn tại");
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.editCategory", bindingResult);
            redirectAttributes.addFlashAttribute("editCategory", categoryDTO);
            redirectAttributes.addFlashAttribute("showEditModal", true);
            return "redirect:/Admin/category-manager";
        }

        Optional<Category> existingCategoryOpt = categoryService.getCategoryById(categoryDTO.getCategoryID());

        if (existingCategoryOpt.isPresent()) {
            Category existingCategory = existingCategoryOpt.get();
            categoryMapper.updateEntityFromDto(categoryDTO, existingCategory);
            existingCategory.setUpdateAt(LocalDateTime.now());
            categoryService.saveCategory(existingCategory);

            redirectAttributes.addFlashAttribute("successMessage", "Cập nhật danh mục thành công!");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Không tìm thấy danh mục!");
        }

        return "redirect:/Admin/category-manager";
    }
    @PostMapping("/delete")
    public ResponseEntity<?> deleteCategories(@RequestBody List<Integer> categoryIds) {
        try {
            // Check if any categories have related products
            if (categoryService.hasRelatedProducts(categoryIds)) {
                return ResponseEntity.badRequest().body("{\"success\": false, \"message\": \"Không thể xóa danh mục có sản phẩm liên quan!\"}");
            }

            categoryService.deleteCategory(categoryIds);
            return ResponseEntity.ok().body("{\"success\": true, \"message\": \"Danh mục đã được xóa thành công!\"}");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("{\"success\": false, \"message\": \"Lỗi khi xóa danh mục: " + e.getMessage() + "\"}");
        }
    }

    @GetMapping("/check-name")
    @ResponseBody
    public ResponseEntity<Boolean> checkNameExists(@RequestParam String name, @RequestParam(required = false) Integer id) {
        boolean exists;
        if (id != null) {
            exists = categoryService.existsByNameAndNotId(name, id);
        } else {
            exists = categoryService.existsByName(name);
        }
        return ResponseEntity.ok(exists);
    }
}
