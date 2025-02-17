package com.codegym.finalModule.mapper.employee;

import com.codegym.finalModule.dto.EmployeeDTO;
import com.codegym.finalModule.model.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    public Employee convertToEmployee(EmployeeDTO employeeDTO) {
        return Employee.builder()
                .employeeName(employeeDTO.getEmployeeName())
                .employeeAddress(employeeDTO.getEmployeeAddress())
                .employeePhone(employeeDTO.getEmployeePhone())
                .employeeEmail(employeeDTO.getEmployeeEmail())
                .employeeBirthday(employeeDTO.getEmployeeBirthday())
                .employeeWork(employeeDTO.getEmployeeWork())
                .build();
    }
}
