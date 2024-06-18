package com.example.plantsstore.DTO.responses;

import java.util.List;

public class SupplierResponse {
  public static class Supplier {
    private String id;
    private String name;
    private String contactPerson;
    private String phone;
    private String email;

    public Supplier(String id, String name, String contactPerson, String phone, String email) {
      this.id = id;
      this.name = name;
      this.contactPerson = contactPerson;
      this.phone = phone;
      this.email = email;
    }

    public Supplier() {
    }

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
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
  }

  public static class SupplierRegistration {
    private Supplier supplier;
    private UserResponse user;

    public SupplierRegistration(Supplier supplier, UserResponse user) {
      this.supplier = supplier;
      this.user = user;
    }

    public SupplierRegistration() {
    }

    public Supplier getSupplier() {
      return supplier;
    }

    public void setSupplier(Supplier supplier) {
      this.supplier = supplier;
    }

    public UserResponse getUser() {
      return user;
    }

    public void setUser(UserResponse user) {
      this.user = user;
    }
  }

  public static class SuppliersPagination {
    public Integer count;
    public List<Supplier> suppliers;

    public SuppliersPagination(Integer count, List<Supplier> suppliers) {
      this.count = count;
      this.suppliers = suppliers;
    }

    public SuppliersPagination() {
    }

    public Integer getCount() {
      return count;
    }

    public void setCount(Integer count) {
      this.count = count;
    }

    public List<Supplier> getSuppliers() {
      return suppliers;
    }

    public void setSuppliers(List<Supplier> suppliers) {
      this.suppliers = suppliers;
    }
  }
}
