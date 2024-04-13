package com.example.plantsstore.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.example.plantsstore.model.*;

public interface InventoryRepository extends CrudRepository<Inventory, String> {
  List<Inventory> findByProductIdIn(Set<String> productIds);
  Inventory findByProductId(String productId);
}