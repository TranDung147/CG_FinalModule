package com.codegym.finalModule.controller.admin;

import com.codegym.finalModule.DTO.product.ProductChoiceDTO;
import com.codegym.finalModule.DTO.warehouse.WarehouseDTO;
import com.codegym.finalModule.mapper.product.ProductMapper;
import com.codegym.finalModule.model.Product;
import com.codegym.finalModule.model.Supplier;
import com.codegym.finalModule.model.WareHouse;
import com.codegym.finalModule.service.impl.SupplierService;
import com.codegym.finalModule.service.impl.WareHouseService;
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
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/Admin/ware-houses")
public class WareHouseController {

    private final WareHouseService wareHouseService;
    private final IProductService productService;
    private final ProductMapper productMapper;

    public WareHouseController(WareHouseService wareHouseService,
                               IProductService productService,
                               ProductMapper productMapper) {
        this.wareHouseService = wareHouseService;
        this.productService = productService;
        this.productMapper = productMapper;
    }


    @GetMapping("/test")
    public String test() {
        return "admin/warehouse/test";
    }

    @GetMapping
    public ModelAndView showWareHouse(@RequestParam(name = "searchField", required = false) String field,
                                      @RequestParam(name = "searchInput",
                                              required = false,
                                              defaultValue = "") String keyword,
                                      @RequestParam(name = "statusStock", required = false, defaultValue = "0") Integer statusStock,
                                      @RequestParam(name = "page", required = false, defaultValue = "1") int page,
                                      @RequestParam(name = "size", required = false, defaultValue = "5") int size) {
        ModelAndView modelAndView = new ModelAndView("admin/warehouse/warehouse-table");
        String filterKeyWord = keyword.trim();
        Page<WareHouse> wareHousesPage;
        if (statusStock == 0) {
            wareHousesPage = this.wareHouseService.searchWareHouses(field, filterKeyWord,
                    null, page, size);
        } else {
            wareHousesPage = this.wareHouseService.searchWareHouses(field, filterKeyWord,
                    statusStock, page, size);
        }

        modelAndView.addObject("wareHouses", wareHousesPage);
        modelAndView.addObject("field", field);
        modelAndView.addObject("statusStock", statusStock);
        modelAndView.addObject("filterKeyWord", filterKeyWord);
        modelAndView.addObject("currentPage", page);
        modelAndView.addObject("totalPages", wareHousesPage.getTotalPages());
        return modelAndView;
    }

    @GetMapping("/api/products-choice")
    @ResponseBody
    public List<ProductChoiceDTO> getProductChoices() {
        List<Product> products = this.productService.getAllProducts();
        return products.stream().map(this.productMapper::convertToProductChoiceDTO).toList();
    }

    @GetMapping("/api/products-choiceFromWareHouse")
    @ResponseBody
    public List<ProductChoiceDTO> getProductChoiceFromWareHouse() {
        List<WareHouse> wareHouseList = this.wareHouseService.getWareHouses();
        return wareHouseList.stream().map(this.productMapper::convertToProductChoiceDTOByWareHouse).toList();
    }

    @GetMapping("/import")
    public ModelAndView showImport() {
        return new ModelAndView("admin/warehouse/import");
    }
    @GetMapping("/export")
    public ModelAndView showExport() {
        return new ModelAndView("admin/warehouse/export");
    }

}