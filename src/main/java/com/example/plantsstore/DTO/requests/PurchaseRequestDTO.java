package com.example.plantsstore.DTO.requests;
import java.util.Date;
import java.util.Map;

public class PurchaseRequestDTO {
  public static class Purchase {
    public Map<String, Integer> amountPerProductId;
    public String employeeId;
    public String customerId;
    public Date deliveryDate;
  }
}
