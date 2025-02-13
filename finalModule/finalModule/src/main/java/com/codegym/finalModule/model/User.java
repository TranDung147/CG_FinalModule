package com.codegym.finalModule.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String username;
    private String password;
    private String email;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;
    @OneToOne(mappedBy = "user" , cascade = CascadeType.ALL)
    private Customer customer;
    @OneToOne(mappedBy = "user" , cascade = CascadeType.ALL)
    private Admin admin ;
    @OneToOne(mappedBy = "user" , cascade = CascadeType.ALL)
    private Employee employee;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
