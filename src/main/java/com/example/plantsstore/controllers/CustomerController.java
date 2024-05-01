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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.plantsstore.DTO.requests.CustomerRequestDTO;
import com.example.plantsstore.DTO.responses.CustomerResponseDTO;
import com.example.plantsstore.model.Customer;
import com.example.plantsstore.repository.CustomerRepository;
import com.example.plantsstore.utility.Utility;

@Controller
@RequestMapping(path="/customers")
public class CustomerController {
  @Autowired
  private CustomerRepository customerRepository;

@PostMapping("/saveCustomer")
  public @ResponseBody ResponseEntity<?> createSupplier(@RequestBody CustomerRequestDTO.Customer newCustomer) {
    List<Customer> existingCustomers = customerRepository.findByEmail(newCustomer.email);

    try {
      Boolean isCustomerExists = false;
      if (!existingCustomers.isEmpty()) {
        for (Customer customer : existingCustomers) {
          if (!customer.getId().equals(newCustomer.id)) {
            isCustomerExists = true;
            break;
          }
        }
      }

      if (isCustomerExists) {
        throw new Exception("Such customer already exists!");
      }

      Customer customer = !Utility.isStringEmpty(newCustomer.id) ? customerRepository.getById(newCustomer.id) : null;

      if (customer != null) {
        customer.setFirstName(newCustomer.firstName);
        customer.setLastName(newCustomer.lastName);
        customer.setAddress(newCustomer.address);
        customer.setEmail(newCustomer.email);
        customer.setPhone(newCustomer.phone);
      } else {
        customer = new Customer(
          newCustomer.firstName,
          newCustomer.lastName,
          newCustomer.email,
          newCustomer.phone,
          newCustomer.address
        );
      }

      customerRepository.save(customer);
    } catch (Exception ex) {
      return ResponseEntity.status(400).body(ex.getMessage());
    }

    return ResponseEntity.status(200).body("Success");
  }

  @GetMapping("/getCustomers")
  public @ResponseBody ResponseEntity<?> getCustomers(
    @RequestParam("limit") Integer limit,
    @RequestParam("offset") Integer offset,
    @RequestParam(value="query", required=false) String query
  ) {
    CustomerResponseDTO.Customers result = new CustomerResponseDTO.Customers();
    try {
      Pageable pageable = PageRequest.of(offset, limit);
      Page<Customer> customersPage = Utility.isStringEmpty(query)
        ? customerRepository.findAllByOrderByCreatedAtDesc(pageable)
        : customerRepository.findByLastNameContainingOrderByCreatedAtDesc(query, query, pageable);

      List<Customer> customersContent = customersPage.getContent();

      List<CustomerResponseDTO.Customer> customers = new ArrayList<>();
      customersContent.forEach(customer -> {
        customers.add(new CustomerResponseDTO.Customer(
          customer.getId(),
          customer.getFirstName(),
          customer.getLastName(),
          customer.getEmail(),
          customer.getAddress(),
          customer.getPhone()
        ));
      });

      result = new CustomerResponseDTO.Customers(
        (int)customersPage.getTotalElements(),
        customers
      );
    } catch (Exception ex) {
      return ResponseEntity.status(400).body(ex.getMessage());
    }
    return ResponseEntity.status(200).body(result);
  }

  @DeleteMapping("/deleteCustomer")
  public @ResponseBody ResponseEntity<?> deleteSupplier(@RequestParam("customerId") String customerId) {
    if (customerId == null) {
      return ResponseEntity.status(400).body("Not enough info provided!");
    }

    if (!customerRepository.existsById(customerId)) {
      return ResponseEntity.status(400).body("Supplier not exists!");
    }

    try {
      customerRepository.deleteById(customerId);
    } catch (Exception ex) {
      return ResponseEntity.status(400).body(ex.getMessage());
    }

    return ResponseEntity.status(200).body("Success");
  }
}
