package com.codegym.finalModule.repository;

import com.codegym.finalModule.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPaymentRepository extends JpaRepository<Payment, Integer>  {

}
