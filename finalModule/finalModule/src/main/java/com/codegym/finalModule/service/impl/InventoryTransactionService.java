package com.codegym.finalModule.service.impl;


import com.codegym.finalModule.DTO.product.ProductChoiceDTO;
import com.codegym.finalModule.DTO.product.ProductChoiceRequestDTO;
import com.codegym.finalModule.DTO.transaction.TransactionsDetailDTO;
import com.codegym.finalModule.enums.TransactionType;
import com.codegym.finalModule.mapper.transaction.TransactionMapper;
import com.codegym.finalModule.model.InventoryTransaction;
import com.codegym.finalModule.model.Product;
import com.codegym.finalModule.model.WareHouse;
import com.codegym.finalModule.repository.IEmployeeRepository;
import com.codegym.finalModule.repository.IInventoryTransactionRepository;
import com.codegym.finalModule.repository.IProductRepository;
import com.codegym.finalModule.repository.IWareHouseRepository;
import com.codegym.finalModule.service.interfaces.IInventoryTransactionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class InventoryTransactionService implements IInventoryTransactionService
        <InventoryTransaction, ProductChoiceRequestDTO , TransactionsDetailDTO> {

    private final TransactionMapper transactionMapper;
    private final IWareHouseRepository wareHouseRepository;
    private final IInventoryTransactionRepository inventoryTransactionRepository;
    private final IEmployeeRepository employeeRepository;
    private final IProductRepository productRepository;

    public InventoryTransactionService(TransactionMapper transactionMapper,
                                       IWareHouseRepository wareHouseRepository,
                                       IInventoryTransactionRepository inventoryTransactionRepository,
                                       IEmployeeRepository employeeRepository,
                                       IProductRepository productRepository) {
        this.transactionMapper = transactionMapper;
        this.wareHouseRepository = wareHouseRepository;
        this.inventoryTransactionRepository = inventoryTransactionRepository;
        this.employeeRepository = employeeRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void importToWarehouse(ProductChoiceRequestDTO productChoiceRequestDTO) {
        List<ProductChoiceDTO> productChoiceDTOList = productChoiceRequestDTO.getProducts();

        for (ProductChoiceDTO productChoiceDTO : productChoiceDTOList) {
            Product product = this.productRepository.findById(productChoiceDTO.getProductId()).orElse(null);
            WareHouse wareHouse = this.wareHouseRepository.findByProduct(product);
            if (wareHouse != null) {
                wareHouse.setQuantity(wareHouse.getQuantity() + productChoiceDTO.getProductQuantity());
                wareHouse.setPrice(productChoiceDTO.getProductPrice());
                this.wareHouseRepository.save(wareHouse);
            } else {
                this.wareHouseRepository.save(WareHouse.builder()
                        .product(product)
                        .quantity(productChoiceDTO.getProductQuantity())
                        .price(productChoiceDTO.getProductPrice())
                        .build());
            }
        }
        InventoryTransaction inventoryTransaction = this.transactionMapper.convertToInventoryTransactionImport(productChoiceRequestDTO);
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        inventoryTransaction.setEmployee(this.employeeRepository.findEmployeeByUser_Username(username));
        System.out.println(this.employeeRepository.findEmployeeByUser_Username(username).getEmployeeName());
        this.inventoryTransactionRepository.save(inventoryTransaction);
    }

    @Override
    public void exportFromWarehouse(ProductChoiceRequestDTO productChoiceRequestDTO) {
        List<ProductChoiceDTO> productChoiceDTOList = productChoiceRequestDTO.getProducts();
        for (ProductChoiceDTO productChoiceDTO : productChoiceDTOList) {
            Product product = this.productRepository.findById(productChoiceDTO.getProductId()).orElse(null);
            WareHouse wareHouse = this.wareHouseRepository.findByProduct(product);
            wareHouse.setQuantity(wareHouse.getQuantity() - productChoiceDTO.getQuantityInput());
            this.wareHouseRepository.save(wareHouse);
        }
        InventoryTransaction inventoryTransaction = this.transactionMapper.convertToInventoryTransactionExport(productChoiceRequestDTO);
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        inventoryTransaction.setEmployee(this.employeeRepository.findEmployeeByUser_Username(username));
        this.inventoryTransactionRepository.save(inventoryTransaction);
    }
    @Override
    public Page<InventoryTransaction> searchTransactions(String field, String keyword, int page, int size,
                                                         LocalDate fromDate, LocalDate toDate, String transactionType) {
        Pageable pageable1 = PageRequest.of(page - 1, size);
        if (transactionType != null && !transactionType.isEmpty()) {
            return this.inventoryTransactionRepository.searchTransactions(field , keyword,
                    TransactionType.valueOf(transactionType) , fromDate, toDate, pageable1);
        }
        return this.inventoryTransactionRepository.searchTransactions(field , keyword ,
                null , fromDate, toDate, pageable1);
    }

    @Override
    public List<TransactionsDetailDTO> getTransactionsDetail(Integer id) {
        InventoryTransaction inventoryTransaction = this.inventoryTransactionRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Inventory transaction not found"));
        return this.transactionMapper.convertToTransactionsDetailDTO(inventoryTransaction) ;
    }



}
