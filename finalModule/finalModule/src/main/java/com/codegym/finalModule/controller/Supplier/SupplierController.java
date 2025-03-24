package com.codegym.finalModule.controller.Supplier;

import com.codegym.finalModule.DTO.supplier.SupplierDTO;
import com.codegym.finalModule.model.Product;
import com.codegym.finalModule.model.Supplier;
import com.codegym.finalModule.service.impl.ProductService;
import com.codegym.finalModule.service.impl.SupplierService;
import com.codegym.finalModule.service.interfaces.IProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/Admin/suppliers-manager")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;
    @Autowired
    private IProductService productService;

    @GetMapping
    public String listSuppliers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(required = false) String filter,
            @RequestParam(required = false) String keyword,
            Model model) {
        // Add empty DTO objects for the add and edit modals
        if (!model.containsAttribute("supplierDTO")) {
            model.addAttribute("supplierDTO", new SupplierDTO());
        }
        if (!model.containsAttribute("editSupplier")) {
            model.addAttribute("editSupplier", new Supplier());
        }

        Page<Supplier> supplierPage;
        if (filter != null && keyword != null && !keyword.isEmpty()) {
            List<Supplier> searchResults = supplierService.searchSuppliers(filter, keyword);
            model.addAttribute("suppliers", searchResults);
            // Since search doesn't use pagination in current implementation, create a dummy page
            supplierPage = new Page<Supplier>() {
                @Override
                public int getTotalPages() {
                    return 1;
                }

                @Override
                public long getTotalElements() {
                    return searchResults.size();
                }

                @Override
                public <U> Page<U> map(java.util.function.Function<? super Supplier, ? extends U> converter) {
                    return null;
                }

                @Override
                public int getNumber() {
                    return 0;
                }

                @Override
                public int getSize() {
                    return searchResults.size();
                }

                @Override
                public int getNumberOfElements() {
                    return searchResults.size();
                }

                @Override
                public List<Supplier> getContent() {
                    return searchResults;
                }

                @Override
                public boolean hasContent() {
                    return !searchResults.isEmpty();
                }

                @Override
                public org.springframework.data.domain.Sort getSort() {
                    return org.springframework.data.domain.Sort.unsorted();
                }

                @Override
                public boolean isFirst() {
                    return true;
                }

                @Override
                public boolean isLast() {
                    return true;
                }

                @Override
                public boolean hasNext() {
                    return false;
                }

                @Override
                public boolean hasPrevious() {
                    return false;
                }

                @Override
                public org.springframework.data.domain.Pageable nextPageable() {
                    return null;
                }

                @Override
                public org.springframework.data.domain.Pageable previousPageable() {
                    return null;
                }

                @Override
                public java.util.Iterator<Supplier> iterator() {
                    return searchResults.iterator();
                }
            };
        } else {
            supplierPage = supplierService.getSuppliers(page, size);
            model.addAttribute("suppliers", supplierPage.getContent());
        }

        model.addAttribute("currentPage", supplierPage.getNumber());
        model.addAttribute("totalPages", supplierPage.getTotalPages());
        model.addAttribute("totalItems", supplierPage.getTotalElements());
        model.addAttribute("pageSize", size);

        return "admin/suppliers/list";
    }

    @GetMapping("/{id}")
    public String getSupplier(@PathVariable Integer id, Model model) {
        Optional<Supplier> supplier = supplierService.getSupplierById(id);
        if (supplier.isPresent()) {
            model.addAttribute("supplier", supplier.get());

            // Use the dedicated method instead of filtering all products
            List<Product> supplierProducts = productService.getProductsBySupplier(id);

            // Add debugging info
            if (supplierProducts.isEmpty()) {
                System.out.println("No products found for supplier " + id);
            } else {
                System.out.println("Found " + supplierProducts.size() + " products for supplier " + id);
                // Print the first product's properties to see its structure
                Product firstProduct = supplierProducts.get(0);
                System.out.println("Product ID: " + firstProduct.getProductID());
                System.out.println("Product Name: " + firstProduct.getName());
                // Add more properties as needed
            }

            model.addAttribute("products", supplierProducts);
            return "admin/suppliers/details";
        }
        return "redirect:/Admin/suppliers-manager";
    }

    @GetMapping("/get/{id}")
    @ResponseBody
    public SupplierDTO getSupplierForEdit(@PathVariable Integer id) {
        Optional<Supplier> supplier = supplierService.getSupplierById(id);
        if (supplier.isPresent()) {
            Supplier s = supplier.get();
            SupplierDTO dto = new SupplierDTO();
            dto.setId(s.getId().longValue());
            dto.setSupplierCode(s.getSupplierCode());
            dto.setName(s.getName());
            dto.setAddress(s.getAddress());
            dto.setPhone(s.getPhone());
            dto.setEmail(s.getEmail());
            return dto;
        }
        return new SupplierDTO();
    }

    @PostMapping("/add")
    public String addSupplier(
            @Valid @ModelAttribute("supplierDTO") SupplierDTO supplierDTO,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.supplierDTO", bindingResult);
            redirectAttributes.addFlashAttribute("supplierDTO", supplierDTO);
            redirectAttributes.addFlashAttribute("showAddModal", true);
            return "redirect:/Admin/suppliers-manager";
        }
        try {
            Supplier supplier = new Supplier();
            supplier.setSupplierCode(supplierDTO.getSupplierCode());
            supplier.setName(supplierDTO.getName());
            supplier.setAddress(supplierDTO.getAddress());
            supplier.setPhone(supplierDTO.getPhone());
            supplier.setEmail(supplierDTO.getEmail());
            supplierService.addSupplier(supplier);
            redirectAttributes.addFlashAttribute("successMessage", "Thêm nhà cung cấp thành công");
            return "redirect:/Admin/suppliers-manager";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Lỗi khi thêm nhà cung cấp: " + e.getMessage());
            redirectAttributes.addFlashAttribute("supplierDTO", supplierDTO);
            redirectAttributes.addFlashAttribute("showAddModal", true);
            return "admin/suppliers/list"; // Return to form with error
        }
    }

    @PostMapping("/edit")
    public String updateSupplier(
            @Valid @ModelAttribute("editSupplier") SupplierDTO supplierDTO,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.editSupplier", bindingResult);
            redirectAttributes.addFlashAttribute("editSupplier", supplierDTO);
            redirectAttributes.addFlashAttribute("showEditModal", true);
            redirectAttributes.addFlashAttribute("errorMessage", "Vui lòng kiểm tra lại thông tin nhà cung cấp");
            return "redirect:/Admin/suppliers-manager";
        }

        try {
            Supplier supplier = new Supplier();
            supplier.setId(supplierDTO.getId().intValue());
            supplier.setSupplierCode(supplierDTO.getSupplierCode());
            supplier.setName(supplierDTO.getName());
            supplier.setAddress(supplierDTO.getAddress());
            supplier.setPhone(supplierDTO.getPhone());
            supplier.setEmail(supplierDTO.getEmail());

            supplierService.updateSupplier(supplier.getId(), supplier);
            redirectAttributes.addFlashAttribute("successMessage", "Cập nhật nhà cung cấp thành công");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi khi cập nhật nhà cung cấp: " + e.getMessage());
            redirectAttributes.addFlashAttribute("editSupplier", supplierDTO);
            redirectAttributes.addFlashAttribute("showEditModal", true);
        }
        return "redirect:/Admin/suppliers-manager";
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteSupplier(@RequestBody List<Integer> supplierIds) {
        try {
            supplierService.deleteSuppliers(supplierIds);
            return ResponseEntity.ok().body("{\"success\": true, \"message\": \"Danh mục đã được xóa thành công!\"}");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("{\"success\": false, \"message\": \"Lỗi khi xóa danh mục!\"}");
        }
    }
}