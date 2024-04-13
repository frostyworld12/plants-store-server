package com.example.plantsstore.model;

import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Inventory {
  @Id
  @GeneratedValue(strategy=GenerationType.UUID)
  private String id;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name="productId", referencedColumnName = "id")
  private Product product;

  private Integer quantityAvailable;
  private Date lastRestockDate;

  public Inventory(
    Product product,
    Integer quantityAvailable,
    Date lastRestockDate
  ) {
    this.product           = product;
    this.quantityAvailable = quantityAvailable;
    this.lastRestockDate   = lastRestockDate;
  }

  public Inventory() {}

  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public Product getProduct() {
    return product;
  }
  public void setProduct(Product product) {
    this.product = product;
  }
  public Integer getQuantityAvailable() {
    return quantityAvailable;
  }
  public void setQuantityAvailable(Integer quantityAvailable) {
    this.quantityAvailable = quantityAvailable;
  }
  public Date getLastRestockDate() {
    return lastRestockDate;
  }
  public void setLastRestockDate(Date lastRestockDate) {
    this.lastRestockDate = lastRestockDate;
  }
}
