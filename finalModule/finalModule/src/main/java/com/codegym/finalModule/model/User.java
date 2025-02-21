package com.codegym.finalModule.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String username;
    private String password;
    private String email;

    @Column(nullable = false, columnDefinition = "TINYINT(1) DEFAULT 1") // Đảm bảo Hibernate ánh xạ đúng với MySQL
    private Boolean enabled;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;

    @OneToOne(mappedBy = "user" , cascade = CascadeType.ALL, orphanRemoval = true)
    private Customer customer;

    @OneToOne(mappedBy = "user" , cascade = CascadeType.ALL, orphanRemoval = true)
    private Admin admin ;

    @OneToOne(mappedBy = "user" , cascade = CascadeType.ALL, orphanRemoval = true)
    private Employee employee;

    @PrePersist
    protected void dateBeforeCreate() {
        this.createdAt = LocalDateTime.now();
    }
    @PreUpdate
    protected void dateBeforeUpdate() {
        this.updatedAt = LocalDateTime.now();
    }


}
