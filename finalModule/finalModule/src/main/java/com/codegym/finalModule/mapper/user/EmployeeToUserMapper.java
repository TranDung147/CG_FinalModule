package com.codegym.finalModule.mapper.user;

import com.codegym.finalModule.DTO.employee.EmployeeDTO;
import com.codegym.finalModule.enums.RoleEnums;
import com.codegym.finalModule.model.Role;
import com.codegym.finalModule.model.User;
import com.codegym.finalModule.repository.IRoleRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class EmployeeToUserMapper {
    private final IRoleRepository roleRepository;
    public EmployeeToUserMapper(IRoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    public User convertEmployeeToUser (EmployeeDTO employeeDTO) {
        Role role = this.roleRepository.findByRoleName(RoleEnums.ROLE_EMPLOYEE);
        List<Role> roleList = new ArrayList<>() ;
        roleList.add(role);
        return User.builder()
                .email(employeeDTO.getEmail())
                .username(employeeDTO.getUsername())
                .password(employeeDTO.getPassword())
                .enabled(true)
                .roles(roleList)
                .build();
    }
}
