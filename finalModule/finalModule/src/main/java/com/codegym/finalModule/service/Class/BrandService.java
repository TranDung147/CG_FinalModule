package com.codegym.finalModule.service.Class;

import com.codegym.finalModule.model.Brand;
import com.codegym.finalModule.repository.IBrandRepository;
import com.codegym.finalModule.service.Interface.IBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BrandService implements IBrandService {

    @Autowired
    private IBrandRepository brandRepository;

    @Override
    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }
}
