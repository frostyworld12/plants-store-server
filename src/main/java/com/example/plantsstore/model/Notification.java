package com.example.plantsstore.model;

import jakarta.persistence.*;

@Entity
public class Notification {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  private String notificationType;
  private String additionalInfo;
  private Boolean isViewed;

  @ManyToOne
  private User user;

  public Notification(String notificationType, String additionalInfo, Boolean isViewed, User user) {
    this.notificationType = notificationType;
    this.additionalInfo = additionalInfo;
    this.isViewed = isViewed;
    this.user = user;
  }

  public Notification() {
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getNotificationType() {
    return notificationType;
  }

  public void setNotificationType(String notificationType) {
    this.notificationType = notificationType;
  }

  public String getAdditionalInfo() {
    return additionalInfo;
  }

  public void setAdditionalInfo(String additionalInfo) {
    this.additionalInfo = additionalInfo;
  }

  public Boolean getIsViewed() {
    return isViewed;
  }

  public void setIsViewed(Boolean isViewed) {
    this.isViewed = isViewed;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
}
