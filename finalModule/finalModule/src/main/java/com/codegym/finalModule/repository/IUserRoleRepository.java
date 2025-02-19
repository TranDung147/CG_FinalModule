package com.codegym.finalModule.repository;

import com.codegym.finalModule.model.User;
import com.codegym.finalModule.model.User_Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IUserRoleRepository extends JpaRepository<User_Role, Integer> {
    List<User_Role> findAllByUser(User user);
}
