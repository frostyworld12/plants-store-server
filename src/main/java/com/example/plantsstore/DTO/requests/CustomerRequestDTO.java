package com.example.plantsstore.DTO.requests;

public class CustomerRequestDTO {
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
