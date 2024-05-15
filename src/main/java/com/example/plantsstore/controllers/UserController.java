package com.example.plantsstore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.plantsstore.DTO.EmployeeDTO;
import com.example.plantsstore.DTO.SupplierDTO;
import com.example.plantsstore.DTO.UserDTO;
import com.example.plantsstore.model.*;
import com.example.plantsstore.repository.EmployeeRepository;
import com.example.plantsstore.repository.SupplierRepository;
import com.example.plantsstore.repository.UserRepository;

@Controller
@RequestMapping(path = "/users")
public class UserController {
  @Autowired
  private SupplierRepository supplierRepository;
  @Autowired
  private EmployeeRepository employeeRepository;
  @Autowired
  private UserRepository userRepository;

  @PostMapping("/createSupplierUser")
  public @ResponseBody ResponseEntity<?> createSupplierUser(@RequestBody SupplierDTO.Supplier newSupplier) {
    try {
      Supplier existingSupplier = supplierRepository.findByNameAndContactPersonAndEmail(
        newSupplier.name,
        newSupplier.contactPerson,
        newSupplier.email
      );

      if (existingSupplier != null) {
        throw new Exception("Such supplier already exists!");
      }

      if (userRepository.findByUsername(newSupplier.user.username) != null) {
        throw new Exception("User with such username already exists!");
      }

      User user = new User(
        newSupplier.user.username,
        newSupplier.user.password,
        "Supplier"
      );

      Supplier supplier = new Supplier(
        newSupplier.name,
        newSupplier.contactPerson,
        newSupplier.email,
        newSupplier.phone,
        user
      );

      userRepository.save(user);
      supplierRepository.save(supplier);

      SupplierDTO.Supplier result = new SupplierDTO.Supplier(
        supplier.getId(),
        supplier.getEmail(),
        supplier.getName(),
        supplier.getPhone(),
        supplier.getContactPerson(),
        new SupplierDTO.User(
          user.getUsername(),
          user.getRole()
        )
      );

      return ResponseEntity.status(200).body(result);
    } catch (Exception ex) {
      return ResponseEntity.status(400).body(ex.getMessage());
    }
  }

  @PostMapping("/getUser")
  public @ResponseBody ResponseEntity<?> getSupplierUser(@RequestBody UserDTO.User currentUser) {
    try {
      User user = userRepository.findByUsernameAndPassword(currentUser.username, currentUser.password);
      if (user == null) {
        throw new Exception("Incorrect username or password!");
      }

      if (currentUser.userType.equals("Supplier")) {
        Supplier supplier = supplierRepository.findByUserId(user.getId());
        if (supplier == null) {
          throw new Exception("Can not find related supplier!");
        }

        SupplierDTO.Supplier result = new SupplierDTO.Supplier(
          supplier.getId(),
          supplier.getEmail(),
          supplier.getName(),
          supplier.getPhone(),
          supplier.getContactPerson(),
          new SupplierDTO.User(
            user.getUsername(),
            user.getRole()
          )
        );
        return ResponseEntity.status(200).body(result);
      } else if (currentUser.userType.equals("Employee")) {
        Employee employee = employeeRepository.findByUserId(user.getId());
        if (employee == null) {
          throw new Exception("Can not find related employee!");
        }

        EmployeeDTO.Employee result = new EmployeeDTO.Employee(
          employee.getId(),
          employee.getImage(),
          employee.getFirstName(),
          employee.getLastName(),
          employee.getPosition(),
          new EmployeeDTO.User(
            user.getUsername(),
            user.getRole()
          )
        );
        return ResponseEntity.status(200).body(result);
      }

      return ResponseEntity.status(200).body(null);
    } catch (Exception ex) {
      return ResponseEntity.status(400).body(ex.getMessage());
    }
  }

  // @PostMapping
  // public  @ResponseBody ResponseEntity<?> createEmployeeUser()
}
