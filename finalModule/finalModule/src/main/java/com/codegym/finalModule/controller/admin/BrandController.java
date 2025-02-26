package com.codegym.finalModule.controller.admin;

import com.codegym.finalModule.DTO.brand.BrandDTO;
import com.codegym.finalModule.model.Brand;
import com.codegym.finalModule.service.impl.BrandService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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
        model.addAttribute("brand", new BrandDTO());
        return "admin/brand/listBrand";
    }

    @PostMapping("/add")
    public String addBrand(@Valid @ModelAttribute("brand") BrandDTO brandDTO,
                           BindingResult bindingResult, Model model,
                           RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("brands", brandService.getAllBrands());

            model.addAttribute("errorMessage", "Dữ liệu nhập không hợp lệ!");
            return "admin/brand/listBrand";
        }
        Brand brand = new Brand();
        BeanUtils.copyProperties(brandDTO, brand);
        brandService.saveBrand(brand);
        model.addAttribute("showModal", true); // Biến để kích hoạt modal
        redirectAttributes.addFlashAttribute("successMessage", "Thêm thương hiệu thành công!");
        return "redirect:/Admin/brand-manager";
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