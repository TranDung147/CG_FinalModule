package com.codegym.finalModule.service.interfaces;


import java.util.List;


public interface IEmployeePositionService <T>{
    List<T> getEmployeePositions();
    void deleteById(Integer id);
}
