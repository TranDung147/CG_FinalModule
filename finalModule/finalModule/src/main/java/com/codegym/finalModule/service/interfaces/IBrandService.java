package com.codegym.finalModule.service.interfaces;

import com.codegym.finalModule.model.Brand;
import java.util.List;
import java.util.Optional;

public interface IBrandService {
    List<Brand> getAllBrands();
    Optional<Brand> getBrandById(Integer brandID);
    Brand saveBrand(Brand brand);
}
