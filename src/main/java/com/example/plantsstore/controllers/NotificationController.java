package com.example.plantsstore.controllers;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.example.plantsstore.DTO.responses.*;
import com.example.plantsstore.model.*;
import com.example.plantsstore.repository.*;
import com.example.plantsstore.utility.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
@RequestMapping(path = "/notifications")
public class NotificationController {
  @Autowired
  private NotificationRepository notificationRepository;

  @GetMapping("/getNotifications")
  public ResponseEntity<?> getNotifications(@RequestParam(value = "userId") String userId) {
    try {
      if (Utility.isStringEmpty(userId)) {
        throw new Exception("User not provided!");
      }

      List<NotificationResponse.Notification> result = new ArrayList<>();
      List<Notification> notifications = notificationRepository.getAllByUserIdAndIsViewedFalse(userId);
      notifications.forEach(notification -> {
        result.add(new NotificationResponse.Notification(
          notification.getId(),
          notification.getNotificationType(),
          notification.getAdditionalInfo(),
          notification.getIsViewed(),
          notification.getUser().getId()
        ));
      });

      return ResponseEntity.status(200).body(result);
    } catch (Exception ex) {
      return ResponseEntity.status(400).body(ex.getMessage());
    }
  }

  @PostMapping("/readNotification")
  public ResponseEntity<?> readNotification(@RequestBody String notificationId) {
    try {
      if (Utility.isStringEmpty(notificationId)) {
        throw new Exception("Not enough info provided");
      }

      if (!notificationRepository.existsById(notificationId)) {
        throw new Exception("Could not find notification");
      }

      notificationRepository.deleteById(notificationId);

      return ResponseEntity.status(200).body("Success");
    } catch (Exception ex) {
      return ResponseEntity.status(400).body(ex.getMessage());
    }
  }
}
