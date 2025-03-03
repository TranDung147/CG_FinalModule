package com.codegym.finalModule.repository;

import com.codegym.finalModule.model.User;
import com.codegym.finalModule.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IUserRoleRepository extends JpaRepository<UserRole, Integer> {
    List<UserRole> findAllByUser(User user);
}