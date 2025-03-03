package com.codegym.finalModule.service.interfaces;

import com.codegym.finalModule.model.WareHouse;
import org.springframework.data.domain.Page;

public interface IWareHouseService <T>{
    Page<T> searchByFieldAndKey(String field, String keyword , int page, int size) ;
    Page<T> getAllWareHouses (int page, int size) ;
    WareHouse getWareHouseById(int id) ;
}
