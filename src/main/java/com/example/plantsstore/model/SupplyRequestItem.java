package com.example.plantsstore.model;

import jakarta.persistence.*;

@Entity
public class SupplyRequestItem {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  @ManyToOne
  private SupplyRequest supplyRequest;

  @ManyToOne
  private Product product;

  private Integer amount;
  private Double costPerUnit;
  private Double total;

  public SupplyRequestItem(SupplyRequest supplyRequest, Product product, Integer amount, Double costPerUnit,
      Double total) {
    this.supplyRequest = supplyRequest;
    this.product = product;
    this.amount = amount;
    this.costPerUnit = costPerUnit;
    this.total = total;
  }

  public SupplyRequestItem() {
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public SupplyRequest getSupplyRequest() {
    return supplyRequest;
  }

  public void setSupplyRequest(SupplyRequest supplyRequest) {
    this.supplyRequest = supplyRequest;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public Integer getAmount() {
    return amount;
  }

  public void setAmount(Integer amount) {
    this.amount = amount;
  }

  public Double getCostPerUnit() {
    return costPerUnit;
  }

  public void setCostPerUnit(Double costPerUnit) {
    this.costPerUnit = costPerUnit;
  }

  public Double getTotal() {
    return total;
  }

  public void setTotal(Double total) {
    this.total = total;
  }
}
