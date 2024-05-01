package com.example.plantsstore.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.plantsstore.DTO.requests.EmployeeRequestDTO;
import com.example.plantsstore.model.Employee;
import com.example.plantsstore.model.User;
import com.example.plantsstore.DTO.responses.EmployeeResponseDTO;
import com.example.plantsstore.repository.EmployeeRepository;
import com.example.plantsstore.repository.UserRepository;
import com.example.plantsstore.utility.Utility;

import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping(path="/employees")
public class EmployeeController {
  @Autowired
  private EmployeeRepository employeeRepository;
  @Autowired
  private UserRepository userRepository;

  @PostMapping("/saveEmployee")
  public @ResponseBody ResponseEntity<?> saveEmployee(@RequestBody EmployeeRequestDTO.Employee newEmployee) {
    User existingUser = userRepository.findByUsernameAndIdNot(newEmployee.username, newEmployee.userId);

    try {
      if (existingUser != null) {
        throw new Exception("Employee with such username already exists!");
      }

      User user = !Utility.isStringEmpty(newEmployee.userId) ? userRepository.getById(newEmployee.userId) : null;
      Employee employee = !Utility.isStringEmpty(newEmployee.userId) ? employeeRepository.findByUserId(newEmployee.userId) : null;

      if (user != null) {
        user.setUsername(newEmployee.username);
        if (Utility.isStringEmpty(newEmployee.password)) {
          user.setPassword(newEmployee.password);
        }

        employee.setImage(newEmployee.image);
        employee.setFirstName(newEmployee.firstName);
        employee.setLastName(newEmployee.lastName);
        employee.setPosition(newEmployee.position);
      } else {
        user = new User(
          newEmployee.username,
          newEmployee.password
        );

        employee = new Employee(
          user,
          newEmployee.image,
          newEmployee.firstName,
          newEmployee.lastName,
          newEmployee.position
        );
      }

      userRepository.save(user);
      employeeRepository.save(employee);
    } catch (Exception ex) {
      return ResponseEntity.status(400).body(ex.getMessage());
    }

    return ResponseEntity.status(200).body("Success");
  }

  @GetMapping("/getEmployees")
  public @ResponseBody ResponseEntity<?> getEmployee(
    @RequestParam("limit") Integer limit,
    @RequestParam("offset") Integer offset,
    @RequestParam(value="query", required=false) String query
  ) {
    EmployeeResponseDTO.Employees result = new EmployeeResponseDTO.Employees();
    try {
      Pageable pageable = PageRequest.of(offset, limit);
      Page<Employee> employeesPage = Utility.isStringEmpty(query)
        ? employeeRepository.findAllByOrderByCreatedAtDesc(pageable)
        : employeeRepository.findByFirstNameContainingOrderByCreatedAtDesc(query, pageable);

      List<Employee> employeeContent = employeesPage.getContent();
      List<EmployeeResponseDTO.Employee> employees = new ArrayList<>();
      employeeContent.forEach(employee -> {
        employees.add(new EmployeeResponseDTO.Employee(
          employee.getUser().getId(),
          employee.getId(),
          employee.getUser().getUsername(),
          employee.getImage(),
          employee.getFirstName(),
          employee.getLastName(),
          employee.getPosition()
        ));
      });

      result = new EmployeeResponseDTO.Employees(
        (int)employeesPage.getTotalElements(),
        employees
      );
    } catch (Exception ex) {
      return ResponseEntity.status(400).body(ex.getMessage());
    }
    return ResponseEntity.status(200).body(result);
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
