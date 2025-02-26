package com.codegym.finalModule.repository;

import com.codegym.finalModule.model.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDetailRepository  extends JpaRepository<ProductDetail, Integer> {

}
