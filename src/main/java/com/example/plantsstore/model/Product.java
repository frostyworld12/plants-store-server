package com.example.plantsstore.model;

import java.time.LocalDateTime;
import java.util.*;

import jakarta.persistence.*;

@Entity
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  private String name;
  @Column(columnDefinition = "LONGTEXT")
  private String description;
  private Double price;

  @Column(columnDefinition = "MEDIUMBLOB")
  private String image;

  @Column(updatable = false)
  private LocalDateTime createdAt;

  @ManyToMany
  private List<Supplier> suppliers;

  public Product(String name, String description, Double price, String image, List<Supplier> suppliers) {
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

  public List<Supplier> getSuppliers() {
    return suppliers;
  }
  public void setSuppliers(List<Supplier> suppliers) {
    this.suppliers = suppliers;
  }
}
