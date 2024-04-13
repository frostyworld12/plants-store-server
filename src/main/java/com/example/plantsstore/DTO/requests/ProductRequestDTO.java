package com.example.plantsstore.DTO.requests;

public class ProductRequestDTO {
  public static class Product {
    public String id;
    public String name;
    public String description;
    public Double price;
    public String image;
    public Integer quantity;
    public Supplier supplier;
  }

  public static class Supplier {
    public String name;
    public String contactPerson;
    public String email;
    public String phone;
  }
}
