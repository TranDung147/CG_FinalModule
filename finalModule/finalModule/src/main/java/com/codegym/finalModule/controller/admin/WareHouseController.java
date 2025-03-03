package com.codegym.finalModule.controller.admin;


import com.codegym.finalModule.DTO.transaction.InventoryTransactionDTO;
import com.codegym.finalModule.model.WareHouse;
import com.codegym.finalModule.service.impl.WareHouseService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/warehouses")
public class WareHouseController {

    private final WareHouseService wareHouseService;

    public WareHouseController(WareHouseService wareHouseService) {
        this.wareHouseService = wareHouseService;
    }

    @GetMapping
    public ModelAndView showWareHouse(@RequestParam(name = "searchField", required = false) String field,
                                      @RequestParam(name = "searchInput",
                                              required = false,
                                              defaultValue = "") String keyword,
                                      @RequestParam(name = "page", required = false, defaultValue = "1") int page,
                                      @RequestParam(name = "size", required = false, defaultValue = "5") int size) {
        ModelAndView modelAndView = new ModelAndView("admin/warehouse/warehouse-table");
        Page<WareHouse> wareHousesPage;
        String filterKeyWord = keyword.trim();
        if (!filterKeyWord.isEmpty()) {
            wareHousesPage = this.wareHouseService.searchByFieldAndKey(field, filterKeyWord, page, size);
        } else {
            wareHousesPage = this.wareHouseService.getAllWareHouses(page, size);
        }
        modelAndView.addObject("wareHouses", wareHousesPage);
        modelAndView.addObject("field", field);
        modelAndView.addObject("filterKeyWord", filterKeyWord);
        modelAndView.addObject("currentPage", page);
        modelAndView.addObject("totalPages", wareHousesPage.getTotalPages());
        return modelAndView;
    }

    @GetMapping("/export/{id}")
    public ModelAndView showFormExport (@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView("admin/warehouse/form-export");
        WareHouse wareHouse = this.wareHouseService.getWareHouseById(id);

        InventoryTransactionDTO inventoryTransactionDTO = InventoryTransactionDTO.builder()
                .product_id(wareHouse.getProduct().getProductID())
                .supplier_id(wareHouse.getSupplier().getId())
                .stockQuantity(wareHouse.getQuantity())
                .quantity(0)
                .build();

        modelAndView.addObject("inventoryTransactionDTO" , inventoryTransactionDTO);
        modelAndView.addObject("productName" , wareHouse.getProduct().getName());
        modelAndView.addObject("supplierName" , wareHouse.getSupplier().getName());
        return modelAndView;
    }

}
