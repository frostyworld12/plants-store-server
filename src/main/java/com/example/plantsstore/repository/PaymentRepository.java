package com.example.plantsstore.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.plantsstore.model.*;

public interface PaymentRepository extends CrudRepository<Payment, String> {
}