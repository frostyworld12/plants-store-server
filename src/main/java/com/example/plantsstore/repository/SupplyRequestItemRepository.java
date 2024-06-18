package com.example.plantsstore.repository;

import java.util.*;

import org.springframework.data.domain.*;
import org.springframework.data.repository.*;
import com.example.plantsstore.model.*;

public interface SupplyRequestItemRepository extends CrudRepository<SupplyRequestItem, String> {
  List<SupplyRequestItem> getAllBySupplyRequestId(String supplyRequestId);
  List<SupplyRequestItem> getAllByProductId(String productId);
}
