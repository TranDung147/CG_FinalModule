package com.codegym.finalModule.service.interfaces;

import com.codegym.finalModule.DTO.employee.EmployeeDTO;
import com.codegym.finalModule.model.Employee;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IEmployeeService {
    //Delete employee
    List<Employee> findByIds(List<Integer> employeeIds);
    List<String> getEmployeeNamesByIds(List<Integer> employeeIds);
    void saveAll(List<Employee> employees);
    Page<Employee> searchByFieldAndKeyword(String field, String keyword, int page, int size);
    Page<Employee> findAll(int page, int size);
    void save(EmployeeDTO employeeDTO);
    void update( EmployeeDTO employeeDTO);
    EmployeeDTO findDTOById(int id);
}
