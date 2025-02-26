package com.codegym.finalModule.model;

import com.codegym.finalModule.enums.RoleEnums;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "role",
        uniqueConstraints = {
                @UniqueConstraint(name = "APP_ROLE_UK", columnNames = "role_name") })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    //    ROLE_ADMIN
//    ROLE_USER
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", nullable = false)
    private Integer roleId;

    @Column(nullable = false, unique = true, name = "role_name", length = 30)
    @Enumerated(EnumType.STRING)
    private RoleEnums roleName;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;
}
