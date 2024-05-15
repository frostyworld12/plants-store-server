package com.example.plantsstore.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.example.plantsstore.model.*;

public interface ProductRepository extends CrudRepository<Product, String> {
  Product getById(String id);

  Product findByNameAndIdNot(String name, String id);
  Product findByName(String name);

  Page<Product> findAllByOrderByCreatedAtDesc(Pageable pageable);
  Page<Product> findByNameContainingOrderByCreatedAtDesc(String name, Pageable pageable);
}