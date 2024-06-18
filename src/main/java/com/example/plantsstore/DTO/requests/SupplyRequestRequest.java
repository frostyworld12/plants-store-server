package com.example.plantsstore.DTO.requests;

import java.util.List;

public class SupplyRequestRequest {
  public static class NewSupplyRequest {
    private String supplierId;
    private String employeeId;
    private String documentName;
    private String documentData;
    private String parentRequestId;
    private List<SupplyRequestItem> requestItems;

    public NewSupplyRequest(String supplierId, String employeeId, String documentName, String documentData,
        List<SupplyRequestItem> requestItems) {
      this.supplierId = supplierId;
      this.employeeId = employeeId;
      this.documentName = documentName;
      this.documentData = documentData;
      this.requestItems = requestItems;
    }

    public NewSupplyRequest(String supplierId, String employeeId, String documentName, String documentData,
        List<SupplyRequestItem> requestItems, String parentRequestId) {
      this.supplierId = supplierId;
      this.employeeId = employeeId;
      this.documentName = documentName;
      this.documentData = documentData;
      this.requestItems = requestItems;
      this.parentRequestId = parentRequestId;
    }

    public NewSupplyRequest() {
    }

    public String getSupplierId() {
      return supplierId;
    }

    public void setSupplierId(String supplierId) {
      this.supplierId = supplierId;
    }

    public String getEmployeeId() {
      return employeeId;
    }

    public void setEmployeeId(String employeeId) {
      this.employeeId = employeeId;
    }

    public String getDocumentName() {
      return documentName;
    }

    public void setDocumentName(String documentName) {
      this.documentName = documentName;
    }

    public String getDocumentData() {
      return documentData;
    }

    public void setDocumentData(String documentData) {
      this.documentData = documentData;
    }

    public String getParentRequestId() {
      return parentRequestId;
    }

    public void setParentRequestId(String parentRequestId) {
      this.parentRequestId = parentRequestId;
    }

    public List<SupplyRequestItem> getRequestItems() {
      return requestItems;
    }

    public void setRequestItems(List<SupplyRequestItem> requestItems) {
      this.requestItems = requestItems;
    }
  }

  public static class SupplyRequestItem {
    private String productId;
    private Integer quantity;
    private Double costPerUnit;

    public SupplyRequestItem(String productId, Integer quantity, Double costPerUnit) {
      this.productId = productId;
      this.quantity = quantity;
      this.costPerUnit = costPerUnit;
    }

    public SupplyRequestItem() {
    }

    public String getProductId() {
      return productId;
    }

    public void setProductId(String productId) {
      this.productId = productId;
    }

    public Integer getQuantity() {
      return quantity;
    }

    public void setQuantity(Integer quantity) {
      this.quantity = quantity;
    }

    public Double getCostPerUnit() {
      return costPerUnit;
    }

    public void setCostPerUnit(Double costPerUnit) {
      this.costPerUnit = costPerUnit;
    }
  }

  public static class SupplyRequestSigning {
    private String requestId;
    private String supplierId;
    private String employeeId;
    private String documentData;

    public SupplyRequestSigning(String requestId, String supplierId, String employeeId, String documentData) {
      this.requestId = requestId;
      this.supplierId = supplierId;
      this.employeeId = employeeId;
      this.documentData = documentData;
    }

    public SupplyRequestSigning() {
    }

    public String getRequestId() {
      return requestId;
    }

    public void setRequestId(String requestId) {
      this.requestId = requestId;
    }

    public String getSupplierId() {
      return supplierId;
    }

    public void setSupplierId(String supplierId) {
      this.supplierId = supplierId;
    }

    public String getEmployeeId() {
      return employeeId;
    }

    public void setEmployeeId(String employeeId) {
      this.employeeId = employeeId;
    }

    public String getDocumentData() {
      return documentData;
    }

    public void setDocumentData(String documentData) {
      this.documentData = documentData;
    }
  }
}
