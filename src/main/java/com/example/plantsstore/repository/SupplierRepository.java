package com.example.plantsstore.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.example.plantsstore.model.Supplier;

public interface SupplierRepository extends CrudRepository<Supplier, String> {
  Supplier getById(String id);

  Page<Supplier> findAllByOrderByCreatedAtDesc(Pageable pageable);
  Page<Supplier> findByNameContainingOrderByCreatedAtDesc(String name, Pageable pageable);

  List<Supplier> findByNameAndContactPersonAndEmail(String name, String contactPerson, String email);
}