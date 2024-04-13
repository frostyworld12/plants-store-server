package com.example.plantsstore.repository;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

import com.example.plantsstore.model.*;

public interface SupplieRepository extends CrudRepository<Supplie, String> {
  List<Supplie> findByProductIdIn(Set<String> productIds);
}