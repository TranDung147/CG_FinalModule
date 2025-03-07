package com.codegym.finalModule.mapper.transaction;


import com.codegym.finalModule.DTO.transaction.InventoryTransactionDTO;
import com.codegym.finalModule.model.InventoryTransaction;
import com.codegym.finalModule.model.Product;
import com.codegym.finalModule.model.Supplier;
import com.codegym.finalModule.repository.IProductRepository;
import com.codegym.finalModule.repository.ISupplierRepository;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {
    private final IProductRepository iproductRepository;
    private final ISupplierRepository iSupplierRepository;

    public TransactionMapper(IProductRepository iproductRepository,
                             ISupplierRepository iSupplierRepository) {
        this.iproductRepository = iproductRepository;
        this.iSupplierRepository = iSupplierRepository;
    }

    public InventoryTransaction convertToInventoryTransaction(InventoryTransactionDTO inventoryTransactionDTO) {
        Product product = iproductRepository.findById(inventoryTransactionDTO.getProduct_id()) // Integer
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Supplier supplier = iSupplierRepository.findById(inventoryTransactionDTO.getSupplier_id())
                .orElseThrow(() -> new RuntimeException("Supplier not found"));

        return InventoryTransaction.builder()
                .product(product)
                .supplier(supplier)
                .quantity(inventoryTransactionDTO.getQuantity())
                .build();
    }

}