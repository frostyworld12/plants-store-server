package com.example.plantsstore.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.example.plantsstore.model.*;

public interface CustomerRepository extends CrudRepository<Customer, String> {
  Customer getById(String id);

  Page<Customer> findAllByOrderByCreatedAtDesc(Pageable pageable);
  Page<Customer> findByLastNameContainingOrderByCreatedAtDesc(String firstName, String lastName, Pageable pageable);

  List<Customer> findByEmail(String email);
}