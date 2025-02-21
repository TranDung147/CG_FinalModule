package com.codegym.finalModule.mapper.employee;
import com.codegym.finalModule.DTO.employee.EmployeeDTO;
import com.codegym.finalModule.model.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    public Employee convertToEmployee(EmployeeDTO employeeDTO) {
        return Employee.builder()
                .employeeName(employeeDTO.getEmployeeName())
                .employeeAddress(employeeDTO.getEmployeeAddress())
                .employeePhone(employeeDTO.getEmployeePhone())
                .employeeBirthday(employeeDTO.getEmployeeBirthday())
                .employeeWork(employeeDTO.getEmployeeWork())
                .build();
    }

    public EmployeeDTO convertToEmployeeDTO(Employee employee) {
        return EmployeeDTO.builder()
                .employeeId(employee.getEmployeeId())
                .employeeName(employee.getEmployeeName())
                .employeeAddress(employee.getEmployeeAddress())
                .employeePhone(employee.getEmployeePhone())
                .employeeBirthday(employee.getEmployeeBirthday())
                .employeeWork(employee.getEmployeeWork())
                .build();
    }
}
