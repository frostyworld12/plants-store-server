package com.example.plantsstore.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.example.plantsstore.model.*;

public interface ProductRepository extends CrudRepository<Product, String> {
  Product getById(String id);
  List<Product> findAll();
  List<Product> findByIdIn(Set<String> ids);
  List<Product> findByNameIgnoreCase(String name);
}