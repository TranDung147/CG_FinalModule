package com.codegym.finalModule.service.interfaces;

import com.codegym.finalModule.model.WareHouse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IWareHouseService {
    List<WareHouse> getAllWareHouses();
    Page<WareHouse> searchWareHouse(String keyword, String statusStock, Integer pageNo);
    Page<WareHouse> findAll(Integer pageNo);
    Page<WareHouse> findByKeyword(String keyword, String statusStock, Integer pageNo ) ;
    void save(WareHouse wareHouse);

}
