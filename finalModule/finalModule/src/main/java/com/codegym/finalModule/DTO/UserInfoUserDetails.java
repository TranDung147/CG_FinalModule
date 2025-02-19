package com.codegym.finalModule.DTO;

import com.codegym.finalModule.model.User;
import com.codegym.finalModule.model.User_Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserInfoUserDetails implements UserDetails {
    private String username;
    private String password;
    private List<GrantedAuthority> authorities;

    public UserInfoUserDetails(User user, List<User_Role> userRoles) {
        this.username = user.getUserName();  // ✅ Đảm bảo đúng tên getter
        this.password = user.getPassWord();  // ✅ Đảm bảo đúng tên getter
        this.authorities = new ArrayList<>();

        // Nếu userRoles là null hoặc trống, thêm quyền mặc định "ROLE_USER"
        if (userRoles != null && !userRoles.isEmpty()) {
            for (User_Role userRole : userRoles) {
                // Đảm bảo role có tiền tố "ROLE_" để Spring Security nhận diện đúng
                String roleName = "ROLE_" + userRole.getRole().getRoleName().toUpperCase();
                authorities.add(new SimpleGrantedAuthority(roleName));
            }
        } else {
            authorities.add(new SimpleGrantedAuthority("ROLE_USER")); // ✅ Mặc định nếu user không có role
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
