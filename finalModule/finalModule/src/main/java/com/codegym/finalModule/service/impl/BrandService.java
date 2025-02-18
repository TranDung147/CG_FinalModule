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
    public Brand saveBrand(Brand brand) {
        if (brand.getBrandID() == null) {
            brand.setCreateAt(LocalDateTime.now());
        }
        brand.setUpdateAt(LocalDateTime.now());
        return brandRepository.save(brand);
    }

    public List<Brand> findByNameContaining(String keyword) {
        return brandRepository.findByNameContainingIgnoreCase(keyword);
    }
}
