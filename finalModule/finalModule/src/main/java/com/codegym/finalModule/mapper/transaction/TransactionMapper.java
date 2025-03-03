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

    private final IProductRepository productRepository;
    private final ISupplierRepository supplierRepository;
    public TransactionMapper(IProductRepository productRepository, ISupplierRepository supplierRepository) {
        this.productRepository = productRepository;
        this.supplierRepository = supplierRepository;
    }

   public InventoryTransaction convertToInventoryTransaction(InventoryTransactionDTO inventoryTransactionDTO) {
       Product product = this.productRepository.findById(inventoryTransactionDTO.getProduct_id()).orElseThrow(
               () -> new RuntimeException("Product not found") );
       Supplier supplier = this.supplierRepository.findById(inventoryTransactionDTO.getSupplier_id()).orElseThrow(
               () -> new RuntimeException("Supplier not found")) ;

       return InventoryTransaction.builder()
               .product(product)
               .supplier(supplier)
               .quantity(inventoryTransactionDTO.getQuantity())
               .build();
   }
   public InventoryTransactionDTO convertToInventoryTransactionDTO(InventoryTransaction inventoryTransaction) {
        return InventoryTransactionDTO.builder()
                .id(inventoryTransaction.getId())
                .quantity(inventoryTransaction.getQuantity())
                .created_at(inventoryTransaction.getCreatedAt())
                .product_id(inventoryTransaction.getProduct().getProductID())
                .supplier_id(inventoryTransaction.getSupplier().getId())
                .employee_id(inventoryTransaction.getEmployee().getEmployeeId())
                .build();
   }

}
