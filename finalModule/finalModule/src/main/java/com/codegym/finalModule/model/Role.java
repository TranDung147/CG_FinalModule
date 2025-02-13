package com.codegym.finalModule.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "roles")
@Getter
@Setter
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roleId;
    private String roleName;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "permission_role" ,
    joinColumns = @JoinColumn(name = "role_id") ,
    inverseJoinColumns = @JoinColumn(name = "permission_id"))
    private List<Permission> permissions;

    @ManyToMany(fetch = FetchType.LAZY , mappedBy = "roles")
    private List<User> users;
}
