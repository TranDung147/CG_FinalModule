package com.codegym.finalModule.service.Class;

import com.codegym.finalModule.repository.IEmployeeRepository;
import com.codegym.finalModule.service.Interface.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
