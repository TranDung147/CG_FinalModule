package com.codegym.finalModule.controller;

import com.codegym.finalModule.model.Brand;
import com.codegym.finalModule.service.Class.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/Admin")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping("/brand-manager")
    public String showListBrand(Model model) {
        List<Brand> brands = brandService.getAllBrands();
        model.addAttribute("brands", brands);
        return "admin/brand/listbrand";
    }
}
