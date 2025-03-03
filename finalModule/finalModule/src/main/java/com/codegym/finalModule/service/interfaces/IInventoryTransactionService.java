package com.codegym.finalModule.service.interfaces;

import org.springframework.data.domain.Page;

import java.time.LocalDate;

public interface IInventoryTransactionService <T , D>{
    void exportFromWarehouse (D d) ;

    Page<T> searchTransactions (String field , String keyword , int page , int size ,
                                LocalDate fromDate , LocalDate toDate , String transactionType);
}
