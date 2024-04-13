package com.example.plantsstore.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.plantsstore.model.Supplier;

public interface SupplierRepository extends CrudRepository<Supplier, String> {
  Supplier findByNameAndContactPersonAndEmailAndPhoneIgnoreCase(String name, String contactPerson, String email, String phone);
}