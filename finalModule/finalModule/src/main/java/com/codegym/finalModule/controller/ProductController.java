package com.codegym.finalModule.controller;

import com.codegym.finalModule.model.Product;
import com.codegym.finalModule.service.Class.BrandService;
import com.codegym.finalModule.service.Class.CategoryService;
import com.codegym.finalModule.service.Class.ProductService;
import com.codegym.finalModule.service.common.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/Admin")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private BrandService brandService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CloudinaryService cloudinaryService;

    @GetMapping("/product-manager")
    public String showListProduct(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("brand", brandService.getAllBrands());

        model.addAttribute("product", new Product());
        return "admin/product/listproduct";
    }


    @PostMapping("/add-productManager")
    public String addProduct(@ModelAttribute("product") Product product,
                             @RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new RuntimeException("Vui lòng chọn một tệp!");
            }

            // Gọi service để upload ảnh lên Cloudinary
            String imageUrl = cloudinaryService.uploadFileToCloudinary(file);
            product.setImageUrl(imageUrl);

            // Lưu sản phẩm vào database
            productService.saveProduct(product);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/Admin/product-manager";
    }



}
