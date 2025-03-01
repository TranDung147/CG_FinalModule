package com.codegym.finalModule.controller.admin;

import com.codegym.finalModule.DTO.product.ProductDTO;
import com.codegym.finalModule.mapper.product.ProductMapper;
import com.codegym.finalModule.model.Product;
import com.codegym.finalModule.model.ProductDetail;
import com.codegym.finalModule.model.ProductImage;
import com.codegym.finalModule.service.common.CloudinaryService;
import com.codegym.finalModule.service.impl.BrandService;
import com.codegym.finalModule.service.impl.CategoryService;
import com.codegym.finalModule.service.impl.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
    private ProductMapper productMapper;
    @Autowired
    private CloudinaryService cloudinaryService;

    @GetMapping
    public String showListProduct(
            @RequestParam(name = "keyword", required = false) String keyword,
            @RequestParam(name = "minPrice", required = false) Double minPrice,
            @RequestParam(name = "maxPrice", required = false) Double maxPrice,
            Model model) {

        List<Product> products = productService.searchProducts(keyword, minPrice, maxPrice);
        DecimalFormat decimalFormat = new DecimalFormat("#,### VND");
        for (Product product : products) {
            product.setFormattedPrice(decimalFormat.format(product.getPrice()));
        }

        model.addAttribute("products", products);
        model.addAttribute("keyword", keyword);
        model.addAttribute("minPrice", minPrice);
//        model.addAttribute("categories", categoryService.getAllCategories());
//        model.addAttribute("brand", brandService.getAllBrands());
//
//        model.addAttribute("product", new Product());
////        model.addAttribute("productDetail", new ProductDetail());

        return "admin/product/listProduct";
    }

    @GetMapping("/edit/{id}")
    public String editProductForm(@PathVariable Integer id, Model model) {
        Optional<Product> product = productService.getProductById(id);
        if (product.isPresent()) {
            model.addAttribute("product", product.get());
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
        productService.saveProduct(product);
        return "redirect:/Admin/product-manager?success=ProductUpdated";
    }

    @GetMapping("/add")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new ProductDTO());
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("brands", brandService.getAllBrands());
        return "admin/product/addProduct"; // Giao diện thêm sản phẩm
    }

    // 🔹 Xử lý khi người dùng thêm sản phẩm
    @PostMapping("/add")
    public String addProduct(
            @Valid @ModelAttribute("product") ProductDTO productDTO,
            BindingResult bindingResult,
            @RequestParam("files") List<MultipartFile> files,
            Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoryService.getAllCategories());
            model.addAttribute("brands", brandService.getAllBrands());
            return "admin/product/addProduct";
        }

        //  Kiểm tra ảnh có được tải lên không
        if (files == null || files.isEmpty()) {
            bindingResult.rejectValue("mainImageUrl", "error.product", "Vui lòng chọn ít nhất một ảnh!");
            model.addAttribute("categories", categoryService.getAllCategories());
            model.addAttribute("brands", brandService.getAllBrands());
            return "admin/product/addProduct";
        }

        // Chuyển đổi từ DTO sang Entity
        Product product = productMapper.toEntity(productDTO);

        product.getProductDetail().setProduct(product);

        //  Lưu sản phẩm vào database
        productService.saveProductWithDetailsAndImages(product, product.getProductDetail(), files);

        return "redirect:/Admin/product-manager"; // Chuyển hướng khi thêm thành công
    }


}

