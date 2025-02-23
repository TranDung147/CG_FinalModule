package com.codegym.finalModule.controller.admin;

import com.codegym.finalModule.model.Product;
import com.codegym.finalModule.service.common.CloudinaryService;
import com.codegym.finalModule.service.impl.ProductService;
import com.codegym.finalModule.service.impl.BrandService;
import com.codegym.finalModule.service.impl.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;

import java.text.DecimalFormat;
import java.util.Optional;

@Controller
@RequestMapping("/Admin/product-manager")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private BrandService brandService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CloudinaryService cloudinaryService;

    @GetMapping
    public String showListProduct(
            @RequestParam(name = "searchType", required = false, defaultValue = "name") String searchType,
            @RequestParam(name = "keyword", required = false) String keyword,
            @RequestParam(name = "minPrice", required = false) Double minPrice,
            @RequestParam(name = "maxPrice", required = false) Double maxPrice,
            Model model) {

        List<Product> products = productService.searchProducts(searchType, keyword, minPrice, maxPrice);

        if (products.isEmpty()) {
            model.addAttribute("message", "Không có sản phẩm phù hợp với dữ liệu!");
        } else {
            DecimalFormat decimalFormat = new DecimalFormat("#,### VND");
            for (Product product : products) {
                product.setFormattedPrice(decimalFormat.format(product.getPrice()));
            }
        }
        model.addAttribute("products", products);
        model.addAttribute("keyword", keyword);
        model.addAttribute("minPrice", minPrice);
        model.addAttribute("maxPrice", maxPrice);
        model.addAttribute("searchType", searchType);

        return "admin/product/listProduct";
    }
    @GetMapping("/edit/{id}")
    public String editProductForm(@PathVariable Integer id, Model model) {
        Optional<Product> product = productService.getProductById(id);
        if (product.isPresent()) {
            Product foundProduct = product.get();
            DecimalFormat decimalFormat = new DecimalFormat("#,###");
            foundProduct.setFormattedPrice(decimalFormat.format(foundProduct.getPrice()));

            model.addAttribute("product", foundProduct);
            model.addAttribute("categories", categoryService.getAllCategories());
            model.addAttribute("brands", brandService.getAllBrands());

            return "admin/product/editProduct";
        } else {
            return "redirect:/Admin/product-manager?error=ProductNotFound";
        }
    }

    @PostMapping("/edit")
    public String editProduct(@Valid @ModelAttribute("product") Product product, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("categories", categoryService.getAllCategories());
            model.addAttribute("brands", brandService.getAllBrands());
            return "admin/product/editProduct";
        }
        if (product.getFormattedPrice() != null && !product.getFormattedPrice().isEmpty()) {
            String cleanedPrice = product.getFormattedPrice().replaceAll("[^0-9]", ""); // Xóa ký tự không phải số
            product.setPrice(Double.parseDouble(cleanedPrice)); // Chuyển về Double
        }

        productService.saveProduct(product);
        return "redirect:/Admin/product-manager?success=ProductUpdated";
    }


    @GetMapping("/product-manager")
    public String showListProduct(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("brand", brandService.getAllBrands());

        model.addAttribute("product", new Product());
        return "admin/product/listProduct";
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

