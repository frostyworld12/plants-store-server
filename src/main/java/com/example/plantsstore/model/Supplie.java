package com.example.plantsstore.model;

import java.time.LocalDateTime;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Supplie {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  @ManyToOne
  @JoinColumn(name = "supplierId")
  private Supplier supplier;

  @ManyToOne
  @JoinColumn(name = "productId")
  private Product product;

  private Date date;
  private Double totalPrice;

  @Column(updatable = false)
  private LocalDateTime createdAt;

  public Supplie(
      Supplier supplier,
      Product product,
      Date date,
      Double totalPrice) {
    this.supplier = supplier;
    this.product = product;
    this.date = date;
    this.totalPrice = totalPrice;
  }

  public Supplie() {
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Supplier getSupplier() {
    return supplier;
  }

  public void setSupplier(Supplier supplier) {
    this.supplier = supplier;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public Double getTotalPrice() {
    return totalPrice;
  }

  public void setTotalPrice(Double totalPrice) {
    this.totalPrice = totalPrice;
  }
}
