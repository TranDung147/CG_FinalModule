package com.codegym.finalModule.service.interfaces;

import com.codegym.finalModule.dto.employee.EmployeeDTO;
import com.codegym.finalModule.model.Employee;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IEmployeeService {
    //Delete employee
    List<Employee> findByIds(List<Integer> employeeIds);
    List<String> getEmployeeNamesByIds(List<Integer> employeeIds);
    void saveAll(List<Employee> employees);
    //End delete employee
    List<Employee> findAll();
    List<Employee> findByKeyword(String keyword);
    Page<Employee> findAll(Integer pageNo);
    Page<Employee> findByKeyword(String keyword, Integer pageNo);
    Page<Employee> searchUsers(String keyword, String type, Integer pageNo);
    void save(EmployeeDTO employeeDTO);
    void update( EmployeeDTO employeeDTO);
    EmployeeDTO findDTOById(int id);
}
