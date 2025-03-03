package com.codegym.finalModule.service.impl;

import com.codegym.finalModule.model.WareHouse;
import com.codegym.finalModule.repository.IWareHouseRepository;
import com.codegym.finalModule.service.interfaces.IWareHouseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class WareHouseService implements IWareHouseService <WareHouse> {

    private final IWareHouseRepository wareHouseRepository;
    public WareHouseService(IWareHouseRepository wareHouseRepository) {
        this.wareHouseRepository = wareHouseRepository;
    }
    @Override
    public Page<WareHouse> searchByFieldAndKey(String field, String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return this.wareHouseRepository.searchWareHouses(field, keyword, pageable);
    }

    @Override
    public Page<WareHouse> getAllWareHouses(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return this.wareHouseRepository.findAll(pageable);
    }

    @Override
    public WareHouse getWareHouseById(int id) {
        return this.wareHouseRepository.findById(id).orElseThrow(null);
    }
}
