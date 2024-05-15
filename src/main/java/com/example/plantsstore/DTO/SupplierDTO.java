package com.example.plantsstore.DTO;

public class SupplierDTO {
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

    public Supplier(String id, String email, String name, String phone, String contactPerson) {
      this.id = id;
      this.email = email;
      this.name = name;
      this.phone = phone;
      this.contactPerson = contactPerson;
    }
  }

  public static class User {
    public String username;
    public String password;
    public String role;

    public User() {
    }

    public User(String username, String password, String role) {
      this.username = username;
      this.password = password;
      this.role = role;
    }

    public User(String username, String role) {
      this.username = username;
      this.role = role;
    }
  }
}
