package com.example.plantsstore.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.plantsstore.model.*;

public interface ProductsInPurchaseRepository extends CrudRepository<ProductsInPurchase, String> {
}