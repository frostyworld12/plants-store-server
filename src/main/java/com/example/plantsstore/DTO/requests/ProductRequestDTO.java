package com.example.plantsstore.DTO.requests;

import java.util.List;

public class ProductRequestDTO {
  public static class Product {
    public String id;
    public String name;
    public String description;
    public Double price;
    public String image;
    public List<String> suppliers;

    public Product(String id, String name, String description, Double price, String image, List<String> suppliers) {
      this.id = id;
      this.name = name;
      this.description = description;
      this.price = price;
      this.image = image;
      this.suppliers = suppliers;
    }

    public Product() {
    }
  }
}
