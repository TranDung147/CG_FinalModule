package com.codegym.finalModule.service.interfaces;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ICustomerService <E,D>{
    List<E> getAll();
    E getById(int id);
    void delete(int id);
    void update(D d , int id);
}
