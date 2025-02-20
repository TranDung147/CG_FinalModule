package com.codegym.finalModule.model;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer employeeId;
    private String employeeName;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate employeeBirthday;
    private String employeeAddress;
    private String employeePhone;
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "position_id")
//    private EmployeePosition employeePosition;
    private String employeeWork;
    private Boolean isDisabled = false;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user ;
}
