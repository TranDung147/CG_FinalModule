package com.codegym.finalModule.mapper.transaction;


import com.codegym.finalModule.DTO.product.ProductChoiceRequestDTO;
import com.codegym.finalModule.DTO.transaction.TransactionsDetailDTO;
import com.codegym.finalModule.enums.TransactionType;
import com.codegym.finalModule.model.InventoryTransaction;
import com.codegym.finalModule.model.TransactionDetail;
import com.codegym.finalModule.repository.IProductRepository;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
public class TransactionMapper {
    private final IProductRepository productRepository;
    public TransactionMapper(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public InventoryTransaction convertToInventoryTransactionImport(ProductChoiceRequestDTO productChoiceRequestDTO) {
        InventoryTransaction inventoryTransaction = InventoryTransaction.builder()
                .transactionType(TransactionType.IMPORT).build();

        List<TransactionDetail> transactionDetailList = productChoiceRequestDTO.getProducts().stream().map(
                productChoiceDTO -> TransactionDetail.builder()
                       .product(this.productRepository.findById(productChoiceDTO.getProductId()).orElse(null))
                       .quantity(productChoiceDTO.getProductQuantity())
                       .price(productChoiceDTO.getProductPrice())
                       .inventoryTransaction(inventoryTransaction)
                       .build()
        ).toList();

        inventoryTransaction.setTransactionDetails(transactionDetailList);
        return inventoryTransaction;
    }
    public InventoryTransaction convertToInventoryTransactionExport(ProductChoiceRequestDTO productChoiceRequestDTO) {
        InventoryTransaction inventoryTransaction = InventoryTransaction.builder()
                .transactionType(TransactionType.EXPORT)
                .build();

        List<TransactionDetail> transactionDetailList = productChoiceRequestDTO.getProducts().stream().map(
                productChoiceDTO -> TransactionDetail.builder()
                        .product(this.productRepository.findById(productChoiceDTO.getProductId()).orElse(null))
                        .quantity(productChoiceDTO.getQuantityInput())
                        .inventoryTransaction(inventoryTransaction)
                        .price(productChoiceDTO.getProductPrice())
                        .build()
        ).toList();

        inventoryTransaction.setTransactionDetails(transactionDetailList);
        return inventoryTransaction;
    }

    public List<TransactionsDetailDTO> convertToTransactionsDetailDTO(InventoryTransaction inventoryTransaction) {
       return inventoryTransaction.getTransactionDetails().stream().map(transactionDetail ->
               TransactionsDetailDTO.builder()
                       .id(transactionDetail.getId())
                       .productName(transactionDetail.getProduct().getName())
                       .supplierName(transactionDetail.getProduct().getSupplier().getName())
                       .quantity(transactionDetail.getQuantity())
                       .price(transactionDetail.getPrice())
                       .build()).toList() ;
    }

}