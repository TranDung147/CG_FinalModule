package com.codegym.finalModule.service.interfaces;

import com.codegym.finalModule.model.Brand;
import com.codegym.finalModule.model.Employee;

import java.util.List;
import java.util.Optional;

public interface IBrandService {
    List<Brand> getAllBrands();
    Optional<Brand> getBrandById(Integer brandID);
    void saveBrand(Brand brand);
    void deleteBrand(List<Integer> brandIds);
    boolean existsByName(String name);
}
