package com.codegym.finalModule.repository;

import com.codegym.finalModule.model.InventoryTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IInventoryTransactionRepository extends JpaRepository<InventoryTransaction, Integer> {
}
