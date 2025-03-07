package com.codegym.finalModule.service.interfaces;

import com.codegym.finalModule.model.WareHouse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IWareHouseService <T> {
   Page<T> searchWareHouses(String field , String keyword , Integer statusStock ,int page , int size);
   List<WareHouse> getWareHouses();
}
