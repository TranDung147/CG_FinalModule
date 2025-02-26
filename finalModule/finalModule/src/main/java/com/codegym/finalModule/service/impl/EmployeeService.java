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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import com.codegym.finalModule.service.interfaces.IEmployeeService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeService implements IEmployeeService {

    @Autowired
    PasswordEncoder passwordEncoder;

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


    @Transactional
    public boolean disableEmployees(List<Integer> employeeIds) {
        try {
            List<Employee> employees = employeeRepository.findAllById(employeeIds);
            for (Employee emp : employees) {
                emp.setIsDisabled(true);
            }
            employeeRepository.saveAll(employees);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Transactional
    public boolean activateEmployees(List<Integer> employeeIds) {
        try {
            List<Employee> employees = employeeRepository.findAllById(employeeIds);
            for (Employee emp : employees) {
                emp.setIsDisabled(false);
            }
            employeeRepository.saveAll(employees);
            return true;
        } catch (Exception e) {
            return false;
        }
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

        Boolean isResetPassword = employeeDTO.getIsResetPassword();
        User user = this.iUserRepository.findById(employeeDTO.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
//        user.setEmployee(null);

//         reset password
        if(isResetPassword){
            String defaultPassword = "123456";
            String encodedPassword = this.passwordEncoder.encode(defaultPassword);
            user.setEncrytedPassword(encodedPassword);
            this.iUserRepository.save(user);
        }

        user.setEmail(employeeDTO.getEmail());
        // update employee
        Employee employee = this.employeeMapper.convertToEmployeeHasId(employeeDTO);
        EmployeePosition employeePosition =
                this.employeePositionRepository.findById(employeeDTO.getEmployeePosition()).orElseThrow(null);
        employee.setEmployeePosition(employeePosition);
        employee.setUser(user);
        System.out.println(employee);
        this.employeeRepository.save(employee);

    }

    @Override
    public EmployeeDTO findDTOById(int id) {
        Employee employee = this.employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found"));
        return this.employeeMapper.convertToEmployeeDTO(employee);
    }

    @Override
    public Boolean findById(int id) {
        return this.employeeRepository.existsById(id);
    }
}

