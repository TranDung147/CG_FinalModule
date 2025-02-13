package com.codegym.finalModule.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "permissions")
@Getter
@Setter
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer permissionId;
    private String permissionName;
    private String description;

    @ManyToMany(fetch = FetchType.LAZY , mappedBy = "permissions")
    private List<Role> roles;
}
