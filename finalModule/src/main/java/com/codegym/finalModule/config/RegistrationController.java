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
        model.addAttribute("user", new User()); // Add empty User object to the model for form binding
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String fullName,
            Model model) {

        // Check if username already exists
        if (iUserRepository.findByUsername(username) != null) {
            model.addAttribute("error", "Username already exists! Please choose another.");
            return "register";
        }

        // Create new User object with all fields
        User user = User.builder()
                .username(username)
                .encrytedPassword(passwordEncoder.encode(password))
                .email(email)
                .fullName(fullName)
                .enabled(true)
                .build();

        // Save the user
        iUserRepository.save(user);

        // Assign ROLE_CUSTOMER to the new user
        Role role = new Role();
        role.setRoleId(2); // Assuming ROLE_CUSTOMER has roleId = 2
        role.setRoleName(RoleEnums.ROLE_CUSTOMER);

        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);
        iUserRoleRepository.save(userRole);

        model.addAttribute("message", "Registration successful! Please login.");
        return "login";
    }
}