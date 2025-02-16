package com.codegym.finalModule.service.Class;

import com.codegym.finalModule.model.Product;
import com.codegym.finalModule.repository.IProductRepository;
import com.codegym.finalModule.service.Interface.IProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public List<Product> searchProducts(String keyword, Double minPrice, Double maxPrice) {
        if ((keyword == null || keyword.trim().isEmpty()) && minPrice == null && maxPrice == null) {
            return productRepository.findAll();
        }
        return productRepository.findByNameContainingAndPriceBetween(
                keyword == null ? "" : keyword,
                minPrice != null ? minPrice : 0.0,
                maxPrice != null ? maxPrice : Double.MAX_VALUE
        );
    }
}
