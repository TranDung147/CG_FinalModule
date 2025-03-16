package com.codegym.finalModule.controller.api;

import com.codegym.finalModule.DTO.order.ProductOrderChoiceDTO;
import com.codegym.finalModule.DTO.product.ProductChoiceRequestDTO;
import com.codegym.finalModule.DTO.product.ProductDTO;
import com.codegym.finalModule.service.impl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public ResponseEntity<Page<ProductOrderChoiceDTO>> getAllProducts(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        Page<ProductOrderChoiceDTO> products = productService.getAllProductsDTO(page, size);
        return ResponseEntity.ok(products);
    }

    // API tìm kiếm sản phẩm theo tên có phân trang
    @GetMapping("/search")
    public ResponseEntity<Page<ProductOrderChoiceDTO>> searchProducts(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        Page<ProductOrderChoiceDTO> products = productService.searchProducts(keyword, page, size);
        return ResponseEntity.ok(products);
    }
}
