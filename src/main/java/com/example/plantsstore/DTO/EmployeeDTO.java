package com.example.plantsstore.DTO;

public class EmployeeDTO {
  public static class Employee {
    public String id;
    public String image;
    public String firstName;
    public String lastName;
    public String position;
    public User user;

    public Employee() {}

    public Employee(String id, String image, String firstName, String lastName, String position, User user) {
      this.id = id;
      this.image = image;
      this.firstName = firstName;
      this.lastName = lastName;
      this.position = position;
      this.user = user;
    }

    public Employee(String id, String image, String firstName, String lastName, String position) {
      this.id = id;
      this.image = image;
      this.firstName = firstName;
      this.lastName = lastName;
      this.position = position;
    }
  }

  public static class User {
    public String id;
    public String username;
    public String password;
    public String role;

    public User() {
    }

    public User(String username, String role) {
      this.username = username;
      this.role = role;
    }

    public User(String id, String username, String password) {
      this.id = id;
      this.username = username;
      this.password = password;
    }
  }
}
