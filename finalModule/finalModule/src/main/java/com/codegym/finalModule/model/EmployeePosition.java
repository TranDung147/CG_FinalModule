package com.codegym.finalModule.model;


import jakarta.persistence.*;
import lombok.*;

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

    @OneToOne(mappedBy = "employeePosition" , fetch = FetchType.EAGER)
    private Employee employee;

}
