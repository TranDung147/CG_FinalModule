package com.codegym.finalModule.mapper.employee;

import com.codegym.finalModule.DTO.employee.EmployeeDTO;
import com.codegym.finalModule.model.Employee;
import com.codegym.finalModule.model.EmployeePosition;
import com.codegym.finalModule.model.User;
import com.codegym.finalModule.repository.IEmployeePositionRepository;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {
    private final IEmployeePositionRepository employeePositionRepository;
    public EmployeeMapper(IEmployeePositionRepository employeePositionRepository) {
        this.employeePositionRepository = employeePositionRepository;
    }
    public Employee convertToEmployee(EmployeeDTO employeeDTO) {
        User user = User.builder()
                .email(employeeDTO.getEmail())
                .username(employeeDTO.getUsername())
                .password(employeeDTO.getPassword())
                .build();
        EmployeePosition employeePosition =
                this.employeePositionRepository.findById(employeeDTO.getEmployeePosition()).orElseThrow(null);
        return Employee.builder()
                .employeeName(employeeDTO.getEmployeeName())
                .employeeAddress(employeeDTO.getEmployeeAddress())
                .employeePhone(employeeDTO.getEmployeePhone())
                .employeeBirthday(employeeDTO.getEmployeeBirthday())
//                .employeePosition(employeePosition)
                .isDisabled(true)
                .user(user)
                .build();
    }

    public EmployeeDTO convertToEmployeeDTO(Employee employee) {
        return EmployeeDTO.builder()
                .employeeId(employee.getEmployeeId())
                .employeeName(employee.getEmployeeName())
                .employeeAddress(employee.getEmployeeAddress())
                .employeePhone(employee.getEmployeePhone())
                .employeeBirthday(employee.getEmployeeBirthday())
//                .employeeWork(employee.getEmployeeWork())
                .build();
    }
}
