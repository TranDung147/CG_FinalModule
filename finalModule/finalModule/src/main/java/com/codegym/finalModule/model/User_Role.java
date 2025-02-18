package com.codegym.finalModule.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_roles") // ✅ Đổi thành `user_roles` để trùng với `User.java`
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User_Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId") // ✅ Trỏ đúng tới `User`
    private User user;

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "role_id") // ✅ Đúng: Trỏ vào `role_id` của Role
    private Role role;


}