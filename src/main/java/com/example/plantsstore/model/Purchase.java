package com.example.plantsstore.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Purchase {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  @ManyToOne
  @JoinColumn(name = "customerId")
  private Customer customer;

  @ManyToOne
  @JoinColumn(name = "employeeId")
  private Employee employee;

  private Date orderDate;
  private Date deliveryDate;
  private Double totalAmount;

  public Purchase() {}

  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }

  public Customer getCustomer() {
    return customer;
  }
  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public Employee getEmployee() {
    return employee;
  }
  public void setEmployee(Employee employee) {
    this.employee = employee;
  }

  public Date getOrderDate() {
    return orderDate;
  }
  public void setOrderDate(Date orderDate) {
    this.orderDate = orderDate;
  }

  public Date getDeliveryDate() {
    return deliveryDate;
  }
  public void setDeliveryDate(Date deliveryDate) {
    this.deliveryDate = deliveryDate;
  }

  public Double getTotalAmount() {
    return totalAmount;
  }
  public void setTotalAmount(Double totalAmount) {
    this.totalAmount = totalAmount;
  }

  public void createPurchase(
    Customer customer,
    Employee employee,
    Double totalAmount,
    Date orderDate,
    Date deliveryDate
  ) {
    this.customer     = customer;
    this.employee     = employee;

    this.totalAmount  = totalAmount;

    this.orderDate    = orderDate;
    this.deliveryDate = deliveryDate;
  }
}
