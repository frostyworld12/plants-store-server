package com.example.plantsstore.model;

import java.time.LocalDateTime;
import java.util.Random;

import jakarta.persistence.*;

@Entity
public class SupplyRequest {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  @Column(updatable = false)
  private LocalDateTime createdAt;

  @Column(updatable = false)
  private String name;

  @OneToOne
  private SupplyRequest parentRequest;

  private Boolean approvedByEmployee;
  private Boolean approvedBySupplier;

  private Boolean hasChild;

  @ManyToOne()
  private Supplier supplier;

  @ManyToOne()
  private Employee employee;

  @ManyToOne()
  private Document document;

  public SupplyRequest(Boolean approvedByEmployee, Boolean approvedBySupplier,
      Supplier supplier, Employee employee, Document document) {
    this.approvedByEmployee = approvedByEmployee;
    this.approvedBySupplier = approvedBySupplier;
    this.supplier = supplier;
    this.employee = employee;
    this.document = document;
    this.createdAt = LocalDateTime.now();

    Random rand = new Random(System.currentTimeMillis());
    this.name = "D-" + ((1 + rand.nextInt(2)) * 10000 + rand.nextInt(10000));
  }

  public SupplyRequest(Boolean approvedByEmployee, Boolean approvedBySupplier,
      Supplier supplier, Employee employee, Document document, SupplyRequest parentRequest) {
    this.approvedByEmployee = approvedByEmployee;
    this.approvedBySupplier = approvedBySupplier;
    this.supplier = supplier;
    this.employee = employee;
    this.document = document;
    this.parentRequest = parentRequest;
    this.createdAt = LocalDateTime.now();

    Random rand = new Random(System.currentTimeMillis());
    this.name = "D-" + ((1 + rand.nextInt(2)) * 10000 + rand.nextInt(10000));
  }

  public SupplyRequest() {
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

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public Boolean getApprovedByEmployee() {
    return approvedByEmployee;
  }

  public void setApprovedByEmployee(Boolean approvedByEmployee) {
    this.approvedByEmployee = approvedByEmployee;
  }

  public Boolean getApprovedBySupplier() {
    return approvedBySupplier;
  }

  public void setApprovedBySupplier(Boolean approvedBySupplier) {
    this.approvedBySupplier = approvedBySupplier;
  }

  public Supplier getSupplier() {
    return supplier;
  }

  public void setSupplier(Supplier supplier) {
    this.supplier = supplier;
  }

  public Employee getEmployee() {
    return employee;
  }

  public void setEmployee(Employee employee) {
    this.employee = employee;
  }

  public Document getDocument() {
    return document;
  }

  public void setDocument(Document document) {
    this.document = document;
  }

  public SupplyRequest getParentRequest() {
    return parentRequest;
  }

  public void setParentRequest(SupplyRequest parentRequest) {
    this.parentRequest = parentRequest;
  }

  public Boolean getHasChild() {
    return hasChild;
  }

  public void setHasChild(Boolean hasChild) {
    this.hasChild = hasChild;
  }
}
