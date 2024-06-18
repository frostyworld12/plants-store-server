package com.example.plantsstore.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.example.plantsstore.model.*;
import com.example.plantsstore.DTO.responses.*;
import com.example.plantsstore.repository.*;
import com.example.plantsstore.utility.*;

@Controller
@RequestMapping(path = "/suppliers")
public class SupplierController {
  @Autowired
  private SupplierRepository supplierRepository;

  @GetMapping("/getSuppliers")
  public @ResponseBody ResponseEntity<?> getSuppliers(
      @RequestParam(value = "limit") Integer limit,
      @RequestParam(value = "offset") Integer offset,
      @RequestParam(value = "query", required = false) String query) {
    try {
      SupplierResponse.SuppliersPagination result = new SupplierResponse.SuppliersPagination();
      List<Supplier> suppliersContent = Utility.isStringEmpty(query)
          ? supplierRepository.findAllByOrderByCreatedAtDesc()
          : supplierRepository.findByNameContainingOrderByCreatedAtDesc(query);

      List<SupplierResponse.Supplier> suppliers = new ArrayList<>();
      suppliersContent.forEach(supplier -> {
        suppliers.add(new SupplierResponse.Supplier(
            supplier.getId(),
            supplier.getName(),
            supplier.getContactPerson(),
            supplier.getPhone(),
            supplier.getEmail()));
      });

      result = new SupplierResponse.SuppliersPagination(
          suppliersContent.size(),
          suppliers);

      return ResponseEntity.status(200).body(result);
    } catch (Exception ex) {
      return ResponseEntity.status(400).body(ex.getMessage());
    }
  }
}
