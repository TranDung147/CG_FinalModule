package com.codegym.finalModule.service.Interface;

import com.codegym.finalModule.model.Product;

import java.util.List;

public interface IProductService {
    List<Product> getAllProducts();
    void saveProduct(Product product);
}
