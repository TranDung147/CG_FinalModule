package com.codegym.finalModule.service.impl;

import com.codegym.finalModule.model.Product;
import com.codegym.finalModule.repository.IProductRepository;
import com.codegym.finalModule.service.interfaces.IProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService implements IProductService {

    private final IProductRepository productRepository;

    public ProductService(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getProductById(Integer productID) {
        return productRepository.findById(productID);
    }

    @Override
    public void saveProduct(Product product) {
        if (product.getProductID() == null) {
            product.setCreateAt(LocalDateTime.now());
        }
        product.setUpdateAt(LocalDateTime.now());
        productRepository.save(product);
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
