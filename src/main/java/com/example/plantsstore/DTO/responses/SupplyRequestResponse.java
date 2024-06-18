package com.example.plantsstore.DTO.responses;

import java.time.LocalDateTime;

import java.util.*;

public class SupplyRequestResponse {
  public static class SupplyRequestAll {
    private String id;
    private Boolean approvedBySupplier;
    private Boolean approvedByEmployee;
    private EmployeeResponse.Employee employee;
    private SupplierResponse.Supplier supplier;
    private String requestName;
    private String documentName;
    private String documentData;
    private LocalDateTime createdDate;
    private List<SupplyRequestItem> requestItems;

    public SupplyRequestAll(
        String id,
        Boolean approvedBySupplier,
        Boolean approvedByEmployee,
        EmployeeResponse.Employee employee,
        SupplierResponse.Supplier supplier,
        String requestName,
        String documentName,
        String documentData,
        LocalDateTime createdDate) {
      this.id = id;
      this.approvedBySupplier = approvedBySupplier;
      this.approvedByEmployee = approvedByEmployee;
      this.employee = employee;
      this.supplier = supplier;
      this.requestName = requestName;
      this.documentName = documentName;
      this.documentData = documentData;
      this.createdDate = createdDate;
    }

    public SupplyRequestAll(
        String id,
        Boolean approvedBySupplier,
        Boolean approvedByEmployee,
        EmployeeResponse.Employee employee,
        SupplierResponse.Supplier supplier,
        String requestName,
        String documentName,
        String documentData,
        List<SupplyRequestItem> requestItems,
        LocalDateTime createdDate) {
      this.id = id;
      this.approvedBySupplier = approvedBySupplier;
      this.approvedByEmployee = approvedByEmployee;
      this.employee = employee;
      this.supplier = supplier;
      this.requestName = requestName;
      this.documentName = documentName;
      this.documentData = documentData;
      this.requestItems = requestItems;
      this.createdDate = createdDate;
    }

    public SupplyRequestAll() {
    }

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    public Boolean getApprovedBySupplier() {
      return approvedBySupplier;
    }

    public void setApprovedBySupplier(Boolean approvedBySupplier) {
      this.approvedBySupplier = approvedBySupplier;
    }

    public Boolean getApprovedByEmployee() {
      return approvedByEmployee;
    }

    public void setApprovedByEmployee(Boolean approvedByEmployee) {
      this.approvedByEmployee = approvedByEmployee;
    }

    public EmployeeResponse.Employee getEmployee() {
      return employee;
    }

    public void setEmployee(EmployeeResponse.Employee employee) {
      this.employee = employee;
    }

    public SupplierResponse.Supplier getSupplier() {
      return supplier;
    }

    public void setSupplier(SupplierResponse.Supplier supplier) {
      this.supplier = supplier;
    }

    public String getRequestName() {
      return requestName;
    }

    public void setRequestName(String requestName) {
      this.requestName = requestName;
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

    public List<SupplyRequestItem> getRequestItems() {
      return requestItems;
    }

    public void setRequestItems(List<SupplyRequestItem> requestItems) {
      this.requestItems = requestItems;
    }

    public LocalDateTime getCreatedDate() {
      return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
      this.createdDate = createdDate;
    }
  }

  public static class SupplyRequestItem {
    private String productName;
    private Integer quantity;
    private Double costPerUnit;
    private Double total;

    public SupplyRequestItem(String productName, Integer quantity, Double costPerUnit, Double total) {
      this.productName = productName;
      this.quantity = quantity;
      this.costPerUnit = costPerUnit;
      this.total = total;
    }

    public SupplyRequestItem() {
    }

    public String getProductName() {
      return productName;
    }

    public void setProductName(String productName) {
      this.productName = productName;
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

    public Double getTotal() {
      return total;
    }

    public void setTotal(Double total) {
      this.total = total;
    }
  }

  public static class SupplyRequestsPagination {
    private Integer count;
    private List<SupplyRequestAll> requests;

    public SupplyRequestsPagination(Integer count, List<SupplyRequestAll> supplyRequests) {
      this.count = count;
      this.requests = supplyRequests;
    }

    public SupplyRequestsPagination() {
    }

    public Integer getCount() {
      return count;
    }

    public void setCount(Integer count) {
      this.count = count;
    }

    public List<SupplyRequestAll> getRequests() {
      return requests;
    }

    public void setRequests(List<SupplyRequestAll> supplyRequests) {
      this.requests = supplyRequests;
    }
  }
}
