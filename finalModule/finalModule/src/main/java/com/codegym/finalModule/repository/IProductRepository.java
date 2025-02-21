package com.codegym.finalModule.repository;

import com.codegym.finalModule.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IProductRepository extends JpaRepository<Product ,Integer> {
    List<Product> findByNameContainingIgnoreCaseAndPriceBetween(String name, Double minPrice, Double maxPrice);
    List<Product> findByCategory_NameContainingIgnoreCaseAndPriceBetween(String categoryName, Double minPrice, Double maxPrice);

}
