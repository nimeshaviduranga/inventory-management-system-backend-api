package com.inventory.inventory.repository;

import com.inventory.inventory.model.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long> {

    Optional<InventoryItem> findBySku(String sku);

    boolean existsBySku(String sku);

    @Query("SELECT i FROM InventoryItem i WHERE i.quantity <= i.lowStockThreshold")
    List<InventoryItem> findLowStockItems();

    List<InventoryItem> findByWarehouse(String warehouse);

    List<InventoryItem> findByQuantityGreaterThan(Integer quantity);

    List<InventoryItem> findByQuantityLessThan(Integer quantity);

    @Query("SELECT i FROM InventoryItem i WHERE i.productName LIKE %:keyword% OR i.sku LIKE %:keyword%")
    List<InventoryItem> searchByKeyword(String keyword);
}