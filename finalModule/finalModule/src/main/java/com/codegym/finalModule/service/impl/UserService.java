package com.codegym.finalModule.service.impl;

import com.codegym.finalModule.model.User;
import com.codegym.finalModule.repository.IUserRepository;
import com.codegym.finalModule.service.interfaces.IUserService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService implements IUserService <User> {

    private final IUserRepository IUserRepository;
    public UserService(IUserRepository IUserRepository) {
        this.IUserRepository = IUserRepository;
    }

    @Override
    public User findById(int id) {
        return this.IUserRepository.findById(id).orElseThrow(
                () -> new RuntimeException("User Not Found"));
    }

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public User update(User user, int id) {
        return null;
    }

    @Override
    public void delete(int id) {

    }
}
