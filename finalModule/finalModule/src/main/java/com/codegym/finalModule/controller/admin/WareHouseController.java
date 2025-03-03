package com.codegym.finalModule.controller.admin;

import com.codegym.finalModule.DTO.warehouse.WarehouseDTO;
import com.codegym.finalModule.enums.ProductStockStatus;
import com.codegym.finalModule.model.Product;
import com.codegym.finalModule.model.Supplier;
import com.codegym.finalModule.model.WareHouse;
import com.codegym.finalModule.service.impl.SupplierService;
import com.codegym.finalModule.service.interfaces.IProductService;
import com.codegym.finalModule.service.interfaces.ISupplierService;
import com.codegym.finalModule.service.interfaces.IWareHouseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("Admin/stock-import")
public class WareHouseController {
    @Autowired
    private IWareHouseService iwareHouseService;

    @Autowired
    private IProductService iProductService;

    @Autowired
    private SupplierService iSupplierService;

    @GetMapping
    public String ShowList(
            @RequestParam(name = "pageNo", required = false, defaultValue = "1") Integer pageNo,
            @RequestParam(name = "keyword", required = false) String keyword,
            @RequestParam(name = "statusStock", required = false) String statusStock,
            Model model) {
        Page<WareHouse> list;

        ProductStockStatus stockStatusEnum = null;
        if (statusStock != null && !statusStock.isEmpty()) {
            try {
                stockStatusEnum = ProductStockStatus.valueOf(statusStock);
            } catch (IllegalArgumentException e) {
                model.addAttribute("error", "Trạng thái kho không hợp lệ!");
            }
        }

        if ((keyword != null && !keyword.isEmpty()) || stockStatusEnum != null) {
            list = iwareHouseService.findByKeyword(keyword, String.valueOf(stockStatusEnum), pageNo);
        } else {
            list = iwareHouseService.findAll(pageNo);
        }

        model.addAttribute("totalPages", list.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("list", list);
        model.addAttribute("keyword", keyword);
        model.addAttribute("statusStock", statusStock);

        return "admin/warehouse/list";
    }

    @GetMapping("/import")
    public String showImportForm(Model model) {
        model.addAttribute("warehouseDTO", new WarehouseDTO()); // Use DTO here
        List<Product> products = iProductService.getAllProducts();
        List<Supplier> suppliers = iSupplierService.getAllSuppliers();
        model.addAttribute("products", products);
        model.addAttribute("suppliers", suppliers);
        return "admin/warehouse/import";
    }

    @PostMapping("/import")
    public String importStock(
            @Valid @ModelAttribute("warehouseDTO") WarehouseDTO warehouseDTO,
            BindingResult bindingResult,
            Model model) {

        // Check for validation errors
        if (bindingResult.hasErrors()) {
            model.addAttribute("products", iProductService.getAllProducts());
            model.addAttribute("suppliers", iSupplierService.getAllSuppliers());
            return "admin/warehouse/import"; // Return to form with errors
        }

        try {
            // Fetch Product and Supplier based on IDs from DTO
            Product product = iProductService.getProductById(warehouseDTO.getProductId())
                    .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy sản phẩm với ID: " + warehouseDTO.getProductId()));

            Supplier supplier = iSupplierService.getSupplierById(warehouseDTO.getSupplierId())
                    .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy nhà cung cấp với ID: " + warehouseDTO.getSupplierId()));

            // Map DTO to Entity
            WareHouse wareHouse = new WareHouse();
            wareHouse.setProduct(product);
            wareHouse.setSupplier(supplier);
            wareHouse.setQuantity(warehouseDTO.getQuantity());
            wareHouse.setPrice(warehouseDTO.getPrice());
            wareHouse.setStatus_stock(warehouseDTO.getStatusStock());

            // Save to database
            iwareHouseService.save(wareHouse);
            model.addAttribute("success", "Nhập kho thành công!");
            return "redirect:/Admin/stock-import"; // Redirect on success
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi nhập kho: " + e.getMessage());
            model.addAttribute("products", iProductService.getAllProducts());
            model.addAttribute("suppliers", iSupplierService.getAllSuppliers());
            return "admin/warehouse/import"; // Return to form with error
        }
    }
}