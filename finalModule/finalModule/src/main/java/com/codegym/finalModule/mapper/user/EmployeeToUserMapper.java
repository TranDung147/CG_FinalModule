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
    private final PasswordEncoder passwordEncoder; // Inject PasswordEncoder

    public EmployeeToUserMapper( PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User convertEmployeeToUser(EmployeeDTO employeeDTO) {

        String hashedPassword = passwordEncoder.encode(employeeDTO.getPassword());

        return User.builder()
                .email(employeeDTO.getEmail())
                .username(employeeDTO.getUsername())
                .encrytedPassword(hashedPassword) // Set hashed password
                .enabled(true)
                .build();
    }
}
