package com.codegym.finalModule.service.impl;

import com.codegym.finalModule.repository.IEmployeeRepository;
import com.codegym.finalModule.service.interfaces.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmployeeService implements IEmployeeService {

    @Autowired
    private IEmployeeRepository employeeRepository;

    public void deleteEmployeeByID(Integer employeeId) {
        if (!employeeRepository.existsById(employeeId)) {
            throw new RuntimeException("ID này không tồn tại!");
        }
        employeeRepository.deleteById(employeeId);
    }
}
