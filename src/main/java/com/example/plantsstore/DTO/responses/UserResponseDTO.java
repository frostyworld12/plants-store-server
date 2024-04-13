package com.example.plantsstore.DTO.responses;

public class UserResponseDTO {
  public static class User {
    public String userId;
    public String employeeId;
    public String username;
    public String firstName;
    public String lastName;
    public String position;

    public User() {}

    public User(String userId, String employeeId, String username, String firstName, String lastName, String position) {
      this.userId = userId;
      this.employeeId = employeeId;
      this.username = username;
      this.firstName = firstName;
      this.lastName = lastName;
      this.position = position;
    }
  }
}
