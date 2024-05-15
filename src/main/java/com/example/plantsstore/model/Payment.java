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
public class Payment {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "purchaseId", referencedColumnName = "id")
  private Purchase purchase;

  private Double amount;

  @Column(updatable = false)
  private LocalDateTime createdAt;
}
