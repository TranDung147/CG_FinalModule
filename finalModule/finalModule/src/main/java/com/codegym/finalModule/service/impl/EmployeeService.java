package com.codegym.finalModule.service.impl;

import com.codegym.finalModule.DTO.employee.EmployeeDTO;
import com.codegym.finalModule.mapper.employee.EmployeeMapper;
import com.codegym.finalModule.mapper.user.EmployeeToUserMapper;
import com.codegym.finalModule.model.Employee;
import com.codegym.finalModule.model.EmployeePosition;
import com.codegym.finalModule.model.User;
import com.codegym.finalModule.repository.IEmployeePositionRepository;
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
    private final EmployeeToUserMapper employeeToUserMapper;
    private final IEmployeePositionRepository employeePositionRepository;
    public EmployeeService(IEmployeeRepository employeeRepository ,
                           EmployeeMapper employeeMapper ,
                           IUserRepository iUserRepository ,
                           EmployeeToUserMapper employeeToUserMapper ,
                           IEmployeePositionRepository employeePositionRepository) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
        this.iUserRepository = iUserRepository;
        this.employeeToUserMapper = employeeToUserMapper;
        this.employeePositionRepository = employeePositionRepository;
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
    public Page<Employee> searchByFieldAndKeyword(String field, String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        if ("work".equals(field)) {
            return this.employeeRepository.findByEmployeePosition_PositionNameContaining(keyword, pageable);
        }
        return this.employeeRepository.searchEmployees(field, keyword, pageable);
    }

    @Override
    public Page<Employee> findAll(int page, int size) {
        return this.employeeRepository.findAll(PageRequest.of(page - 1, size));
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

        User user = this.employeeToUserMapper.convertEmployeeToUser(employeeDTO);
        this.iUserRepository.save(user);
        EmployeePosition employeePosition =
                this.employeePositionRepository.findById(employeeDTO.getEmployeePosition()).orElseThrow(null);
        Employee employee = this.employeeMapper.convertToEmployee(employeeDTO) ;
        employee.setUser(user);
        employee.setEmployeePosition(employeePosition);
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

