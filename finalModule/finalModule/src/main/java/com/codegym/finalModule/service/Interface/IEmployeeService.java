package com.codegym.finalModule.service.Interface;

import com.codegym.finalModule.model.Employee;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IEmployeeService {
//    void disableEmployeesByID(List<Integer> employeeIds);
    List<String> getEmployeeNamesByIds(List<Integer> employeeIds);

    List<Employee> findAll();
    List<Employee> findByIds(List<Integer> employeeIds);
    void saveAll(List<Employee> employees);
    List<Employee> findByKeyword(String keyword);

    Page<Employee> findAll(Integer pageNo);
    Page<Employee> findByKeyword(String keyword, Integer pageNo);
    Page<Employee> searchUsers(String keyword, String type, Integer pageNo);
<<<<<<< Updated upstream
=======
    void save(EmployeeDTO employeeDTO);
    void update( EmployeeDTO employeeDTO);
    EmployeeDTO findDTOById(int id);
>>>>>>> Stashed changes
}
