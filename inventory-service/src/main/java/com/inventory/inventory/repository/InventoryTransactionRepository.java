package com.inventory.inventory.repository;

import com.inventory.inventory.model.InventoryTransaction;
import com.inventory.inventory.model.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface InventoryTransactionRepository extends JpaRepository<InventoryTransaction, Long> {

    List<InventoryTransaction> findByInventoryItemId(Long inventoryItemId);

    List<InventoryTransaction> findByInventoryItemSku(String sku);

    List<InventoryTransaction> findByType(TransactionType type);

    List<InventoryTransaction> findByReferenceId(String referenceId);

    List<InventoryTransaction> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);

    List<InventoryTransaction> findByCreatedBy(String createdBy);
}