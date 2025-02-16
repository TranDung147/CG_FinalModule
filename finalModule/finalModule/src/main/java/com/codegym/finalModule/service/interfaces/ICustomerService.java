package com.codegym.finalModule.service.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ICustomerService <E,D>{
    Page<E> getAllCustomers(int page, int size);
    Page<E> searchByFieldAndKey(String field, String keyword , int page, int size);
    E getCustomerById(int id);
    void saveCustomer(D d);
    void deleteCustomer(int id);
    void updateCustomer(D d , int id);
}
