package com.codegym.finalModule.repository;

import com.codegym.finalModule.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



@Repository
public interface IEmployeeRepository extends JpaRepository<Employee, Integer> {
    boolean existsByEmployeePhone(String phone);
    Page<Employee> findByEmployeePosition_PositionNameContaining(String positionName, Pageable pageable);

    @Query("SELECT e from Employee e where "  +
            "(:field = 'name' AND e.employeeName LIKE %:keyword%) OR " +
            "(:field = 'phone' AND e.employeePhone LIKE %:keyword%)")
    Page<Employee> searchEmployees (@Param("field") String field,
                                    @Param("keyword") String keyword,
                                    Pageable pageable);

}