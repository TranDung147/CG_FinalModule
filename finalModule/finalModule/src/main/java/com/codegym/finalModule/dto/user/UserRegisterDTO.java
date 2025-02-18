package com.codegym.finalModule.dto.user;

import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRegisterDTO {
    private String username;
    private String password;
    private String email;
    private String fullName;
    private String phone;
    private String address;
    private LocalDate birthDate;
}
