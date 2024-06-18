package com.example.plantsstore.DTO.responses;

public class NotificationResponse {
  public static class Notification {
    private String id;
    private String notificationType;
    private String additionalInfo;
    private Boolean isViewed;
    private String userId;

    public Notification(String id, String notificationType, String additionalInfo, Boolean isViewed, String userId) {
      this.id = id;
      this.notificationType = notificationType;
      this.additionalInfo = additionalInfo;
      this.isViewed = isViewed;
      this.userId = userId;
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

    public String getUserId() {
      return userId;
    }

    public void setUserId(String userId) {
      this.userId = userId;
    }
  }
}
