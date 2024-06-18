package com.example.plantsstore.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.example.plantsstore.model.*;
import com.example.plantsstore.repository.*;
import com.example.plantsstore.utility.Utility;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping(path = "/employees")
public class EmployeeController {
  @Autowired
  private EmployeeRepository employeeRepository;
  @Autowired
  private UserRepository userRepository;

  @GetMapping("/getEmployees")
  public @ResponseBody ResponseEntity<?> getEmployees(
      @RequestParam("limit") Integer limit,
      @RequestParam("offset") Integer offset,
      @RequestParam(value = "query", required = false) String query) {
    // EmployeeResponseDTO.Employees result = new EmployeeResponseDTO.Employees();
    // try {
    //   Pageable pageable = PageRequest.of(offset, limit);
    //   Page<Employee> employeesPage = Utility.isStringEmpty(query)
    //       ? employeeRepository.findAllByOrderByCreatedAtDesc(pageable)
    //       : employeeRepository.findByFirstNameContainingOrderByCreatedAtDesc(query, pageable);

    //   List<Employee> employeeContent = employeesPage.getContent();
    //   List<EmployeeResponseDTO.Employee> employees = new ArrayList<>();
    //   employeeContent.forEach(employee -> {
    //     employees.add(new EmployeeResponseDTO.Employee(
    //         employee.getUser().getId(),
    //         employee.getId(),
    //         employee.getUser().getUsername(),
    //         employee.getImage(),
    //         employee.getFirstName(),
    //         employee.getLastName(),
    //         employee.getPosition()));
    //   });

    //   result = new EmployeeResponseDTO.Employees(
    //       (int) employeesPage.getTotalElements(),
    //       employees);
    // } catch (Exception ex) {
    //   return ResponseEntity.status(400).body(ex.getMessage());
    // }
    return ResponseEntity.status(200).body("");
  }

  @GetMapping("/getEmployee")
  public @ResponseBody ResponseEntity<?> getEmployee(@RequestParam("employeeId") String employeeId) {
    // try {
    //   Employee employee = employeeRepository.getById(employeeId);
    //   if (employee == null) {
    //     throw new Exception("Can not find employee!");
    //   }

    //   EmployeeDTO.Employee result = new EmployeeDTO.Employee(
    //       employee.getId(),
    //       employee.getImage(),
    //       employee.getFirstName(),
    //       employee.getLastName(),
    //       employee.getPosition());

    //   return ResponseEntity.status(200).body(result);
    // } catch (Exception ex) {
    //   return ResponseEntity.status(400).body(ex.getMessage());
    // }
    return ResponseEntity.status(200).body("");

  }

  @PostMapping("/saveEmployee")
  public ResponseEntity<?> saveEmployee() {
    try {
      // EmployeeDTO.User employeeUser = newEmployee.user;
      // User user = null;

      // if (employeeUser != null) {
      //   User existingUser = !Utility.isStringEmpty(employeeUser.id)
      //       ? userRepository.findByUsername(employeeUser.username)
      //       : userRepository.findByUsernameAndIdNot(employeeUser.username, employeeUser.id);

      //   if (existingUser != null) {
      //     throw new Error("User with such username already exsts!");
      //   }

      //   user = !Utility.isStringEmpty(employeeUser.id)
      //       ? userRepository.getById(employeeUser.id)
      //       : new User();
      //   if (!Utility.isStringEmpty(employeeUser.username))
      //     user.setUsername(employeeUser.username);
      //   if (!Utility.isStringEmpty(employeeUser.password))
      //     user.setPassword(employeeUser.password);
      // }

      // Employee employee = !Utility.isStringEmpty(newEmployee.id)
      //     ? employeeRepository.getById(newEmployee.id)
      //     : new Employee();

      // if (!Utility.isStringEmpty(newEmployee.firstName))
      //   employee.setFirstName(newEmployee.firstName);
      // if (!Utility.isStringEmpty(newEmployee.lastName))
      //   employee.setLastName(newEmployee.lastName);
      // if (!Utility.isStringEmpty(newEmployee.image))
      //   employee.setImage(newEmployee.image);
      // if (!Utility.isStringEmpty(newEmployee.position))
      //   employee.setPosition(newEmployee.position);
      // if (user != null) {
      //   employee.setUser(user);
      //   userRepository.save(user);
      // }
      // employeeRepository.save(employee);

      return ResponseEntity.status(200).body("Success");
    } catch (Exception ex) {
      return ResponseEntity.status(400).body(ex.getMessage());
    }
  }

  @GetMapping("/getEmployeeById")
  public @ResponseBody ResponseEntity<?> getEmployeeById(@RequestParam("employeeId") String employeeId) {
    try {
      return ResponseEntity.status(200).body("Success");
    } catch (Exception ex) {
      return ResponseEntity.status(400).body(ex.getMessage());
    }
  }


  @DeleteMapping("/deleteEmployee")
  public @ResponseBody ResponseEntity<?> deleteEmployee(@RequestParam("userId") String userId) {
    if (userId == null) {
      return ResponseEntity.status(400).body("Not enough info provided!");
    }

    if (!userRepository.existsById(userId)) {
      return ResponseEntity.status(400).body("Usser not exists!");
    }

    try {
      employeeRepository.deleteById(employeeRepository.findByUserId(userId).getId());
      userRepository.deleteById(userId);
    } catch (Exception ex) {
      return ResponseEntity.status(400).body(ex.getMessage());
    }

    return ResponseEntity.status(200).body("Success");
  }
}
