package com.example.plantsstore.DTO.requests;

import java.util.List;

public class ProductRequest {
  public static class NewProduct {
    private String id;
    private String name;
    private String description;
    private String image;
    private List<String> suppliers;

    public NewProduct(String id, String name, String description, String image, List<String> suppliers) {
      this.id = id;
      this.name = name;
      this.description = description;
      this.image = image;
      this.suppliers = suppliers;
    }

    public NewProduct() {
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

    public String getImage() {
      return image;
    }

    public void setImage(String image) {
      this.image = image;
    }

    public List<String> getSuppliers() {
      return suppliers;
    }

    public void setSuppliers(List<String> suppliers) {
      this.suppliers = suppliers;
    }
  }
}
