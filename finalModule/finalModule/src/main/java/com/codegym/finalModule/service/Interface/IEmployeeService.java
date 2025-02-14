package com.codegym.finalModule.service.Interface;

import com.codegym.finalModule.model.Employee;

import java.util.List;

public interface IEmployeeService {
    void deleteEmployeeByID(List<Integer> employeeIds);
    List<String> getEmployeeNamesByIds(List<Integer> employeeIds);
}
