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

import com.example.plantsstore.DTO.SupplierDTO;
import com.example.plantsstore.DTO.requests.SupplierRequestDTO;
import com.example.plantsstore.model.Supplier;
import com.example.plantsstore.model.User;
import com.example.plantsstore.DTO.responses.SupplierResponseDTO;
import com.example.plantsstore.repository.SupplierRepository;
import com.example.plantsstore.repository.UserRepository;
import com.example.plantsstore.utility.Utility;

import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping(path = "/suppliers")
public class SupplierController {
  @Autowired
  private SupplierRepository supplierRepository;
  @Autowired
  private UserRepository userRepository;

  @PostMapping("/saveSupplier")
  public @ResponseBody ResponseEntity<?> saveSupplier(@RequestBody SupplierRequestDTO.Supplier newSupplier) {
    try {
      Supplier existingSupplier = supplierRepository.findByNameAndContactPersonAndEmailAndIdNot(
        newSupplier.name,
        newSupplier.contactPerson,
        newSupplier.email,
        newSupplier.id
      );

      if (existingSupplier != null) {
        throw new Exception("Such supplier already exists!");
      }

      Supplier supplier = !Utility.isStringEmpty(newSupplier.id) ? supplierRepository.getById(newSupplier.id) : null;
      User user = supplier != null ? supplier.getUser() : null;

      if (supplier != null) {
        user.setUsername(newSupplier.user.username);

        supplier.setName(newSupplier.name);
        supplier.setContactPerson(newSupplier.contactPerson);
        supplier.setEmail(newSupplier.email);
        supplier.setPhone(newSupplier.phone);
      } else {
        user = new User(
          newSupplier.user.username,
          newSupplier.user.password,
          "Supplier"
        );

        supplier = new Supplier(
          newSupplier.name,
          newSupplier.contactPerson,
          newSupplier.email,
          newSupplier.phone,
          user
        );
      }

      userRepository.save(user);
      supplierRepository.save(supplier);
    } catch (Exception ex) {
      return ResponseEntity.status(400).body(ex.getMessage());
    }

    return ResponseEntity.status(200).body("Success");
  }

  @GetMapping("/getSupplier")
  public @ResponseBody ResponseEntity<?> getSupplier(@RequestParam("supplierId") String supplierId) {
    try {
      Supplier supplier = supplierRepository.getById(supplierId);

      if (supplier == null) {
        throw new Exception("Can not find supplier!");
      }

      SupplierDTO.Supplier result = new SupplierDTO.Supplier(
        supplier.getId(),
        supplier.getEmail(),
        supplier.getName(),
        supplier.getPhone(),
        supplier.getContactPerson()
      );

      return ResponseEntity.status(200).body(result);
    } catch (Exception ex) {
      return ResponseEntity.status(400).body(ex.getMessage());
    }
  }

  @GetMapping("/getAllSuppliers")
  public @ResponseBody ResponseEntity<?> getAllSuppliers(
      @RequestParam(value = "query", required = false) String query) {
    SupplierResponseDTO.Suppliers result = new SupplierResponseDTO.Suppliers();
    try {
      List<Supplier> suppliers = Utility.isStringEmpty(query)
          ? supplierRepository.findAllByOrderByCreatedAtDesc()
          : supplierRepository.findByNameContainingOrderByCreatedAtDesc(query);

      List<SupplierResponseDTO.Supplier> suppliersResult = new ArrayList<>();
      suppliers.forEach(supplier -> {
        suppliersResult.add(new SupplierResponseDTO.Supplier(
            supplier.getId(),
            supplier.getEmail(),
            supplier.getName(),
            supplier.getPhone(),
            supplier.getContactPerson()));
      });

      result = new SupplierResponseDTO.Suppliers(
          suppliers.size(),
          suppliersResult);
    } catch (Exception ex) {
      return ResponseEntity.status(400).body(ex.getMessage());
    }
    return ResponseEntity.status(200).body(result);
  }

  @GetMapping("/getSuppliers")
  public @ResponseBody ResponseEntity<?> getSuppliers(
      @RequestParam("limit") Integer limit,
      @RequestParam("offset") Integer offset,
      @RequestParam(value = "query", required = false) String query) {
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
            supplier.getContactPerson()));
      });

      result = new SupplierResponseDTO.Suppliers(
          (int) suppliersPage.getTotalElements(),
          suppliers);
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

    // if (!supplierRepository.existsById(supplierId)) {
    // return ResponseEntity.status(400).body("Supplier not exists!");
    // }

    try {
      userRepository.deleteById(supplierId);
    } catch (Exception ex) {
      return ResponseEntity.status(400).body(ex.getMessage());
    }

    return ResponseEntity.status(200).body("Success");
  }
}
