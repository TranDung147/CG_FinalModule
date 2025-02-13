package com.codegym.finalModule.repository;

import com.codegym.finalModule.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEmployeeRepository extends JpaRepository<Employee, Integer> {
}
