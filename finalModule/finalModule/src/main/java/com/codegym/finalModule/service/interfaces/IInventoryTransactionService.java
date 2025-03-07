package com.codegym.finalModule.service.interfaces;

import com.codegym.finalModule.model.TransactionDetail;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

public interface IInventoryTransactionService <T , D , H>{
    void exportFromWarehouse (D d) ;
    void importToWarehouse (D d) ;
    Page<T> searchTransactions (String field , String keyword , int page , int size ,
                                LocalDate fromDate , LocalDate toDate , String transactionType);
    List<H> getTransactionsDetail (Integer id);
}
