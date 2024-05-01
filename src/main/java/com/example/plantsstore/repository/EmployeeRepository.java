package com.example.plantsstore.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import com.example.plantsstore.model.*;

public interface EmployeeRepository extends CrudRepository<Employee, String>, JpaSpecificationExecutor<Employee> {
  Employee getById(String id);

  Page<Employee> findAllByOrderByCreatedAtDesc(Pageable pageable);
  Page<Employee> findByFirstNameContainingOrderByCreatedAtDesc(String name, Pageable pageable);

  Employee findByUserId(String userId);
}