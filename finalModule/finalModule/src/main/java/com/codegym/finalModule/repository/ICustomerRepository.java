package com.codegym.finalModule.repository;

import com.codegym.finalModule.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Integer> {
    boolean existsByPhoneNumber(String phoneNumber);
    Page<Customer> findByCustomerId(int customerId, Pageable pageable);

    @Query("SELECT c FROM Customer c WHERE " +
            "(:field = 'name' AND c.customerName LIKE %:keyword%) OR " +
            "(:field = 'phone' AND c.phoneNumber LIKE %:keyword%) OR " +
            "(:field = 'address' AND c.address LIKE %:keyword%)")
    Page<Customer> searchCustomers(@Param("field") String field,
                                   @Param("keyword") String keyword,
                                   Pageable pageable);
    Customer findByPhoneNumber(String phoneNumber);
  
    Page<Customer> findByCustomerNameContainingIgnoreCase(String name, Pageable pageable);
    Page<Customer> findByPhoneNumberContaining(String phone, Pageable pageable);
    Page<Customer> findByAddressContainingIgnoreCase(String address, Pageable pageable);
    Page<Customer> findByEmailContainingIgnoreCase(String email, Pageable pageable);
}
