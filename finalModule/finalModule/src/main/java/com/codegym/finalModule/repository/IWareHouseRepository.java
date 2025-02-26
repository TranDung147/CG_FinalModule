package com.codegym.finalModule.repository;

import com.codegym.finalModule.model.WareHouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IWareHouseRepository extends JpaRepository<WareHouse, Integer> {
}
