package com.example.plantsstore.DTO.responses;

import java.util.List;

public class SupplierResponseDTO {
  public static class Suppliers {
    public Integer count;
    public List<Supplier> suppliers;

    public Suppliers(Integer count, List<Supplier> suppliers) {
      this.count = count;
      this.suppliers = suppliers;
    }

    public Suppliers() {
    }
  }

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
