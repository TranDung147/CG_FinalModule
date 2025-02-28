package com.codegym.finalModule.service.impl;

import com.codegym.finalModule.model.Brand;
import com.codegym.finalModule.repository.IBrandRepository;
import com.codegym.finalModule.service.interfaces.IBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BrandService implements IBrandService {

    @Autowired
    private IBrandRepository brandRepository;

    @Override
    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    @Override
    public Optional<Brand> getBrandById(Integer brandID) {
        return brandRepository.findById(brandID);
    }

    @Override
    public void saveBrand(Brand brand) {
        if (brand.getBrandID() != null&& brandRepository.existsById(brand.getBrandID())) {
            Brand existingBrand = brandRepository.findById(brand.getBrandID()).orElse(null);
            if (existingBrand != null) {
                existingBrand.setName(brand.getName());
                existingBrand.setCountry(brand.getCountry());
                existingBrand.setUpdateAt(LocalDateTime.now());
                brandRepository.save(existingBrand);
                brand.setCreateAt(existingBrand.getCreateAt());
            }
        } else {
            brand.setCreateAt(LocalDateTime.now());
            brand.setUpdateAt(LocalDateTime.now());
            brandRepository.save(brand);
        }
    }

    @Override
    public void deleteBrand(List<Integer> brandIds) {
        brandRepository.deleteAllById(brandIds);
    }


    public List<Brand> findByNameContaining(String keyword) {
        return brandRepository.findByNameContainingIgnoreCase(keyword);
    }
}
