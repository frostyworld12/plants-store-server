package com.example.plantsstore.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.plantsstore.DTO.requests.EmployeeRequestDTO;
import com.example.plantsstore.DTO.responses.EmployeeResponseDTO;
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
  // @Autowired
  // private UserRepository userRepository;
  // @Autowired
  // private EmployeeRepository employeeRepository;

  // @PostMapping("/createUser")
  // public @ResponseBody ResponseEntity<String> createUser(@RequestBody EmployeeRequestDTO.User newUserDTO) {
  //   User user = userRepository.findByUsername(newUserDTO.username);
  //   if (user != null) {
  //     return ResponseEntity.status(400).body("User with such username already exists!");
  //   }

  //   User userToCreate = new User();
  //   userToCreate.createUser(newUserDTO.username, newUserDTO.password);
  //   userRepository.save(userToCreate);

  //   Employee newEmployee = new Employee();
  //   newEmployee.createEmployee(newUserDTO.image, newUserDTO.firstName, newUserDTO.lastName, newUserDTO.position, userToCreate);
  //   employeeRepository.save(newEmployee);

  //   return ResponseEntity.status(200).body("Success");
  // }

  // @PostMapping("/login")
  // public @ResponseBody ResponseEntity<String> loginUser(@RequestBody EmployeeRequestDTO.User userDTO) {
  //   User user = userRepository.findByUsername(userDTO.username);
  //   if (user == null) {
  //     return ResponseEntity.status(400).body("Could not find user!");
  //   }

  //   if (!user.getPassword().equals(userDTO.password)) {
  //     return ResponseEntity.status(400).body("Incorrect email or password!");
  //   }

  //   return ResponseEntity.status(200).body("Success");
  // }

  // @GetMapping("/getUsers")
  // public @ResponseBody ResponseEntity<UserResponseDTO.UsersRecords> getUsers(@RequestParam("limit") Integer limit, @RequestParam("offset") Integer offset) {
  //   Pageable pageable = PageRequest.of(offset, limit);
  //   Page<Employee> employeesPage = employeeRepository.findAll(pageable);
  //   List<Employee> employees = employeesPage.getContent();

  //   List<UserResponseDTO.User> employeesUsers = new ArrayList<>();
  //   employees.forEach(employee -> {
  //     employeesUsers.add(new UserResponseDTO.User(
  //       employee.getUser().getId(),
  //       employee.getId(),
  //       employee.getUser().getUsername(),
  //       employee.getImage(),
  //       employee.getFirstName(),
  //       employee.getLastName(),
  //       employee.getPosition()
  //     ));
  //   });

  //   UserResponseDTO.UsersRecords result = new UserResponseDTO.UsersRecords(employeesUsers, (int)employeesPage.getTotalElements());
  //   return ResponseEntity.status(200).body(result);
  // }

  // @DeleteMapping("/deleteUser")
  // public @ResponseBody ResponseEntity<String> deleteUser(@RequestParam("limit") String userId) {
  //   System.out.println("userId: " + userId);
  //   Employee employee = employeeRepository.findByUserId(userId);
  //   if (employee != null) {
  //     employeeRepository.deleteById(employee.getId());
  //     userRepository.deleteById(employee.getUser().getId());
  //   } else {
  //     return ResponseEntity.status(400).body("Such employee does not exist!");
  //   }
  //   return ResponseEntity.status(200).body("Success");
  // }

  // @PostMapping("/updateUser")
  // public @ResponseBody ResponseEntity<String> updateUser(@RequestBody EmployeeRequestDTO.User newUserDTO) {
  //   if (Utility.isStringEmpty(newUserDTO.userId)) {
  //     return ResponseEntity.status(400).body("Not enough info provided!");
  //   }

  //   if (userRepository.findById(newUserDTO.userId).isPresent()) {
  //     User userToUpdate = userRepository.getById(newUserDTO.userId);
  //     Employee employeeToUpdate = employeeRepository.findByUserId(newUserDTO.userId);

  //     User alreadyExistingUser = userRepository.findByUsername(newUserDTO.username);
  //     if (alreadyExistingUser != null && alreadyExistingUser.getId() != userToUpdate.getId()) {
  //       return ResponseEntity.status(400).body("User with such username already exists!");
  //     }

  //     employeeToUpdate.setImage(newUserDTO.image);
  //     employeeToUpdate.setFirstName(newUserDTO.firstName);
  //     employeeToUpdate.setLastName(newUserDTO.lastName);
  //     employeeToUpdate.setPosition(newUserDTO.position);
  //     userToUpdate.setUsername(newUserDTO.username);
  //     if (!Utility.isStringEmpty(newUserDTO.password)) {
  //       userToUpdate.setPassword(newUserDTO.password);
  //     }

  //     employeeRepository.save(employeeToUpdate);
  //     userRepository.save(userToUpdate);

  //   } else {
  //     return ResponseEntity.status(400).body("Such user does not exist!");
  //   }
  //   return ResponseEntity.status(200).body("Success");
  // }
}
