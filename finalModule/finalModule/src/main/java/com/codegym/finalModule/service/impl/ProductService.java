package com.codegym.finalModule.service.impl;

import com.codegym.finalModule.model.Product;
import com.codegym.finalModule.model.ProductDetail;
import com.codegym.finalModule.model.ProductImage;
import com.codegym.finalModule.repository.IProductRepository;
import com.codegym.finalModule.repository.ProductDetailRepository;
import com.codegym.finalModule.repository.ProductImageRepository;
import com.codegym.finalModule.service.common.CloudinaryService;
import com.codegym.finalModule.service.interfaces.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService implements IProductService {

    private final IProductRepository productRepository;
    private final ProductDetailRepository productDetailRepository;
    @Autowired
    private ProductImageRepository productImageRepository;


    public ProductService(IProductRepository productRepository, ProductImageRepository productImageRepository, CloudinaryService cloudinaryService, ProductImageRepository productImageRepository1, ProductDetailRepository productDetailRepository) {

        this.productRepository = productRepository;

        this.productImageRepository = productImageRepository;
        this.productDetailRepository = productDetailRepository;
    }

    @Override
    @Transactional
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getProductById(Integer productID) {

        return productRepository.findById(productID);
    }

    @Override
    @Transactional
    public Product saveProduct(Product product ) {
         return  productRepository.save(product);
    }

    @Override
    @Transactional
    public ProductDetail saveProductDetail(ProductDetail productDetail) {
        return productDetailRepository.save(productDetail);
    }


    @Override
    @Transactional
    public void saveProductWithImages(Product product, List<ProductImage> productImages) {
        if (product.getProductID() == null) {
            throw new RuntimeException(" Lỗi: Sản phẩm chưa được lưu!");
        }

        if (!productImages.isEmpty()) {
            for (ProductImage img : productImages) {
                img.setProduct(product); // ✅ Đảm bảo mỗi ảnh có ProductID hợp lệ
            }
            productImageRepository.saveAll(productImages);
        }
    }



    public List<Product> searchProducts(String keyword, Double minPrice, Double maxPrice) {
        if (keyword == null || keyword.trim().isEmpty()) {
            keyword = ""; // Không null, nhưng cũng không để trống hoàn toàn
        }
        if (minPrice == null) {
            minPrice = 0.0;
        }
        if (maxPrice == null) {
            maxPrice = Double.MAX_VALUE;
        }
        return productRepository.findByNameContainingIgnoreCaseAndPriceBetween(keyword, minPrice, maxPrice);
    }


}
