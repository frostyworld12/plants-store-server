package com.example.plantsstore.model;

import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Employee {
  @Id
  @GeneratedValue(strategy=GenerationType.UUID)
  private String id;

  @OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true, targetEntity = User.class)
  @JoinColumn(name="userId", referencedColumnName = "id", nullable = false)
  private User user;

  @Column(columnDefinition="MEDIUMBLOB")
  private String image;

  private String firstName;
  private String lastName;
  private String position;

  @Column(updatable = false)
  private LocalDateTime createdAt;

  public Employee(User user, String image, String firstName, String lastName, String position) {
    this.user = user;
    this.image = image;
    this.firstName = firstName;
    this.lastName = lastName;
    this.position = position;
    this.createdAt = LocalDateTime.now();
  }

  public Employee() {}

  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }

  public User getUser() {
    return user;
  }
  public void setUser(User user) {
    this.user = user;
  }

  public String getImage() {
    return image;
  }
  public void setImage(String image) {
    this.image = image;
  }

  public String getFirstName() {
    return firstName;
  }
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getPosition() {
    return position;
  }
  public void setPosition(String position) {
    this.position = position;
  }
}
