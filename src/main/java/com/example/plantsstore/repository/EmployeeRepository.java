package com.example.plantsstore.repository;

import org.springframework.data.domain.*;
import org.springframework.data.repository.*;
import com.example.plantsstore.model.*;

public interface EmployeeRepository extends CrudRepository<Employee, String> {
  Employee getById(String id);

  Page<Employee> findAllByOrderByCreatedAtDesc(Pageable pageable);
  Page<Employee> findByFirstNameContainingOrderByCreatedAtDesc(String name, Pageable pageable);

  Employee findByUserId(String userId);
}