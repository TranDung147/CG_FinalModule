package com.codegym.finalModule.mapper.user;

import com.codegym.finalModule.DTO.employee.EmployeeDTO;
import com.codegym.finalModule.enums.RoleEnums;
import com.codegym.finalModule.model.Role;
import com.codegym.finalModule.model.User;
import com.codegym.finalModule.repository.IRoleRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeToUserMapper {
    private final IRoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder; // Inject PasswordEncoder

    public EmployeeToUserMapper(IRoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User convertEmployeeToUser(EmployeeDTO employeeDTO) {
        Role role = this.roleRepository.findByRoleName(RoleEnums.ROLE_EMPLOYEE);
        List<Role> roleList = new ArrayList<>();
        roleList.add(role);

        // Hash the password before setting it
        String hashedPassword = passwordEncoder.encode(employeeDTO.getPassword());

        return User.builder()
                .email(employeeDTO.getEmail())
                .username(employeeDTO.getUsername())
                .encrytedPassword(hashedPassword) // Set hashed password
                .enabled(true)
                .roles(roleList)
                .build();
    }
}
