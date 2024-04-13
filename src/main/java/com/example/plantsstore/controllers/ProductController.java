package com.example.plantsstore.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.plantsstore.DTO.requests.ProductRequestDTO;
import com.example.plantsstore.DTO.responses.ProductResponseDTO;
import com.example.plantsstore.model.Inventory;
import com.example.plantsstore.model.Product;
import com.example.plantsstore.model.Supplie;
import com.example.plantsstore.model.Supplier;
import com.example.plantsstore.repository.InventoryRepository;
import com.example.plantsstore.repository.ProductRepository;
import com.example.plantsstore.repository.SupplieRepository;
import com.example.plantsstore.repository.SupplierRepository;
import com.example.plantsstore.utility.Utility;

@Controller
@RequestMapping(path="/products")
public class ProductController {
  @Autowired
  private ProductRepository productRepository;
  @Autowired
  private SupplierRepository supplierRepository;
  @Autowired
  private SupplieRepository supplieRepository;
  @Autowired
  private InventoryRepository inventoryRepository;

  @PostMapping("/createProduct")
  public @ResponseBody ResponseEntity<String> createProduct(@RequestBody ProductRequestDTO.Product newProductDTO) {
    List<Product> existingProducts = productRepository.findByNameIgnoreCase(newProductDTO.name);

    Product product = null;
    if (existingProducts.isEmpty()) {
      product = new Product(
        newProductDTO.name,
        newProductDTO.description,
        newProductDTO.price,
        newProductDTO.image
      );
      productRepository.save(product);
    } else if (!Utility.isStringEmpty(newProductDTO.id)) {
      product = productRepository.getById(newProductDTO.id);
    }

    if (product == null) {
      return ResponseEntity.status(400).body("Can not find product!");
    }

    Supplier supplier = supplierRepository.findByNameAndContactPersonAndEmailAndPhoneIgnoreCase(
      newProductDTO.supplier.name,
      newProductDTO.supplier.contactPerson,
      newProductDTO.supplier.email,
      newProductDTO.supplier.phone
    );

    if (supplier == null) {
      supplier = new Supplier(
        newProductDTO.supplier.name,
        newProductDTO.supplier.contactPerson,
        newProductDTO.supplier.email,
        newProductDTO.supplier.phone
      );
    }

    Supplie supplie = new Supplie(
      supplier,
      product,
      new Date(),
      newProductDTO.price * newProductDTO.quantity
    );
    supplieRepository.save(supplie);

    Inventory inventory = inventoryRepository.findByProductId(product.getId());
    if (inventory == null) {
      inventory = new Inventory(
        product,
        newProductDTO.quantity,
        new Date()
      );

      inventoryRepository.save(inventory);
    } else {
      inventory.setQuantityAvailable(inventory.getQuantityAvailable() + newProductDTO.quantity);
      inventory.setLastRestockDate(new Date());

      inventoryRepository.save(inventory);
    }

    return ResponseEntity.status(200).body("Product successfully created!");
  }

  @GetMapping("/getProducts")
  public @ResponseBody ResponseEntity<List<ProductResponseDTO.Product>> getProducts() {
    List<Product> products = productRepository.findAll();
    List<ProductResponseDTO.Product> productsData = processProducts(products);

    return ResponseEntity.status(200).body(productsData);
  }

  @GetMapping("/getProductById")
  public @ResponseBody ResponseEntity<List<ProductResponseDTO.Product>> getProducts(@PathVariable("productId") String productId) {
    Product product = productRepository.getById(productId);
    List<ProductResponseDTO.Product> productsData = processProducts(new ArrayList<>(Arrays.asList(product)));

    return ResponseEntity.status(200).body(productsData);
  }

  @DeleteMapping("/deleteProduct/{productId}")
  public @ResponseBody ResponseEntity<String> deleteProduct(@PathVariable("productId") String productId) {
    if (productRepository.findById(productId).isPresent()) {
      productRepository.deleteById(productId);
    } else {
      return ResponseEntity.status(400).body("Such product does not exist!");
    }

    return ResponseEntity.status(200).body("Success");
  }

  private List<ProductResponseDTO.Product> processProducts(List<Product> products) {
    List<ProductResponseDTO.Product> productsData = new ArrayList<>();

    Set<String> productsIds = new HashSet<>();
    for (Product product : products) {
      productsIds.add(product.getId());
    }

    List<Inventory> inventories = inventoryRepository.findByProductIdIn(productsIds);
    Map<String, Integer> productsPerQuantity = new HashMap<>();
    for (Inventory inventory : inventories) {
      productsPerQuantity.put(inventory.getProduct().getId(), inventory.getQuantityAvailable());
    }

    List<Supplie> supplies = supplieRepository.findByProductIdIn(productsIds);
    Map<String, Supplier> supplierPerProducts = new HashMap<>();
    for (Supplie supplie : supplies) {
      supplierPerProducts.put(supplie.getProduct().getId(), supplie.getSupplier());
    }

    for (Product product : products) {
      productsData.add(
        new ProductResponseDTO.Product(
          product.getId(),
          product.getName(),
          product.getDescription(),
          product.getPrice(),
          product.getImage(),
          productsPerQuantity.get(product.getId()),
          new ProductResponseDTO.Supplier(
            supplierPerProducts.get(product.getId()).getId(),
            supplierPerProducts.get(product.getId()).getName(),
            supplierPerProducts.get(product.getId()).getContactPerson(),
            supplierPerProducts.get(product.getId()).getEmail()
          )
        )
      );
    }

    return productsData;
  }
}
