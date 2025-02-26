package com.codegym.finalModule.service.interfaces;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IUserService <T> {
    T findById(int id);
    T save(T t);
    T update(T t , int id);
    void delete(int id);
}
