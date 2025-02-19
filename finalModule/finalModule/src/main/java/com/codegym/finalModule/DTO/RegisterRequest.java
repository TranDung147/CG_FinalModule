package com.codegym.finalModule.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Data
@Getter
@Setter
public class RegisterRequest {

    @NotBlank(message = "Username cannot be empty")
    @Size(min = 4, max = 20, message = "Username must be between 4 and 20 characters")
    private String userName;

    @NotBlank(message = "Password cannot be empty")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String passWord;

    @NotBlank(message = "Confirm Password cannot be empty")
    private String confirmPassword;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Invalid email format")
    private String email;

    private String fullName; // Không bắt buộc
    private Boolean enabled = true; // ✅ Mặc định tài khoản được kích hoạt

    /**
     * ✅ Kiểm tra mật khẩu có khớp nhau không
     */
    public boolean isPasswordMatching() {
        return this.passWord != null && this.passWord.equals(this.confirmPassword);
    }



}
