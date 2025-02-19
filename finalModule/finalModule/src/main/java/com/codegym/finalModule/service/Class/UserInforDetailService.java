package com.codegym.finalModule.service.Class;

import com.codegym.finalModule.DTO.UserInfoUserDetails;
import com.codegym.finalModule.model.Role;
import com.codegym.finalModule.model.User;
import com.codegym.finalModule.model.User_Role;
import com.codegym.finalModule.repository.IUserRepository;
import com.codegym.finalModule.repository.IUserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserInforDetailService implements UserDetailsService {

    @Autowired
    private IUserRepository iUserRepository;

    @Autowired
    private IUserRoleRepository iUserRoleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // ✅ Sửa: Lấy `User` từ `Optional`
        User appUser = iUserRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        List<User_Role> userRoles = iUserRoleRepository.findAllByUser(appUser);

        // ✅ Trả về `UserInfoUserDetails`
        return new UserInfoUserDetails(appUser, userRoles);
    }




}
