package com.example.plantsstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import com.example.plantsstore.model.*;

public interface EmployeeRepository extends CrudRepository<Employee, String>, JpaSpecificationExecutor<Employee> {
  List<Employee> findAll();
  Employee getById(String id);
  Employee findByUserId(String userId);
}