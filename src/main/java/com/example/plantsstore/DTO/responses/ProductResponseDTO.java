package com.example.plantsstore.DTO.responses;

import java.util.List;

public class ProductResponseDTO {
  public static class Products {
    public Integer count;
    public List<Product> products;

    public Products(Integer count, List<Product> products) {
      this.count = count;
      this.products = products;
    }

    public Products() {
    }
  }

  public static class Product {
    public String id;
    public String name;
    public String description;
    public Double price;
    public String image;
    public List<SupplierResponseDTO.Supplier> suppliers;

    public Product(String id, String name, String description, Double price, String image, List<SupplierResponseDTO.Supplier> suppliers) {
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
