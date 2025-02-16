package com.codegym.finalModule.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "employees")
@Getter
@Setter
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer employeeId;
    private String employeeName;
    private LocalDate employeeBirthday;
    private String employeeAddress;
    private String employeePhone;
    private String employeeWork;
    private boolean isDisabled = false;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user ;
}
