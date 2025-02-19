package com.codegym.finalModule.service.Class;


import com.codegym.finalModule.DTO.RegisterRequest;
import com.codegym.finalModule.model.Role;
import com.codegym.finalModule.model.User;
import com.codegym.finalModule.repository.IRoleRepository;
import com.codegym.finalModule.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public String register(RegisterRequest request) {
        // ✅ Kiểm tra nếu user đã tồn tại
        if (userRepository.findByUserName(request.getUserName()).isPresent()) {
            return "Username already exists!";
        }

        // ✅ Mã hóa mật khẩu bằng BCrypt
        String encodedPassword = passwordEncoder.encode(request.getPassWord());

        // ✅ Tạo user mới
        User newUser = new User();
        newUser.setUserName(request.getUserName());
        newUser.setPassWord(encodedPassword);
        newUser.setEmail(request.getEmail());
        newUser.setFullName(request.getFullName());

        newUser.setEnabled(true); // ✅ Đảm bảo `enabled` luôn có giá trị (Không bị null)

        newUser.setCreatedAt(LocalDateTime.now());
        newUser.setUpdatedAt(LocalDateTime.now());

        // ✅ Gán quyền ROLE_USER
        Role userRole = roleRepository.findByRoleName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Role not found!"));
        newUser.setRoles(Collections.singleton(userRole));

        // ✅ Lưu user vào database
        userRepository.save(newUser);

        return "User registered successfully!";
    }
}
