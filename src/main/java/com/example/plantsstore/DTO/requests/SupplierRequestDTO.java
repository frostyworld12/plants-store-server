package com.example.plantsstore.DTO.requests;

public class SupplierRequestDTO {
  public static class Supplier {
    public String id;
    public String email;
    public String name;
    public String phone;
    public String contactPerson;
    public User user;

    public Supplier() {
    }

    public Supplier(String id, String email, String name, String phone, String contactPerson, User user) {
      this.id = id;
      this.email = email;
      this.name = name;
      this.phone = phone;
      this.contactPerson = contactPerson;
      this.user = user;
    }
  }

  public static class User {
    public String username;
    public String password;

    public User() {
    }

    public User(String username, String password) {
      this.username = username;
      this.password = password;
    }
  }
}
