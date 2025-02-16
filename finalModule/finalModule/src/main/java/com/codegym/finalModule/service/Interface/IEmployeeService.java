package com.codegym.finalModule.service.Interface;

import com.codegym.finalModule.model.Employee;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IEmployeeService {
    void deleteEmployeeByID(List<Integer> employeeIds);
    List<String> getEmployeeNamesByIds(List<Integer> employeeIds);

    List<Employee> findAll();

    List<Employee> findByKeyword(String keyword);

    Page<Employee> findAll(Integer pageNo);
    Page<Employee> findByKeyword(String keyword, Integer pageNo);
    Page<Employee> searchUsers(String keyword, String type, Integer pageNo);
    void save(Employee employee);
    boolean existsByEmail(String email);
}
