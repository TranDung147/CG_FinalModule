package com.codegym.finalModule.service.interfaces;


import org.springframework.data.domain.Page;

public interface ISupplierService <T>{
    Page<T> getSuppliers(int page, int size);
}
