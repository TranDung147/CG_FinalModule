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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
            @RequestParam(name = "message", required = false) String message,
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

        // Thêm thông báo nếu có
        if (message != null) {
            model.addAttribute("message", message);
        }

        // Thêm thông báo khi không tìm thấy sản phẩm
        if (products.isEmpty() && (keyword != null || minPrice != null || maxPrice != null || categoryId != null)) {
            model.addAttribute("emptyMessage", "Không tìm thấy sản phẩm phù hợp với dữ liệu tìm kiếm!");
        }

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
            return "redirect:/Admin/product-manager?message=Không tìm thấy sản phẩm!";
        }
    }

    @PostMapping("/edit")
    public String editProduct(@Valid @ModelAttribute("product") Product product, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("categories", categoryService.getAllCategories());
            model.addAttribute("brands", brandService.getAllBrands());
            return "admin/product/editProduct";
        }
        productService.saveProduct(product);
        redirectAttributes.addAttribute("message", "Cập nhật sản phẩm thành công!");
        return "redirect:/Admin/product-manager";
    }


    @PostMapping("/add")
    public String addProduct(@ModelAttribute("product") Product product,
                             @ModelAttribute("productDetail") ProductDetail productDetail,
                             @RequestParam("files") List<MultipartFile> files,
                             RedirectAttributes redirectAttributes) {
        try {
            // Kiểm tra nếu không có ảnh nào được tải lên
            if (files == null || files.isEmpty() || files.get(0).isEmpty()) {
                redirectAttributes.addAttribute("message", "Vui lòng chọn ít nhất một ảnh!");
                return "redirect:/Admin/product-manager";
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
            productService.saveProduct(product);

            redirectAttributes.addAttribute("message", "Thêm sản phẩm thành công!");

        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addAttribute("message", "Lỗi: " + e.getMessage());
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


