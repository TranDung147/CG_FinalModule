package com.codegym.finalModule.service.impl;

import com.codegym.finalModule.DTO.transaction.InventoryTransactionDTO;
import com.codegym.finalModule.enums.TransactionType;
import com.codegym.finalModule.exception.warehouse.WareHouseException;
import com.codegym.finalModule.mapper.transaction.TransactionMapper;
import com.codegym.finalModule.model.InventoryTransaction;
import com.codegym.finalModule.model.WareHouse;
import com.codegym.finalModule.repository.IEmployeeRepository;
import com.codegym.finalModule.repository.IInventoryTransactionRepository;
import com.codegym.finalModule.repository.IWareHouseRepository;
import com.codegym.finalModule.service.interfaces.IInventoryTransactionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class InventoryTransactionService implements IInventoryTransactionService
        <InventoryTransaction, InventoryTransactionDTO> {
    private final TransactionMapper transactionMapper;
    private final IWareHouseRepository wareHouseRepository;
    private final IInventoryTransactionRepository inventoryTransactionRepository;
    private final IEmployeeRepository employeeRepository;

    public InventoryTransactionService(TransactionMapper transactionMapper,
                                       IWareHouseRepository wareHouseRepository ,
                                       IInventoryTransactionRepository inventoryTransactionRepository ,
                                       IEmployeeRepository employeeRepository) {
        this.transactionMapper = transactionMapper;
        this.wareHouseRepository = wareHouseRepository;
        this.inventoryTransactionRepository = inventoryTransactionRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void exportFromWarehouse(InventoryTransactionDTO inventoryTransactionDTO) {
        InventoryTransaction inventoryTransaction =
                this.transactionMapper.convertToInventoryTransaction(inventoryTransactionDTO);
        System.out.println("Product ID: " + inventoryTransactionDTO.getProduct_id());
        System.out.println("Supplier ID: " + inventoryTransactionDTO.getSupplier_id());
        WareHouse wareHouse = this.wareHouseRepository.findByProductAndSupplier(
                inventoryTransaction.getProduct(), inventoryTransaction.getSupplier());
        if (wareHouse == null) {
            throw new RuntimeException("Warehouse not found");
        }
        if (wareHouse.getQuantity() < inventoryTransactionDTO.getQuantity()) {
            throw new WareHouseException("Vượt quá số lượng trong kho !");
        }
        wareHouse.setQuantity(wareHouse.getQuantity() - inventoryTransactionDTO.getQuantity());
        this.wareHouseRepository.save(wareHouse);

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        inventoryTransaction.setTransactionType(TransactionType.EXPORT);
        inventoryTransaction.setEmployee(this.employeeRepository.findEmployeeByUser_Username(username));
        this.inventoryTransactionRepository.save(inventoryTransaction);
    }

    @Override
    public Page<InventoryTransaction> searchTransactions(String field, String keyword,
                                                         int page, int size, LocalDate fromDate,
                                                         LocalDate toDate, String transactionType) {
        Pageable pageable1 = PageRequest.of(page - 1, size);
        if (transactionType != null && !transactionType.isEmpty()) {
            return this.inventoryTransactionRepository.filterTransactions(field , keyword,
                    TransactionType.valueOf(transactionType) , fromDate, toDate, pageable1);
        }
        return this.inventoryTransactionRepository.filterTransactions(field , keyword ,
                null , fromDate, toDate, pageable1);
    }


}
