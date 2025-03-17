package com.codegym.finalModule.controller.admin;

import com.codegym.finalModule.DTO.brand.BrandDTO;
import com.codegym.finalModule.model.Brand;
import com.codegym.finalModule.service.impl.BrandService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/Admin/brand-manager")
public class BrandController {
    @Autowired
    private BrandService brandService;

    @GetMapping
    public String showListBrand(
            @RequestParam(name = "keyword", required = false) String keyword,
            @RequestParam(name = "page", defaultValue = "0") int page,
            Model model) {

        int pageSize = 10; // Số brand hiển thị trên mỗi trang
        Page<Brand> brandPage;

        if (keyword != null && !keyword.trim().isEmpty()) {
            brandPage = brandService.findByNameContainingPaginated(keyword, PageRequest.of(page, pageSize));
        } else {
            brandPage = brandService.getAllBrandsPaginated(PageRequest.of(page, pageSize));
        }

        model.addAttribute("brands", brandPage.getContent());
        model.addAttribute("currentPage", page + 1);
        model.addAttribute("totalPages", brandPage.getTotalPages());
        model.addAttribute("keyword", keyword);
        model.addAttribute("brand", new BrandDTO());

        return "admin/product_brand_category/listBrand";
    }

    @PostMapping("/add")
    public String addBrand(@Valid @ModelAttribute("brand") BrandDTO brandDTO,
                           BindingResult bindingResult, Model model,
                           RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            Page<Brand> brandPage = brandService.getAllBrandsPaginated(PageRequest.of(0, 5));
            model.addAttribute("brands", brandPage.getContent());
            model.addAttribute("currentPage", 1);
            model.addAttribute("totalPages", brandPage.getTotalPages());
            model.addAttribute("errorMessage", "Dữ liệu nhập không hợp lệ!");
            return "admin/product_brand_category/listBrand";
        }
        Brand brand = new Brand();
        BeanUtils.copyProperties(brandDTO, brand);
        brandService.saveBrand(brand);
        model.addAttribute("showModal", true); // Biến để kích hoạt modal
        redirectAttributes.addFlashAttribute("successMessage", "Thêm thương hiệu thành công!");
        return "redirect:/Admin/brand-manager";
    }

    @GetMapping("/edit/{id}")
    public String showEditBrandForm(@PathVariable("id") Integer id, Model model) {
        Optional<Brand> brand = brandService.getBrandById(id);
        if (brand.isPresent()) {
            model.addAttribute("brand", brand);
            return "admin/product_brand_category/listBrand";
        } else {
            return "redirect:/Admin/brand-manager?error=BrandNotFound";
        }
    }

    @PostMapping("/edit")
    public String updateBrand(@Valid @ModelAttribute("brand") Brand brand,
                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/product_brand_category/listBrand";
        }
        brand.setUpdateAt(LocalDateTime.now());
        brandService.saveBrand(brand);
        return "redirect:/Admin/brand-manager?success=BrandUpdated";
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteBrands(@RequestBody List<Integer> brandIds) {
        try {
            brandService.deleteBrand(brandIds);
            return ResponseEntity.ok().body("{\"success\": true, \"message\": \"Thương hiệu đã được xóa thành công!\"}");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("{\"success\": false, \"message\": \"Lỗi khi xóa thương hiệu!\"}");
        }
    }

    @GetMapping("/check-name")
    @ResponseBody
    public ResponseEntity<Object> checkBrandNameExists(@RequestParam("name") String name) {
        boolean exists = brandService.existsByName(name);
        return ResponseEntity.ok().body(
                Map.of("exists", exists)
        );
    }
}