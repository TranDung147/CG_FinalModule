package com.codegym.finalModule.mapper.employee;

import com.codegym.finalModule.DTO.employee.EmployeeDTO;
import com.codegym.finalModule.enums.RoleEnums;
import com.codegym.finalModule.model.Employee;
import com.codegym.finalModule.model.EmployeePosition;
import com.codegym.finalModule.model.Role;
import com.codegym.finalModule.model.User;
import com.codegym.finalModule.repository.IEmployeePositionRepository;
import com.codegym.finalModule.repository.IRoleRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeMapper {

    public Employee convertToEmployee(EmployeeDTO employeeDTO) {

        return Employee.builder()
                .employeeName(employeeDTO.getEmployeeName())
                .employeeAddress(employeeDTO.getEmployeeAddress())
                .employeePhone(employeeDTO.getEmployeePhone())
                .employeeBirthday(employeeDTO.getEmployeeBirthday())
                .isDisabled(false)
                .build();
    }
    public Employee convertToEmployeeHasId(EmployeeDTO employeeDTO) {

        return Employee.builder()
                .employeeId(employeeDTO.getEmployeeId())
                .employeeName(employeeDTO.getEmployeeName())
                .employeeAddress(employeeDTO.getEmployeeAddress())
                .employeePhone(employeeDTO.getEmployeePhone())
                .employeeBirthday(employeeDTO.getEmployeeBirthday())

                .isDisabled(false)
                .build();
    }

    public EmployeeDTO convertToEmployeeDTO(Employee employee) {
        return EmployeeDTO.builder()
                .employeeId(employee.getEmployeeId())
                .employeeName(employee.getEmployeeName())
                .employeeAddress(employee.getEmployeeAddress())
                .employeePhone(employee.getEmployeePhone())
                .employeeBirthday(employee.getEmployeeBirthday())
                .email(employee.getUser().getEmail())
                .employeePosition(employee.getEmployeePosition().getPositionId())

                .username(employee.getUser().getUsername())
                .password(employee.getUser().getEncrytedPassword())
                .userId(employee.getUser().getUserId())
                .isResetPassword(false)


//                .employeeWork(employee.getEmployeeWork())
                .build();
    }
}
