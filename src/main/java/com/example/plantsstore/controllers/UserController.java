package com.example.plantsstore.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.plantsstore.DTO.requests.UserRequestDTO;
import com.example.plantsstore.DTO.responses.UserResponseDTO;
import com.example.plantsstore.model.Employee;
import com.example.plantsstore.model.User;
import com.example.plantsstore.repository.EmployeeRepository;
import com.example.plantsstore.repository.UserRepository;
import com.example.plantsstore.utility.Utility;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping(path="/users")
public class UserController {
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private EmployeeRepository employeeRepository;

  @PostMapping("/createUser")
  public @ResponseBody ResponseEntity<String> createUser(@RequestBody UserRequestDTO.User newUserDTO) {
    User user = userRepository.findByUsername(newUserDTO.username);
    if (user != null) {
      return ResponseEntity.status(400).body("User with such username already exists!");
    }

    User userToCreate = new User();
    userToCreate.createUser(newUserDTO.username, newUserDTO.password);
    userRepository.save(userToCreate);

    Employee newEmployee = new Employee();
    newEmployee.createEmployee(newUserDTO.firstName, newUserDTO.lastName, newUserDTO.position, userToCreate);
    employeeRepository.save(newEmployee);

    return ResponseEntity.status(200).body("Success");
  }

  @PostMapping("/login")
  public @ResponseBody ResponseEntity<String> loginUser(@RequestBody UserRequestDTO.User userDTO) {
    User user = userRepository.findByUsername(userDTO.username);
    if (user == null) {
      return ResponseEntity.status(400).body("Could not find user!");
    }

    if (!user.getPassword().equals(userDTO.password)) {
      return ResponseEntity.status(400).body("Incorrect email or password!");
    }

    return ResponseEntity.status(200).body("Success");
  }

  @GetMapping("/getUsers")
  public @ResponseBody ResponseEntity<List<UserResponseDTO.User>> getUsers() {
    List<Employee> employees = new ArrayList<>(employeeRepository.findAll());

    List<UserResponseDTO.User> employeesUsers = new ArrayList<>();
    employees.forEach(employee -> {
      employeesUsers.add(new UserResponseDTO.User(
        employee.getUser().getId(),
        employee.getId(),
        employee.getUser().getUsername(),
        employee.getFirstName(),
        employee.getLastName(),
        employee.getPosition()
      ));
    });

    return ResponseEntity.status(200).body(employeesUsers);
  }

  @DeleteMapping("/deleteUser/{userId}")
  public @ResponseBody ResponseEntity<String> deleteUser(@PathVariable("userId") String userId) {
    System.out.println("userId: " + userId);
    Employee employee = employeeRepository.findByUserId(userId);
    if (employee != null) {
      employeeRepository.deleteById(employee.getId());
      userRepository.deleteById(employee.getUser().getId());
    } else {
      return ResponseEntity.status(400).body("Such employee does not exist!");
    }
    return ResponseEntity.status(200).body("Success");
  }

  @PostMapping("/updateUser")
  public @ResponseBody ResponseEntity<String> updateUser(@RequestBody UserRequestDTO.User newUserDTO) {
    if (Utility.isStringEmpty(newUserDTO.userId)) {
      return ResponseEntity.status(400).body("Not enough info provided!");
    }

    if (userRepository.findById(newUserDTO.userId).isPresent()) {
      User userToUpdate = userRepository.getById(newUserDTO.userId);
      Employee employeeToUpdate = employeeRepository.findByUserId(newUserDTO.userId);

      Boolean isUserUpdated = false;
      Boolean isEmployeeUpdated = false;

      if (!userToUpdate.getUsername().equals(newUserDTO.username) && !Utility.isStringEmpty(newUserDTO.username)) {
        if (userRepository.findByUsername(newUserDTO.username) != null) {
          return ResponseEntity.status(400).body("User with such username already exists!");
        }
        isUserUpdated = true;
        userToUpdate.setUsername(newUserDTO.username);
      }
      if (!userToUpdate.getPassword().equals(newUserDTO.password) && !Utility.isStringEmpty(newUserDTO.password)) {
        isUserUpdated = true;
        userToUpdate.setPassword(newUserDTO.password);
      }

      if (!employeeToUpdate.getFirstName().equals(newUserDTO.firstName) && !Utility.isStringEmpty(newUserDTO.firstName)) {
        isEmployeeUpdated = true;
        employeeToUpdate.setFirstName(newUserDTO.firstName);
      }

      if (!employeeToUpdate.getLastName().equals(newUserDTO.lastName) && !Utility.isStringEmpty(newUserDTO.lastName)) {
        isEmployeeUpdated = true;
        employeeToUpdate.setLastName(newUserDTO.lastName);
      }

      if (!employeeToUpdate.getPosition().equals(newUserDTO.position) && !Utility.isStringEmpty(newUserDTO.position)) {
        isEmployeeUpdated = true;
        employeeToUpdate.setPosition(newUserDTO.position);
      }

      if (isEmployeeUpdated) {
        employeeRepository.save(employeeToUpdate);
      }

      if (isUserUpdated) {
        userRepository.save(userToUpdate);
      }
    } else {
      return ResponseEntity.status(400).body("Such user does not exist!");
    }
    return ResponseEntity.status(200).body("Success");
  }
}
