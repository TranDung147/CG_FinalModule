package com.codegym.finalModule.repository;


import com.codegym.finalModule.model.EmployeePosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEmployeePositionRepository extends JpaRepository<EmployeePosition, Integer> {
}
