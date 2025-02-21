package com.codegym.finalModule.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "EmployeePositions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeePosition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer positionId;
    private String positionName;
    private String positionDescription;

    @OneToMany(mappedBy = "employeePosition", cascade = CascadeType.ALL)
    private List<Employee> employees;

}
