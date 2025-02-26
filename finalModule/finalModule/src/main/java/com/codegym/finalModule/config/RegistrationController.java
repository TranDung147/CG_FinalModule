package com.codegym.finalModule.config;

import com.codegym.finalModule.enums.RoleEnums;
import com.codegym.finalModule.model.Role;
import com.codegym.finalModule.model.User;
import com.codegym.finalModule.model.UserRole;

import com.codegym.finalModule.repository.IUserRepository;
import com.codegym.finalModule.repository.IUserRoleRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationController {

    @Autowired
    private IUserRepository iUserRepository;

    @Autowired
    private IUserRoleRepository iUserRoleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {

        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String username, @RequestParam String password, Model model) {
        User user = new User();
        user.setUsername(username);
        user.setEncrytedPassword(passwordEncoder.encode(password));
        user.setEnabled(true);
        iUserRepository.save(user);

        // Assign ROLE_USER to the new user
        Role role = new Role();
        role.setRoleId(2); // Assuming ROLE_USER has roleId = 1
        role.setRoleName(RoleEnums.valueOf("ROLE_CUSTOMER"));

        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);
        iUserRoleRepository.save(userRole);

        model.addAttribute("message", "Registration successful! Please login.");
        return "login";
    }
}