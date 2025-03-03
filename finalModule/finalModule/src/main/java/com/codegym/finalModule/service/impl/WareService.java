package com.codegym.finalModule.service.impl;

import com.codegym.finalModule.model.WareHouse;
import com.codegym.finalModule.repository.IWareHouseRepository;
import com.codegym.finalModule.service.interfaces.IWareHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WareService implements IWareHouseService {
    @Autowired
    private IWareHouseRepository wareHouseRepository;
    @Override
    public List<WareHouse> getAllWareHouses() {
        return wareHouseRepository.findAll();
    }

@Override
    public Page<WareHouse> searchWareHouse(String keyword, String statusStock, Integer pageNo) {
        Pageable pageable = PageRequest.of(pageNo - 1, 5);
        return wareHouseRepository.searchByProductOrSupplierAndStatus(keyword, statusStock, pageable);
    }

    @Override
    public Page<WareHouse> findAll(Integer pageNo) {
        Pageable pageable = PageRequest.of(pageNo - 1, 5);
        return wareHouseRepository.findAll(pageable);
    }

    @Override
    public Page<WareHouse> findByKeyword(String keyword, String statusStock, Integer pageNo) {
        return searchWareHouse(keyword, statusStock, pageNo);
    }
    @Override
    public void save(WareHouse wareHouse) {
        wareHouseRepository.save(wareHouse);
    }
}

