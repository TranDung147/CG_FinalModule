package com.codegym.finalModule.service.Class;

import com.codegym.finalModule.model.User;
import com.codegym.finalModule.repository.IUserRepository;
import com.codegym.finalModule.service.Interface.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepository userRepository;

    @Override
    public User getUserByUserName(String userName) {
        return userRepository.findByUserName(userName)
                .orElseThrow(() -> new RuntimeException("Khong tim thay thong tin nguoi dung: " + userName));

    }

}
