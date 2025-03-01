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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService implements IProductService {
    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private ProductImageRepository productImageRepository;
    @Autowired
    private CloudinaryService cloudinaryService;
    @Autowired
    private ProductDetailRepository productDetailRepository;


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
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    @Transactional
    public ProductDetail saveProductDetail(ProductDetail productDetail) {
        return productDetailRepository.save(productDetail);
    }

    @Override
    @Transactional
    public List<ProductImage> saveProductImages(List<ProductImage> productImages) {
        return productImageRepository.saveAll(productImages);
    }

    @Override
    @Transactional
    public void saveProductWithImages(Product product, List<ProductImage> productImages) {
        // Lưu sản phẩm trước
        Product savedProduct = productRepository.save(product);

        // Gán sản phẩm đã lưu cho từng ảnh
        if (productImages != null && !productImages.isEmpty()) {
            for (ProductImage image : productImages) {
                image.setProduct(savedProduct);
            }

            // Lưu danh sách ảnh
            productImageRepository.saveAll(productImages);

            // Nếu có ảnh, đặt ảnh đầu tiên làm ảnh chính
            if (!productImages.isEmpty()) {
                savedProduct.setMainImageUrl(productImages.get(0).getImageURL());
                productRepository.save(savedProduct);
            }
        }
    }


    @Override
    @Transactional
    public Product saveProductWithDetailsAndImages(Product product, ProductDetail productDetail, List<MultipartFile> files) {
        try {
            // ✅ 1. Cập nhật thời gian trước khi lưu
            LocalDateTime now = LocalDateTime.now();
            product.setCreateAt(now);
            product.setUpdateAt(now);

            // ✅ 2. Gán ProductDetail vào Product trước khi lưu
            if (productDetail != null) {
                productDetail.setProduct(product);
                product.setProductDetail(productDetail);
                productDetail.setCreateAt(now);
                productDetail.setUpdateAt(now);
            }

            // ✅ 3. Lưu sản phẩm trước để có ID
            product = productRepository.save(product);

            // ✅ 4. Lưu ProductDetail sau khi Product có ID
            if (productDetail != null) {
                productDetailRepository.save(productDetail);
            }

            // ✅ 5. Xử lý lưu ảnh sản phẩm nếu có ảnh
            String mainImageUrl = null;
            List<ProductImage> productImages = new ArrayList<>();

            if (files != null && !files.isEmpty()) {
                for (int i = 0; i < files.size(); i++) {
                    MultipartFile file = files.get(i);
                    if (!file.isEmpty()) {
                        String imageUrl = cloudinaryService.uploadFileToCloudinary(file);
                        if (i == 0) {
                            mainImageUrl = imageUrl; // ✅ Ảnh đầu tiên là ảnh chính
                        }
                        ProductImage productImage = new ProductImage();
                        productImage.setImageURL(imageUrl);
                        productImage.setProduct(product);
                        productImages.add(productImage);
                    }
                }
            }

            // ✅ 6. Lưu danh sách ảnh nếu có
            if (!productImages.isEmpty()) {
                productImageRepository.saveAll(productImages);
            }

            // ✅ 7. Cập nhật ảnh chính cho sản phẩm
            product.setMainImageUrl(mainImageUrl);
            productRepository.save(product);

            return product;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Lỗi khi lưu sản phẩm: " + e.getMessage());
        }
    }

    @Override
    public void deleteProduct(List<Integer> productIds) {
        productRepository.deleteAllById(productIds);
    }

    public Page<Product> searchProducts(String keyword, Double minPrice, Double maxPrice, Integer categoryId, int page, int size) {
        if (keyword != null && keyword.trim().isEmpty()) {
            keyword = null;  // Bỏ qua nếu từ khóa rỗng
        }
        if (minPrice == null || minPrice < 0) {
            minPrice = null;  // Không giới hạn minPrice
        }
        if (maxPrice == null || maxPrice < 0) {
            maxPrice = null;  // Không giới hạn maxPrice
        }
        if (categoryId == null || categoryId == 0) {
            categoryId = null; // Không lọc theo category nếu không chọn
        }
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.searchProducts(categoryId, keyword, minPrice, maxPrice, pageable);
    }
}