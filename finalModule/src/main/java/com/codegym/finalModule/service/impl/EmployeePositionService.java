package com.codegym.finalModule.service.impl;


import com.codegym.finalModule.model.EmployeePosition;
import com.codegym.finalModule.repository.IEmployeePositionRepository;
import com.codegym.finalModule.service.interfaces.IEmployeePositionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeePositionService implements IEmployeePositionService <EmployeePosition> {
    private final IEmployeePositionRepository employeePositionRepository;
    public EmployeePositionService(IEmployeePositionRepository employeePositionRepository) {
        this.employeePositionRepository = employeePositionRepository;
    }
    @Override
    public List<EmployeePosition> getEmployeePositions() {
        return this.employeePositionRepository.findAll();
    }
}
