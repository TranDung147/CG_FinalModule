package com.codegym.finalModule.controller.admin;

import com.codegym.finalModule.enums.ProductStockStatus;
import com.codegym.finalModule.model.WareHouse;
import com.codegym.finalModule.service.interfaces.IWareHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("Admin/stock-import")
public class WareHouseController {
    @Autowired
    private IWareHouseService iwareHouseService;

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
}


