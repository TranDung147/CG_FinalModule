package com.codegym.finalModule.controller.admin;

import com.codegym.finalModule.model.Product;
import com.codegym.finalModule.model.ProductDetail;
import com.codegym.finalModule.model.ProductImage;
import com.codegym.finalModule.service.common.CloudinaryService;
import com.codegym.finalModule.service.impl.BrandService;
import com.codegym.finalModule.service.impl.CategoryService;
import com.codegym.finalModule.service.impl.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
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
    private CloudinaryService cloudinaryService;

    @GetMapping
    public String showListProduct(
            @RequestParam(name = "keyword", required = false) String keyword,
            @RequestParam(name = "minPrice", required = false) Double minPrice,
            @RequestParam(name = "maxPrice", required = false) Double maxPrice,
            @RequestParam(name = "category", required = false) Integer categoryId,
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "size", required = false, defaultValue = "3") int size,
            Model model) {

        Page<Product> productPage = productService.searchProducts(keyword, minPrice, maxPrice, categoryId, page - 1, size);
        List<Product> products = productPage.getContent();
        DecimalFormat decimalFormat = new DecimalFormat("#,### VND");

        for (Product product : products) {
            try {
                Double priceValue = (product.getPrice() != null) ? product.getPrice() : 0.0;
                product.setFormattedPrice(decimalFormat.format(priceValue));
            } catch (IllegalArgumentException e) {
                product.setFormattedPrice("0 VND");
            }
        }

        model.addAttribute("products", products);
        model.addAttribute("productPage", productPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", productPage.getTotalPages());
        model.addAttribute("keyword", keyword);
        model.addAttribute("minPrice", minPrice);
        model.addAttribute("maxPrice", maxPrice);
        model.addAttribute("category", categoryId);
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("brands", brandService.getAllBrands());

        model.addAttribute("product", new Product());
        model.addAttribute("productDetail", new ProductDetail());
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


    @PostMapping("/add")
    public String addProduct(@ModelAttribute("product") Product product,
                             @ModelAttribute("productDetail") ProductDetail productDetail,
                             @RequestParam("files") List<MultipartFile> files) {
        try {
            // Kiểm tra nếu không có ảnh nào được tải lên
            if (files == null || files.isEmpty()) {
                throw new RuntimeException("Vui lòng chọn ít nhất một ảnh!");
            }

            // Danh sách ảnh sẽ lưu vào database
            List<ProductImage> productImages = new ArrayList<>();
            for (MultipartFile file : files) {
                if (!file.isEmpty()) {
                    // Upload ảnh lên Cloudinary và lấy URL ảnh
                    String imageUrl = cloudinaryService.uploadFileToCloudinary(file);

                    // Tạo đối tượng ProductImage
                    ProductImage productImage = new ProductImage();
                    productImage.setImageURL(imageUrl);
                    productImage.setProduct(product);

                    productImages.add(productImage);
                }
            }
            // Thiết lập ngày tạo sản phẩm
            product.setCreateAt(LocalDateTime.now());
            product.setUpdateAt(LocalDateTime.now());

            // Liên kết sản phẩm với chi tiết sản phẩm
            productDetail.setProduct(product);
            product.setProductDetail(productDetail);

            // Liên kết sản phẩm với danh sách ảnh
            product.setProductImages(productImages);
            product.setProductImages(productImages);
            productService.saveProduct(product);


        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/Admin/product-manager";
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteProducts(@RequestBody List<Integer> productIds) {
        try {
            productService.deleteProduct(productIds);
            return ResponseEntity.ok().body("{\"success\": true, \"message\": \"Sản phẩm đã được xóa thành công!\"}");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("{\"success\": false, \"message\": \"Lỗi khi xóa sản phẩm!\"}");
        }
    }
}

