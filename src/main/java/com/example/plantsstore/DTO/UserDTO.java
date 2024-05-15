package com.example.plantsstore.DTO;

public class UserDTO {
  public static class User {
    public String username;
    public String password;
    public String userType;

    public User() {
    }

    public User(String username, String password, String userType) {
      this.username = username;
      this.password = password;
      this.userType = userType;
    }
  }
}
