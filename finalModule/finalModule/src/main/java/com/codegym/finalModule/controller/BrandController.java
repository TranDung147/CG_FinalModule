package com.codegym.finalModule.controller;

import com.codegym.finalModule.DTO.BrandDTO;
import com.codegym.finalModule.model.Brand;
import com.codegym.finalModule.service.Class.BrandService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/Admin")
public class BrandController {
    @Autowired
    private BrandService brandService;
//    @GetMapping("/brand-manager")
//    public String showListBrand(Model model) {
//        List<Brand> brands = brandService.getAllBrands();
//        model.addAttribute("brands", brands);
//        return "admin/brand/listbrand";
//    }

    @GetMapping("/brand-manager")
    public String showBrandList(Model model) {
        model.addAttribute("brands", brandService.getAllBrands());
        model.addAttribute("brand", new BrandDTO()); // Sử dụng DTO thay vì Entity
        return "admin/brand/listbrand";
    }

    @PostMapping("/add-brandManager")
    public String addBrand(@Valid @ModelAttribute("brandDTO") BrandDTO brandDTO,
                           BindingResult result,
                           RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Dữ liệu không hợp lệ!");
            return "admin/brand/listbrand";
        }
        // Sao chép và lưu dữ liệu
        Brand brand = new Brand();
        BeanUtils.copyProperties(brandDTO, brand);
        brandService.save(brand);
        redirectAttributes.addFlashAttribute("successMessage", "Thêm thương hiệu thành công!");
        return "redirect:/Admin/brand-manager";
    }


}
