package com.codegym.finalModule.controller.api;

import com.codegym.finalModule.DTO.product.ProductDTO;
import com.codegym.finalModule.service.impl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductAPIController {

    @Autowired
    private ProductService productService;

    @GetMapping("/select/{keyword}")
    public List<ProductDTO> getProductsByKeyword(@PathVariable String keyword) {
        return this.productService.getProductsDTOByKeyword(keyword);
    }
}
