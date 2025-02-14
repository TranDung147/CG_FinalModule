package com.codegym.finalModule.repository;

import com.codegym.finalModule.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IEmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query("SELECT u FROM Employee u WHERE " +
            "(:type = 'all' AND (LOWER(u.employeeName) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR u.employeePhone LIKE CONCAT('%', :keyword, '%') OR LOWER(u.employeeWork) LIKE LOWER(CONCAT('%', :keyword, '%')))) " +
            "OR (:type = 'employeeName' AND LOWER(u.employeeName) LIKE LOWER(CONCAT('%', :keyword, '%'))) " +
            "OR (:type = 'employeePhone' AND u.employeePhone LIKE CONCAT('%', :keyword, '%')) " +
            "OR (:type = 'employeeWork' AND LOWER(u.employeeWork) LIKE LOWER(CONCAT('%', :keyword, '%')))")
    Page<Employee> searchByKeywordAndType(String keyword, String type, Pageable pageable);

}
