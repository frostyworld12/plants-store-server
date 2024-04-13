package com.example.plantsstore.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.plantsstore.model.*;

public interface PurchaseRepository extends CrudRepository<Purchase, String> {
  List<Purchase> findAll();
  Purchase getById(String purchaseId);
}