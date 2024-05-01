package com.example.plantsstore.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.plantsstore.DTO.requests.PurchaseRequestDTO;
import com.example.plantsstore.DTO.responses.PurchaseResponseDTO;
import com.example.plantsstore.model.Customer;
import com.example.plantsstore.model.Employee;
import com.example.plantsstore.model.Product;
import com.example.plantsstore.model.ProductsInPurchase;
import com.example.plantsstore.model.Purchase;
import com.example.plantsstore.repository.CustomerRepository;
import com.example.plantsstore.repository.EmployeeRepository;
import com.example.plantsstore.repository.ProductRepository;
import com.example.plantsstore.repository.ProductsInPurchaseRepository;
import com.example.plantsstore.repository.PurchaseRepository;
import com.example.plantsstore.utility.Utility;

@Controller
@RequestMapping(path="/purchases")
public class PurchaseController {
  @Autowired
  private PurchaseRepository purchaseRepository;
  @Autowired
  private ProductRepository productRepository;
  @Autowired
  private CustomerRepository customerRepository;
  @Autowired
  private EmployeeRepository employeeRepository;
  @Autowired
  private ProductsInPurchaseRepository productsInPurchaseRepository;

  @PostMapping("/createPurchase")
  public @ResponseBody ResponseEntity<String> createPurchase(@RequestBody PurchaseRequestDTO.Purchase newPurchaseDTO) {
    // if (newPurchaseDTO.amountPerProductId == null || newPurchaseDTO.customerId == null || newPurchaseDTO.employeeId == null) {
    //   return ResponseEntity.status(400).body("Not enogh info provided!");
    // }

    // Customer customer = customerRepository.getById(newPurchaseDTO.customerId);
    // if (customer == null) {
    //   return ResponseEntity.status(400).body("Can not find customer!");
    // }
    // Employee employee = employeeRepository.getById(newPurchaseDTO.customerId);
    // if (employee == null) {
    //   return ResponseEntity.status(400).body("Can not find employee!");
    // }

    // List<Product> products = productRepository.findByIdIn(newPurchaseDTO.amountPerProductId.keySet());
    // if (products.isEmpty()) {
    //   return ResponseEntity.status(400).body("Can not find products!");
    // }

    // Map<String, Product> productPerId = new HashMap<>();
    // Map<String, Double> productSubtotalPerId = new HashMap<>();
    // for (Product product : products) {
    //   productPerId.put(product.getId(), product);
    //   if (!productSubtotalPerId.containsKey(product.getId())) {
    //     productSubtotalPerId.put(product.getId(), product.getPrice());
    //   } else {
    //     productSubtotalPerId.put(product.getId(), productSubtotalPerId.get(product.getId()) + product.getPrice());
    //   }
    // }

    // Double totalAmount = 0.0;
    // for (Product product : products) {
    //   totalAmount += product.getPrice();
    // }

    // Purchase purchaseToCreate = new Purchase();
    // purchaseToCreate.createPurchase(
    //   customer,
    //   employee,
    //   totalAmount,
    //   new Date(),
    //   newPurchaseDTO.deliveryDate
    // );

    // String purchaseId = purchaseRepository.save(purchaseToCreate).getId();
    // if (Utility.isStringEmpty(purchaseId)) {
    //   return ResponseEntity.status(400).body("Failed to create purchase!");
    // }

    // List<ProductsInPurchase> productsInPurchase = new ArrayList<>();
    // for (String productId : newPurchaseDTO.amountPerProductId.keySet()) {
    //   ProductsInPurchase productInPurchase = new ProductsInPurchase();
    //   productInPurchase.createProductInPurchase(
    //     newPurchaseDTO.amountPerProductId.get(productId),
    //     productSubtotalPerId.get(productId),
    //     productPerId.get(productId),
    //     purchaseToCreate
    //   );

    //   productsInPurchase.add(productInPurchase);
    // }

    // productsInPurchaseRepository.saveAll(productsInPurchase);

    return ResponseEntity.status(200).body("Success");
  }

  @GetMapping("/getPurchase")
  public @ResponseBody ResponseEntity<List<PurchaseResponseDTO.Purchase>> getPurchase() {
    List<Purchase> purchases = purchaseRepository.findAll();
    List<PurchaseResponseDTO.Purchase> purchasesData = new ArrayList<>();
    for (Purchase purchase : purchases) {
      PurchaseResponseDTO.Purchase purchaseData = new PurchaseResponseDTO.Purchase(
        purchase.getId(),
        fillInEmployeeResponse(purchase),
        fillInCustomerResponse(purchase),
        purchase.getDeliveryDate(),
        purchase.getOrderDate(),
        purchase.getTotalAmount()
      );

      purchasesData.add(purchaseData);
    }

    return ResponseEntity.status(200).body(purchasesData);
  }

  @GetMapping("/getPurchaseById")
  public @ResponseBody ResponseEntity<PurchaseResponseDTO.Purchase> getPurchaseById(@PathVariable("purchaseId") String purchaseId) {
    // Purchase purchase = purchaseRepository.getById(purchaseId);
    // List<PurchaseResponseDTO.ProductInPurchase> productsInPurchase = new ArrayList<>();

    // for (ProductsInPurchase productInPurchase : purchase.getProductsInPurchases()) {
    //   productsInPurchase.add(new PurchaseResponseDTO.ProductInPurchase(
    //     productInPurchase.getProduct().getId(),
    //     productInPurchase.getProduct().getName(),
    //     productInPurchase.getProduct().getPrice(),
    //     productInPurchase.getQuantity(),
    //     productInPurchase.getSubtotal()
    //   ));
    // }

    // PurchaseResponseDTO.Purchase purchaseData = new PurchaseResponseDTO.Purchase(
    //   purchase.getId(),
    //   fillInEmployeeResponse(purchase),
    //   fillInCustomerResponse(purchase),
    //   productsInPurchase,
    //   purchase.getDeliveryDate(),
    //   purchase.getOrderDate(),
    //   purchase.getTotalAmount()
    // );

    return ResponseEntity.status(200).body(null);
  }


  private static PurchaseResponseDTO.Employee fillInEmployeeResponse(Purchase purchase) {
    return null;
    // return new PurchaseResponseDTO.Employee(
    //   purchase.getEmployee().getName(),
    //   purchase.getEmployee().getPosition(),
    //   purchase.getEmployee().getUser().getUsername(),
    //   purchase.getEmployee().getId()
    // );
  }

  private static PurchaseResponseDTO.Customer fillInCustomerResponse(Purchase purchase) {
    return null;

    // return new PurchaseResponseDTO.Customer(
    //   purchase.getCustomer().getName(),
    //   purchase.getCustomer().getAddress(),
    //   purchase.getCustomer().getEmail(),
    //   purchase.getCustomer().getId()
    // );
  }
}
