package com.codegym.finalModule.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Integer roleId;

    @Column(nullable = false, unique = true)
    private String roleName;

    // ✅ Quan hệ với bảng trung gian User_Role
    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<User_Role> roleUsers;

    // ✅ Quan hệ ManyToMany với User
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;
}
