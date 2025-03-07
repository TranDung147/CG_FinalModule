package com.codegym.finalModule.config;
import com.codegym.finalModule.model.User;
import com.codegym.finalModule.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;

@Controller
public class SecurityController {
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping(value = "/login")
    public String loginPage(Model model, @RequestParam(value = "error", defaultValue = "") String error) {
        model.addAttribute("error", error);
        return "login";
    }

    @GetMapping(value = "/home")
    public String homePage(Authentication authentication, Model model) {
        String username = authentication.getName();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String role = authorities.stream()
                .findFirst()
                .map(GrantedAuthority::getAuthority)
                .orElse("ROLE_USER");

        model.addAttribute("username", username);
        model.addAttribute("role", role);

        // Redirect based on role
        return switch (role) {
            case "ROLE_ADMIN" -> "admin/home";
            case "ROLE_WAREHOUSE" -> "warehouse/home";
            case "ROLE_SALES" -> "sales/home";
            case "ROLE_BUSINESS" -> "business/home";
//            case "ROLE_USER" -> "user/home";
            default -> "403";
        };
    }

    @GetMapping(value = "/logoutSuccessful")
    public String logoutSuccessfulPage(Model model) {
        model.addAttribute("title", "Logout");
        return "logoutSuccessfulPage";
    }

    @GetMapping(value = "/403")
    public String view403Page() {
        return "403";
    }
    @GetMapping("/account")
    public String viewAccount(Authentication authentication, Model model) {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return "403";
        }
        model.addAttribute("user", user);
        return "account";
    }

    @PostMapping("/account/update")
    public String updateAccount(
            Authentication authentication,
            @RequestParam String fullName,
            @RequestParam String email,
            Model model) {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return "403";
        }

        // Update user details
        user.setFullName(fullName);
        user.setEmail(email);
        userRepository.save(user);

        model.addAttribute("message", "Account updated successfully!");
        model.addAttribute("user", user);
        return "account";
    }

    @PostMapping("/account/change-password")
    public String changePassword(
            Authentication authentication,
            @RequestParam String oldPassword,
            @RequestParam String newPassword,
            @RequestParam String confirmPassword,
            Model model) {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return "403";
        }

        if (!passwordEncoder.matches(oldPassword, user.getEncrytedPassword())) {
            model.addAttribute("error", "Old password is incorrect!");
            model.addAttribute("user", user);
            return "account";
        }

        if (!newPassword.equals(confirmPassword)) {
            model.addAttribute("error", "New password and confirmation do not match!");
            model.addAttribute("user", user);
            return "account";
        }

        // Update password
        user.setEncrytedPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        model.addAttribute("message", "Password changed successfully!");
        model.addAttribute("user", user);
        return "account";
    }
}