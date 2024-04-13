package com.example.plantsstore.DTO.responses;

import java.util.Date;
import java.util.List;

public class PurchaseResponseDTO {
  public static class Purchase {
    public String id;
    public Employee employee;
    public Customer customer;
    public List<ProductInPurchase> products;
    public Date deliveryDate;
    public Date orderDate;
    public Double totalAmount;

    public Purchase() {}

    public Purchase(
      String id,
      Employee employee,
      Customer customer,
      Date deliveryDate,
      Date orderDate,
      Double totalAmount
    ) {
      this.id = id;
      this.employee = employee;
      this.customer = customer;
      this.deliveryDate = deliveryDate;
      this.orderDate = orderDate;
      this.totalAmount = totalAmount;
    }

    public Purchase(
      String id,
      Employee employee,
      Customer customer,
      List<ProductInPurchase> products,
      Date deliveryDate,
      Date orderDate,
      Double totalAmount
    ) {
      this.id = id;
      this.employee = employee;
      this.customer = customer;
      this.products = products;
      this.deliveryDate = deliveryDate;
      this.orderDate = orderDate;
      this.totalAmount = totalAmount;
    }
  }

  public static class ProductInPurchase {
    public String id;
    public String name;
    public Double price;
    public Integer quantity;
    public Double subtotal;

    public ProductInPurchase() {}

    public ProductInPurchase(
      String id,
      String name,
      Double price,
      Integer quantity,
      Double subtotal
    ) {
      this.id = id;
      this.name = name;
      this.price = price;
      this.quantity = quantity;
      this.subtotal = subtotal;
    }
  }

  public static class Employee {
    public String id;
    public String name;
    public String username;
    public String position;

    public Employee() {}

    public Employee(
      String id,
      String name,
      String username,
      String position
    ) {
      this.id = id;
      this.name = name;
      this.username = username;
      this.position = position;
    }
  }

  public static class Customer {
    public String id;
    public String name;
    public String email;
    public String address;

    public Customer() {}

    public Customer(
      String id,
      String name,
      String email,
      String address
    ) {
      this.id = id;
      this.name = name;
      this.email = email;
      this.address = address;
    }
  }
}
