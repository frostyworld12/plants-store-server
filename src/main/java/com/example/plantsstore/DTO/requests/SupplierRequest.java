package com.example.plantsstore.DTO.requests;

public class SupplierRequest {
  public static class SupplierRegistration {
    private String name;
    private String contactPerson;
    private String phone;
    private String email;
    private String username;
    private String password;

    public SupplierRegistration(
      String name,
      String contactPerson,
      String phone,
      String email,
      String password,
      String username
    ) {
      this.name = name;
      this.contactPerson = contactPerson;
      this.phone = phone;
      this.email = email;
      this.password = password;
      this.username = username;
    }

    public SupplierRegistration() {
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getContactPerson() {
      return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
      this.contactPerson = contactPerson;
    }

    public String getPhone() {
      return phone;
    }

    public void setPhone(String phone) {
      this.phone = phone;
    }

    public String getEmail() {
      return email;
    }

    public void setEmail(String email) {
      this.email = email;
    }

    public String getUsername() {
      return username;
    }

    public void setUsername(String username) {
      this.username = username;
    }

    public String getPassword() {
      return password;
    }

    public void setPassword(String password) {
      this.password = password;
    }
  }
}

