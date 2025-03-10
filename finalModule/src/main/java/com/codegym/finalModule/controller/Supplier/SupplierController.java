package com.codegym.finalModule.controller.Supplier;

import com.codegym.finalModule.DTO.supplier.SupplierDTO;
import com.codegym.finalModule.model.Supplier;
import com.codegym.finalModule.service.impl.SupplierService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/Admin/suppliers-manager")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @GetMapping
    public String listSuppliers(
            @RequestParam(required = false) String filter,
            @RequestParam(required = false) String keyword,
            Model model) {
        if (filter != null && keyword != null && !keyword.isEmpty()) {
            model.addAttribute("suppliers", supplierService.searchSuppliers(filter, keyword));
        } else {
            model.addAttribute("suppliers", supplierService.getAllSuppliers());
        }
        return "admin/suppliers/list";
    }

    @GetMapping("/{id}")
    public String getSupplier(@PathVariable Long id, Model model) {
        Optional<Supplier> supplier = supplierService.getSupplierById(id);
        if (supplier.isPresent()) {
            model.addAttribute("supplier", supplier.get());
            return "admin/suppliers/details";
        }
        return "redirect:/Admin/suppliers-manager";
    }

    @GetMapping("/add")
    public String showAddSupplierForm(Model model) {
        model.addAttribute("supplierDTO", new SupplierDTO()); // Initialize DTO
        return "admin/suppliers/add"; // Return the add supplier form
    }

    @PostMapping("/add")
    public String addSupplier(
            @Valid @ModelAttribute("supplierDTO") SupplierDTO supplierDTO,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {

        // Check for validation errors
        if (bindingResult.hasErrors()) {
            return "admin/suppliers/add"; // Return to form with errors
        }

        try {
            // Map DTO to Entity
            Supplier supplier = new Supplier();
            supplier.setSupplierCode(supplierDTO.getSupplierCode());
            supplier.setName(supplierDTO.getName());
            supplier.setAddress(supplierDTO.getAddress());
            supplier.setPhone(supplierDTO.getPhone());
            supplier.setEmail(supplierDTO.getEmail());

            // Save the supplier
            supplierService.addSupplier(supplier);
            redirectAttributes.addFlashAttribute("successMessage", "Thêm nhà cung cấp thành công");
            return "redirect:/Admin/suppliers-manager";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Lỗi khi thêm nhà cung cấp: " + e.getMessage());
            return "admin/suppliers/add"; // Return to form with error
        }
    }

    @PostMapping("/edit")
    public String updateSupplier(@ModelAttribute Supplier supplier, RedirectAttributes redirectAttributes) {
        try {
            supplierService.updateSupplier(supplier.getId(), supplier);
            redirectAttributes.addFlashAttribute("successMessage", "Cập nhật nhà cung cấp thành công");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi khi cập nhật nhà cung cấp: " + e.getMessage());
        }
        return "redirect:/Admin/suppliers-manager";
    }

    @PostMapping
    public String deleteSelectedSuppliers(
            @RequestParam(value = "ids", required = false) String[] supplierIds,
            RedirectAttributes redirectAttributes) {
        try {
            if (supplierIds != null && supplierIds.length > 0) {
                List<Long> ids = Arrays.stream(supplierIds)
                        .map(Long::parseLong)
                        .collect(Collectors.toList());

                supplierService.deleteSuppliers(ids);
                redirectAttributes.addFlashAttribute("successMessage", "Xóa nhà cung cấp thành công");
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", "Vui lòng chọn ít nhất một nhà cung cấp để xóa");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi khi xóa nhà cung cấp: " + e.getMessage());
        }
        return "redirect:/Admin/suppliers-manager";
    }
}