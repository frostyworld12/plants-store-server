package com.example.plantsstore.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.plantsstore.DTO.requests.CustomerRequestDTO;
import com.example.plantsstore.model.Customer;
import com.example.plantsstore.repository.CustomerRepository;

@Controller
@RequestMapping(path="/customers")
public class CustomerController {
  @Autowired
  private CustomerRepository customerRepository;

  @PostMapping("/createCustomer")
  public @ResponseBody ResponseEntity<String> createCustomer(@RequestBody CustomerRequestDTO.Customer newCustomerDTO) {
    if (newCustomerDTO == null) {
      return ResponseEntity.status(400).body("Not enough info provided!");
    }

    // List<Customer> existingCustomers = customerRepository.findByNameEmailAddress(
    //   newCustomerDTO.name,
    //   newCustomerDTO.email,
    //   newCustomerDTO.address
    // );

    // if (!existingCustomers.isEmpty()) {
    //   return ResponseEntity.status(400).body("Such customer already exists");
    // }

    // Customer customerToCreate = new Customer();
    // customerToCreate.createCustomer(
    //   newCustomerDTO.name,
    //   newCustomerDTO.email,
    //   newCustomerDTO.phone,
    //   newCustomerDTO.address
    // );

    // customerRepository.save(customerToCreate);

    return ResponseEntity.status(200).body("Success");
  }

  @PostMapping("/updateCustomer")
  public @ResponseBody ResponseEntity<String> updateCustomer(@RequestBody CustomerRequestDTO.Customer newCustomerDTO) {
    if (newCustomerDTO.id == null) {
      return ResponseEntity.status(400).body("Not enough info provided!");
    }

    // List<Customer> existingCustomers = customerRepository.findByNameEmailAddress(
    //   newCustomerDTO.name,
    //   newCustomerDTO.email,
    //   newCustomerDTO.address
    // );

    // if (!existingCustomers.isEmpty()) {
    //   return ResponseEntity.status(400).body("Such customer already exists");
    // }

    // Customer customerToUpdate = new Customer();
    // customerToUpdate.createCustomer(
    //   newCustomerDTO.name,
    //   newCustomerDTO.email,
    //   newCustomerDTO.phone,
    //   newCustomerDTO.address
    // );
    // customerToUpdate.setId(newCustomerDTO.id);

    // customerRepository.save(customerToUpdate);

    return ResponseEntity.status(200).body("Success");
  }

  @GetMapping("/getCustomer")
  public @ResponseBody ResponseEntity<Customer> getCustomer(@RequestBody String customerId) {
    Customer customer = customerRepository.getById(customerId);
    return ResponseEntity.status(200).body(customer);
  }

  @GetMapping("/getCustomers")
  public @ResponseBody ResponseEntity<List<Customer>> getCustomers() {
    List<Customer> customers = customerRepository.findAll();
    return ResponseEntity.status(200).body(customers);
  }
}
