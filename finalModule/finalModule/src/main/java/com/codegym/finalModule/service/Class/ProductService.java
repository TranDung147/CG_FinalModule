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
}
