package com.codegym.finalModule.controller.admin;

import com.codegym.finalModule.dto.EmployeeDTO;
import com.codegym.finalModule.model.Brand;
import com.codegym.finalModule.service.impl.BrandService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/Admin/brand-manager")
public class BrandController {
    @Autowired
    private BrandService brandService;
    @GetMapping
    public String showListBrand(@RequestParam(name = "keyword", required = false) String keyword, Model model) {
        List<Brand> brands;
        if (keyword != null && !keyword.trim().isEmpty()) {
            brands = brandService.findByNameContaining(keyword);
        } else {
            brands = brandService.getAllBrands();
        }
        model.addAttribute("brands", brands);
        return "admin/brand/listBrand";
    }

    @GetMapping("/edit/{id}")
    public String showEditEmployeeForm(@PathVariable Integer id, Model model) {
        Optional<Brand> brand = brandService.getBrandById(id);
        if (brand.isPresent()) {
            model.addAttribute("brand", brand.get());
            return "admin/brand/editBrand";
        } else {
            return "redirect:/Admin/brand-manager?error=BrandNotFound";
        }
    }

    @PostMapping("/edit")
    public String updateEmployee(@Valid @ModelAttribute("employeeDTO") Brand brand,
                                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/brand/editBrand";
        }
        brandService.saveBrand(brand);
        return "redirect:/Admin/brand-manager?success=BrandUpdated";
    }
}
