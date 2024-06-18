package com.example.plantsstore.model;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
public class Document {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;
  @Column(columnDefinition = "MEDIUMBLOB")
  private String document;
  private String name;
  @Column(updatable = false)
  private LocalDateTime createdAt;

  public Document(String document, String name) {
    this.document = document;
    this.name = name;
    this.createdAt = LocalDateTime.now();
  }

  public Document() {
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getDocument() {
    return document;
  }

  public void setDocument(String document) {
    this.document = document;
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
}
