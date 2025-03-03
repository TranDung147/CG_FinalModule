package com.codegym.finalModule.config;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;

@Controller
public class SecurityController {

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
}