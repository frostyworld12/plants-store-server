package com.example.plantsstore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.plantsstore.DTO.requests.*;
import com.example.plantsstore.DTO.responses.*;
import com.example.plantsstore.model.*;
import com.example.plantsstore.repository.*;

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
  public ResponseEntity<?> createSupplier(@RequestBody SupplierRequest.SupplierRegistration newSupplier) {
    try {
      if (supplierRepository.findByNameAndContactPersonAndEmail(
          newSupplier.getName(),
          newSupplier.getContactPerson(),
          newSupplier.getEmail()
        ) != null
      ) {
        throw new Exception("Such supplier already exists!");
      }

      if (userRepository.findByUsername(newSupplier.getUsername()) != null) {
        throw new Exception("User with such username already exists!");
      }

      User user = new User(
        newSupplier.getUsername(),
        newSupplier.getPassword(),
        "Supplier"
      );

      Supplier supplier = new Supplier(
        newSupplier.getName(),
        newSupplier.getContactPerson(),
        newSupplier.getEmail(),
        newSupplier.getPhone(),
        user
      );

      userRepository.save(user);
      supplierRepository.save(supplier);

      SupplierResponse.SupplierRegistration result = setupSupplierData(supplier, user);

      return ResponseEntity.status(200).body(result);
    } catch (Exception ex) {
      return ResponseEntity.status(400).body(ex.getMessage());
    }
  }

  @PostMapping("/getUser")
  public @ResponseBody ResponseEntity<?> getUser(@RequestBody UserRequest.UserLogin userData) {
    try {
      User user = userRepository.findByUsernameAndPassword(userData.getUsername(), userData.getPassword());
      if (user == null) {
        throw new Exception("Incorrect username or password!");
      }

      if (user.getRole().equals("Employee")) {
        EmployeeResponse.EmployeeRegistration result = getEmployeeData(user);
        if (result == null) {
          throw new Exception("Could not find related employee!");
        }

        return ResponseEntity.status(200).body(result);
      } else if (user.getRole().equals("Supplier")) {
        SupplierResponse.SupplierRegistration result = getSupplierData(user);
        if (result == null) {
          throw new Exception("Could not find related supplier!");
        }

        return ResponseEntity.status(200).body(result);
      }

      return ResponseEntity.status(200).body(null);
    } catch (Exception ex) {
      return ResponseEntity.status(400).body(ex.getMessage());
    }
  }

  @GetMapping("/getUserById")
  public @ResponseBody ResponseEntity<?> getUserById(@RequestParam("userId") String userId) {
    try {
      User user = userRepository.getById(userId);

      if (user == null) {
        throw new Exception("Could not find user!");
      }

      if (user.getRole().equals("Employee")) {
        EmployeeResponse.EmployeeRegistration result = getEmployeeData(user);
        if (result == null) {
          throw new Exception("Could not find related employee!");
        }

        return ResponseEntity.status(200).body(result);
      } else if (user.getRole().equals("Supplier")) {
        SupplierResponse.SupplierRegistration result = getSupplierData(user);
        if (result == null) {
          throw new Exception("Could not find related supplier!");
        }

        return ResponseEntity.status(200).body(result);
      }
      return ResponseEntity.status(200).body(null);
    } catch (Exception ex) {
      return ResponseEntity.status(400).body(ex.getMessage());
    }
  }


  private SupplierResponse.SupplierRegistration getSupplierData(User user) {
    SupplierResponse.SupplierRegistration result = null;

    Supplier supplier = supplierRepository.findByUserId(user.getId());
    if (supplier != null) {
      result = setupSupplierData(supplier, user);
    }

    return result;
  }

  private SupplierResponse.SupplierRegistration setupSupplierData(Supplier supplier, User user) {
    return new SupplierResponse.SupplierRegistration(
      new SupplierResponse.Supplier(
        supplier.getId(),
        supplier.getName(),
        supplier.getContactPerson(),
        supplier.getPhone(),
        supplier.getEmail()
      ),
      new UserResponse(
        user.getId(),
        user.getUsername(),
        user.getRole()
      )
    );
  }

  private EmployeeResponse.EmployeeRegistration getEmployeeData(User user) {
    EmployeeResponse.EmployeeRegistration result = null;

    Employee employee = employeeRepository.findByUserId(user.getId());
    if (employee != null) {
      result = new EmployeeResponse.EmployeeRegistration(
        new EmployeeResponse.Employee(
          employee.getId(),
          employee.getFirstName(),
          employee.getLastName(),
          employee.getPosition(),
          employee.getImage()
        ),
        new UserResponse(
          user.getId(),
          user.getUsername(),
          user.getRole()
        )
      );
    }

    return result;
  }
}
