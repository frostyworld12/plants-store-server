package com.example.plantsstore.DTO.responses;

public class ProductResponseDTO {
  public static class Product {
    public String id;
    public String name;
    public String description;
    public Double price;
    public String image;
    public Integer quantity;
    public Supplier supplier;

    public Product(
      String id,
      String name,
      String description,
      Double price,
      String image,
      Integer quantity,
      Supplier supplier
    ) {
      this.id          = id;
      this.name        = name;
      this.description = description;
      this.price       = price;
      this.image       = image;
      this.quantity    = quantity;
      this.supplier    = supplier;
    }

  }

  public static class Supplier {
    public String id;
    public String name;
    public String contactPerson;
    public String email;
    public String phone;

    public Supplier(
      String id,
      String name,
      String contactPerson,
      String email
    ) {
      this.id            = id;
      this.name          = name;
      this.contactPerson = contactPerson;
      this.email         = email;
    }
  }
}
