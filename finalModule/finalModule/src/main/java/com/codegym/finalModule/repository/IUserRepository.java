package com.codegym.finalModule.repository;

import com.codegym.finalModule.model.Role;
import com.codegym.finalModule.model.User;
import com.codegym.finalModule.model.User_Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUserName(String username);
    Optional<User> findByEmail(String email); // ✅ Tìm user theo email

    Optional<User> findByResetToken(String resetToken);

}

