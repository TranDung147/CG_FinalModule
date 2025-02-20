package com.codegym.finalModule.service.impl;

import com.codegym.finalModule.DTO.employee.EmployeeDTO;
import com.codegym.finalModule.mapper.employee.EmployeeMapper;
import com.codegym.finalModule.model.Employee;
import com.codegym.finalModule.repository.IEmployeeRepository;
import com.codegym.finalModule.repository.IUserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import com.codegym.finalModule.service.interfaces.IEmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService implements IEmployeeService {

    private final IEmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final IUserRepository iUserRepository;
    public EmployeeService(IEmployeeRepository employeeRepository ,
                           EmployeeMapper employeeMapper ,
                           IUserRepository iUserRepository) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
        this.iUserRepository = iUserRepository;
    }

    @Override
    public List<Employee> findByIds(List<Integer> employeeIds) {
        return employeeRepository.findAllById(employeeIds);
    }

    @Override
    public List<String> getEmployeeNamesByIds(List<Integer> employeeIds) {
        return employeeRepository.findAllById(employeeIds).stream().map(Employee::getEmployeeName).toList();
    }

    @Override
    public void saveAll(List<Employee> employees) {
        employeeRepository.saveAll(employees);
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public List<Employee> findByKeyword(String keyword) {
        return employeeRepository.searchByKeywordAndType(keyword, "all", PageRequest.of(0, Integer.MAX_VALUE)).getContent();
    }

    @Override
    public Page<Employee> findAll(Integer pageNo) {
        Pageable pageable = PageRequest.of(pageNo - 1, 5);
        return employeeRepository.findAll(pageable);
    }

    @Override
    public Page<Employee> findByKeyword(String keyword, Integer pageNo) {
        Pageable pageable = PageRequest.of(pageNo - 1, 5);
        return employeeRepository.searchByKeywordAndType(keyword, "all", pageable);
    }

    @Override
    public Page<Employee> searchUsers(String keyword, String type, Integer pageNo) {
        Pageable pageable = PageRequest.of(pageNo - 1, 5);
        return employeeRepository.searchByKeywordAndType(keyword, type, pageable);

    }
    @Override
    public void save(EmployeeDTO employeeDTO) {
        if (this.employeeRepository.existsByEmployeePhone(employeeDTO.getEmployeePhone())) {
            throw new EntityNotFoundException("Employee Phone already exists");
        }
        if (this.iUserRepository.existsByEmail(employeeDTO.getEmail())) {
            throw new EntityNotFoundException("Email already exists");
        }
        if (this.iUserRepository.existsByUsername(employeeDTO.getUsername())) {
            throw new EntityNotFoundException("Username already exists");
        }
        Employee employee = this.employeeMapper.convertToEmployee(employeeDTO) ;
        this.employeeRepository.save(employee);

    }

    @Override
    public void update(EmployeeDTO employeeDTO) {
        Employee employee = this.employeeRepository.findById(employeeDTO.getEmployeeId())
                .orElseThrow(() -> new EntityNotFoundException("Employee not found"));
        employee.setEmployeeName(employeeDTO.getEmployeeName());
        employee.setEmployeePhone(employeeDTO.getEmployeePhone());
        employee.setEmployeeAddress(employeeDTO.getEmployeeAddress());
        employee.setEmployeeBirthday(employeeDTO.getEmployeeBirthday());
//        employee.setEmployeeWork(employeeDTO.getEmployeeWork());
        employee = this.employeeMapper.convertToEmployee(employeeDTO);

        this.employeeRepository.save(employee);
    }

    @Override
    public EmployeeDTO findDTOById(int id) {
        Employee employee = this.employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found"));
        return this.employeeMapper.convertToEmployeeDTO(employee);
    }
}

