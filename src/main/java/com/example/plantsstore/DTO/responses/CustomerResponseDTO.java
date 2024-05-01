package com.example.plantsstore.DTO.responses;

import java.util.List;

public class CustomerResponseDTO {
  public static class Customers {
    public Integer count;
    public List<Customer> suppliers;

    public Customers(Integer count, List<Customer> suppliers) {
      this.count = count;
      this.suppliers = suppliers;
    }

    public Customers() {}
  }

  public static class Customer {
    public String id;
    public String firstName;
    public String lastName;
    public String email;
    public String address;
    public String phone;

    public Customer(
      String id,
      String firstName,
      String lastName,
      String email,
      String address,
      String phone
    ) {
      this.id = id;
      this.firstName = firstName;
      this.lastName = lastName;
      this.email = email;
      this.address = address;
      this.phone = phone;
    }

    public Customer() {}
  }
}
