package com.example.plantsstore.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ProductsInPurchase {
  @Id
  @GeneratedValue(strategy=GenerationType.UUID)
  private String id;

  private Integer quantity;
  private Double subtotal;

  @ManyToOne
  @JoinColumn(name = "purchaseId")
  private Purchase purchase;

  @ManyToOne
  @JoinColumn(name = "productId")
  private Product product;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public Double getSubtotal() {
    return subtotal;
  }

  public void setSubtotal(Double subtotal) {
    this.subtotal = subtotal;
  }

  public Purchase getPurchase() {
    return purchase;
  }

  public void setPurchase(Purchase purchase) {
    this.purchase = purchase;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public void createProductInPurchase(
    Integer quantity,
    Double subtotal,
    Product product,
    Purchase purchase
  ) {
    this.quantity = quantity;
    this.subtotal = subtotal;
    this.product = product;
    this.purchase = purchase;
  }
}
