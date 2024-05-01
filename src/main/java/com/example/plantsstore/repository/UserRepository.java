package com.example.plantsstore.repository;

import org.springframework.data.repository.CrudRepository;
import com.example.plantsstore.model.*;

public interface UserRepository extends CrudRepository<User, String> {
  User findByUsernameAndIdNot(String username, String id);
  User findByUsername(String username);

  User getById(String id);
}