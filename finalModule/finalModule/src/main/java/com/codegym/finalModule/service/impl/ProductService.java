package com.codegym.finalModule.service.impl;

import com.codegym.finalModule.DTO.product.ProductDTO;
import com.codegym.finalModule.model.Product;
import com.codegym.finalModule.repository.IProductRepository;
import com.codegym.finalModule.service.interfaces.IProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    public Product saveProduct(Product product) {
        if (product.getProductID() == null) {
            product.setCreateAt(LocalDateTime.now());
        }
        product.setUpdateAt(LocalDateTime.now());
        return productRepository.save(product);
    }

    public List<Product> searchProducts(String searchType, String keyword, Double minPrice, Double maxPrice) {
        if (keyword == null || keyword.trim().isEmpty()) {
            keyword = "";
        }
        if (minPrice == null) {
            minPrice = 0.0;
        }
        if (maxPrice == null) {
            maxPrice = Double.MAX_VALUE;
        }

        if ("category".equalsIgnoreCase(searchType)) {
            return productRepository.findByCategory_NameContainingIgnoreCaseAndPriceBetween(keyword, minPrice, maxPrice);
        } else {
            return productRepository.findByNameContainingIgnoreCaseAndPriceBetween(keyword, minPrice, maxPrice);
        }
    }

    @Override
    public List<ProductDTO> getProductsDTOByKeyword(String keyword) {

        List<ProductDTO> productDTOS = new ArrayList<>();
        productDTOS.add(new ProductDTO(1, "Sản phẩm s1", 1000000.0));
        productDTOS.add(new ProductDTO(2, "Sản phẩm s2", 2000000.0));
        productDTOS.add(new ProductDTO(3, "Sản phẩm s3", 3000000.0));
        productDTOS.add(new ProductDTO(4, "Sản phẩm s4", 4000000.0));
        return productDTOS;

    }

    public Product findById(Integer id) {
        return productRepository.findById(id).orElse(null);
    }


}
