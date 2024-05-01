package com.example.plantsstore.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.plantsstore.DTO.requests.SupplierRequestDTO;
import com.example.plantsstore.model.Supplier;
import com.example.plantsstore.DTO.responses.SupplierResponseDTO;
import com.example.plantsstore.repository.SupplierRepository;
import com.example.plantsstore.utility.Utility;

import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping(path="/suppliers")
public class SupplierController {
  @Autowired
  private SupplierRepository supplierRepository;

  @PostMapping("/saveSupplier")
  public @ResponseBody ResponseEntity<?> createSupplier(@RequestBody SupplierRequestDTO.Supplier newSupplier) {
    List<Supplier> existingSuppliers = supplierRepository.findByNameAndContactPersonAndEmail(
      newSupplier.name,
      newSupplier.contactPerson,
      newSupplier.email
    );

    try {
      Boolean isSupplierExists = false;
      if (!existingSuppliers.isEmpty()) {
        for (Supplier supplier : existingSuppliers) {
          if (!supplier.getId().equals(newSupplier.id)) {
            isSupplierExists = true;
            break;
          }
        }
      }

      if (isSupplierExists) {
        throw new Exception("Such supplier already exists!");
      }

      Supplier supplier = !Utility.isStringEmpty(newSupplier.id) ? supplierRepository.getById(newSupplier.id) : null;

      if (supplier != null) {
        supplier.setName(newSupplier.name);
        supplier.setContactPerson(newSupplier.contactPerson);
        supplier.setEmail(newSupplier.email);
        supplier.setPhone(newSupplier.phone);
      } else {
        supplier = new Supplier(
          newSupplier.name,
          newSupplier.contactPerson,
          newSupplier.email,
          newSupplier.phone
        );
      }

      supplierRepository.save(supplier);
    } catch (Exception ex) {
      return ResponseEntity.status(400).body(ex.getMessage());
    }

    return ResponseEntity.status(200).body("Success");
  }

  @GetMapping("/getSuppliers")
  public @ResponseBody ResponseEntity<?> getSuppliers(
    @RequestParam("limit") Integer limit,
    @RequestParam("offset") Integer offset,
    @RequestParam(value="query", required=false) String query
  ) {
    SupplierResponseDTO.Suppliers result = new SupplierResponseDTO.Suppliers();
    try {
      Pageable pageable = PageRequest.of(offset, limit);
      Page<Supplier> suppliersPage = Utility.isStringEmpty(query)
        ? supplierRepository.findAllByOrderByCreatedAtDesc(pageable)
        : supplierRepository.findByNameContainingOrderByCreatedAtDesc(query, pageable);

      List<Supplier> suppliersContent = suppliersPage.getContent();

      List<SupplierResponseDTO.Supplier> suppliers = new ArrayList<>();
      suppliersContent.forEach(supplier -> {
        suppliers.add(new SupplierResponseDTO.Supplier(
          supplier.getId(),
          supplier.getEmail(),
          supplier.getName(),
          supplier.getPhone(),
          supplier.getContactPerson()
        ));
      });

      result = new SupplierResponseDTO.Suppliers(
        (int)suppliersPage.getTotalElements(),
        suppliers
      );
    } catch (Exception ex) {
      return ResponseEntity.status(400).body(ex.getMessage());
    }
    return ResponseEntity.status(200).body(result);
  }

  @DeleteMapping("/deleteSupplier")
  public @ResponseBody ResponseEntity<?> deleteSupplier(@RequestParam("supplierId") String supplierId) {
    if (supplierId == null) {
      return ResponseEntity.status(400).body("Not enough info provided!");
    }

    if (!supplierRepository.existsById(supplierId)) {
      return ResponseEntity.status(400).body("Supplier not exists!");
    }

    try {
      supplierRepository.deleteById(supplierId);
    } catch (Exception ex) {
      return ResponseEntity.status(400).body(ex.getMessage());
    }

    return ResponseEntity.status(200).body("Success");
  }
}
