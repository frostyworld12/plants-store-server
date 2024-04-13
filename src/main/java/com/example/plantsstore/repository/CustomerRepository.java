package com.example.plantsstore.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.plantsstore.model.*;

public interface CustomerRepository extends CrudRepository<Customer, String> {
  // List<Customer> findByNameEmailAddress(String name, String email, String address);
  List<Customer> findAll();
  Customer getById(String id);
}