package com.codegym.finalModule.repository;

import com.codegym.finalModule.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Integer> {
    boolean existsByPhoneNumber(String phoneNumber);
    Page<Customer> findByCustomerId(int customerId, Pageable pageable);
    @Query("SELECT c FROM Customer c LEFT JOIN FETCH c.user WHERE " +
            "(:field = 'name' AND c.customerName LIKE %:keyword%) OR " +
            "(:field = 'phone' AND c.phoneNumber LIKE %:keyword%) OR " +
            "(:field = 'address' AND c.address LIKE %:keyword%)")
    Page<Customer> searchCustomers(@Param("field") String field,
                                   @Param("keyword") String keyword,
                                   Pageable pageable);
    Customer findByPhoneNumber(String phoneNumber);
    Page<Customer> findByCustomerNameContaining(String name, Pageable pageable);

    Page<Customer> findByPhoneNumberContaining(String phone, Pageable pageable);

    Page<Customer> findByAddressContaining(String address, Pageable pageable);

    @Query("SELECT c FROM Customer c WHERE LOWER(c.user.email) LIKE LOWER(CONCAT('%', :email, '%'))")
    Page<Customer> searchByEmail(@Param("email") String email, Pageable pageable);



}
