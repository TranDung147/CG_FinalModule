package com.codegym.finalModule.controller;

import com.codegym.finalModule.DTO.RegisterRequest;
import com.codegym.finalModule.service.Class.AuthService;
//import com.codegym.finalModule.service.PasswordResetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller

public class AuthController {
    @Autowired
    private AuthService authService;
    //@Autowired
//private PasswordResetService passwordResetService;
    @GetMapping(value = "/login")
    public String loginPage(Model model, @RequestParam(value = "error", defaultValue = "") String error,
                            @RequestParam(value = "logout", defaultValue = "") String logout) {
        if (!error.isEmpty()) {
            model.addAttribute("error", "Invalid username or password.");
        }
        if (!logout.isEmpty()) {
            model.addAttribute("logout", "You have been logged out successfully.");
        }
        model.addAttribute("registerRequest", new RegisterRequest());
        return "index";
    }

    @GetMapping(value = "/logoutSuccessful")
    public String logoutSuccessfulPage(Model model) {
        model.addAttribute("title", "Logout");
        return "logoutSuccessfulPage";
    }
    /**
     * ✅ Hiển thị trang đăng ký
     */
//    @GetMapping("/register")
//    public String registerPage(Model model) {
//        model.addAttribute("registerRequest", new RegisterRequest());
//        return "index"; // Trả về template `register.html`
//    }

    /**
     * ✅ Xử lý đăng ký tài khoản (POST)
     */
    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("registerRequest") RegisterRequest request,
                           BindingResult result,
                           Model model) {
        // Kiểm tra lỗi validate
        if (result.hasErrors()) {
            return "index"; // Nếu có lỗi, quay lại trang đăng ký
        }

        // Kiểm tra mật khẩu có khớp nhau không
        if (!request.isPasswordMatching()) {
            model.addAttribute("error", "Passwords do not match.");
            return "index";
        }

        // Gọi service để xử lý đăng ký
        String response = authService.register(request);

        if (response.equals("User registered successfully!")) {
            return "redirect:/login?success"; // Chuyển hướng đến trang đăng nhập sau khi đăng ký thành công
        } else {
            model.addAttribute("error", response);
            return "index";
        }
    }
    @GetMapping("/forgot-password")
    public String forgotPasswordPage() {
        return "forgot-password"; // Trả về template `forgot-password.html`
    }

    /**
     * Xử lý yêu cầu đặt lại mật khẩu
     */
//    @PostMapping("/forgot-password")
//    public String processForgotPassword(@RequestParam String email, Model model) {
//        String response = passwordResetService.processForgotPassword(email);
//        model.addAttribute("message", response);
//        return "forgot-password";
//    }

    /**
     * Hiển thị trang đặt lại mật khẩu
     */
    @GetMapping("/reset-password")
    public String resetPasswordPage(@RequestParam String token, Model model) {
        model.addAttribute("token", token);
        return "reset-password"; // Trả về template `reset-password.html`
    }

    /**
     * ✅ Xử lý đặt mật khẩu mới
     */
//    @PostMapping("/reset-password")
//    public String resetPassword(@RequestParam String token,
//                                @RequestParam String newPassword,
//                                Model model) {
//        String response = passwordResetService.resetPassword(token, newPassword);
//        model.addAttribute("message", response);
//        return "reset-password";
//    }

    @GetMapping(value = "/403")
    public String view403Page() {
        return "403";
    }
}
