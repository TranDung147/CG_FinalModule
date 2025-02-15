package com.codegym.finalModule.controller;

import com.codegym.finalModule.model.Product;
import com.codegym.finalModule.service.Class.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/Admin")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/product-manager")
    public String showListProduct(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "admin/product/listproduct";
    }
}
