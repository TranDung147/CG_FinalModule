package com.codegym.finalModule.controller.api;

import com.codegym.finalModule.DTO.order.ProductChosen;
import com.codegym.finalModule.model.Product;
import com.codegym.finalModule.service.impl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductAPIController {

    @Autowired
    private ProductService productService;

//    @GetMapping("/select/{keyword}")
//    public List<ProductDTO> getProductsByKeyword(@PathVariable String keyword) {
//        return this.productService.getProductsDTOByKeyword(keyword);
//    }

    @GetMapping("/productChosen/{id}")
    public ProductChosen getProductById(@PathVariable Integer id) {
        ProductChosen product = productService.getProductByIdUseInOrder(id);
        if (product == null) {
            throw new RuntimeException("Không tìm thấy sản phẩm với ID: " + id);
        }
        return product;
    }

}
