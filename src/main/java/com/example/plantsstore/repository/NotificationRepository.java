package com.example.plantsstore.repository;

import java.util.*;

import org.springframework.data.repository.*;
import com.example.plantsstore.model.*;

public interface NotificationRepository extends CrudRepository<Notification, String> {
  List<Notification> getAllByUserIdAndIsViewedFalse(String userId);

  Notification getById(String id);
}
