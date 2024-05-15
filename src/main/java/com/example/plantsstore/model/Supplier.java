package com.example.plantsstore.model;

import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Supplier {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  private String name;
  private String contactPerson;
  private String email;
  private String phone;

  @OneToOne
  @JoinColumn(name = "userId", referencedColumnName = "id")
  private User user;

  @Column(updatable = false)
  private LocalDateTime createdAt;

  public Supplier(
    String name,
    String contactPerson,
    String email,
    String phone,
    User user
  ) {
    this.name = name;
    this.contactPerson = contactPerson;
    this.email = email;
    this.phone = phone;
    this.user = user;
    this.createdAt = LocalDateTime.now();
  }

  public Supplier() {
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

  public String getContactPerson() {
    return contactPerson;
  }

  public void setContactPerson(String contactPerson) {
    this.contactPerson = contactPerson;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
}
