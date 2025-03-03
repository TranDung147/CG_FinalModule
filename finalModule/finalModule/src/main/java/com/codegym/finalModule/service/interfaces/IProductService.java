package com.codegym.finalModule.service.interfaces;

import com.codegym.finalModule.DTO.product.ProductDTO;
import com.codegym.finalModule.model.Product;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    List<Product> getAllProducts();
    Optional<Product> getProductById(Integer productID);
    Product saveProduct(Product product);
    List<ProductDTO> getProductsDTOByKeyword(String keyword);
    Product findById(Integer id);
}
