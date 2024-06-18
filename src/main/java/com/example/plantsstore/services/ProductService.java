package com.example.plantsstore.services;

import java.util.ArrayList;
import java.util.List;

import com.example.plantsstore.model.*;
import com.example.plantsstore.DTO.responses.SupplierResponse;

public class ProductService {
  public static List<SupplierResponse.Supplier> getProductSuppliers(List<Supplier> suppliers) {
    List<SupplierResponse.Supplier> result = new ArrayList<>();
    suppliers.forEach(supplier -> {
      result.add(new SupplierResponse.Supplier(
          supplier.getId(),
          supplier.getName(),
          supplier.getContactPerson(),
          supplier.getPhone(),
          supplier.getEmail()));
    });

    return result;
  }
}
