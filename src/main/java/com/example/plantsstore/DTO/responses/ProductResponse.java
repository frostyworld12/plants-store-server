package com.example.plantsstore.DTO.responses;

import java.util.List;

public class ProductResponse {
  public static class Product {
    private String id;
    private String name;
    private String description;
    private Double price;
    private String image;
    private List<SupplierResponse.Supplier> suppliers;

    public Product(
        String id,
        String name,
        String description,
        Double price,
        String image,
        List<SupplierResponse.Supplier> suppliers) {
      this.id = id;
      this.name = name;
      this.description = description;
      this.price = price;
      this.image = image;
      this.suppliers = suppliers;
    }

    public Product() {
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

    public String getDescription() {
      return description;
    }

    public void setDescription(String description) {
      this.description = description;
    }

    public Double getPrice() {
      return price;
    }

    public void setPrice(Double price) {
      this.price = price;
    }

    public String getImage() {
      return image;
    }

    public void setImage(String image) {
      this.image = image;
    }

    public List<SupplierResponse.Supplier> getSuppliers() {
      return suppliers;
    }

    public void setSuppliers(List<SupplierResponse.Supplier> suppliers) {
      this.suppliers = suppliers;
    }
  }

  public static class ProductsPagination {
    private Integer count;
    private List<Product> products;

    public ProductsPagination(Integer count, List<Product> products) {
      this.count = count;
      this.products = products;
    }

    public ProductsPagination() {
    }

    public Integer getCount() {
      return count;
    }

    public void setCount(Integer count) {
      this.count = count;
    }

    public List<Product> getProducts() {
      return products;
    }

    public void setProducts(List<Product> products) {
      this.products = products;
    }
  }
}
