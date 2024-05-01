package com.example.plantsstore.DTO.requests;

public class SupplierRequestDTO {
  public static class Supplier {
    public String id;
    public String email;
    public String name;
    public String phone;
    public String contactPerson;

    public Supplier() {}

    public Supplier(String id, String email, String name, String phone, String contactPerson) {
      this.id = id;
      this.email = email;
      this.name = name;
      this.phone = phone;
      this.contactPerson = contactPerson;
    }
  }
}
