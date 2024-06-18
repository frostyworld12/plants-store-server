package com.example.plantsstore.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.example.plantsstore.DTO.requests.*;
import com.example.plantsstore.DTO.responses.*;
import com.example.plantsstore.model.*;
import com.example.plantsstore.repository.*;
import com.example.plantsstore.services.*;
import com.example.plantsstore.utility.*;

@Controller
@RequestMapping(path = "/products")
public class ProductController {
  @Autowired
  private ProductRepository productRepository;
  @Autowired
  private SupplierRepository supplierRepository;
  @Autowired
  private SupplyRequestItemRepository supplyRequestItemRepository;

  @PostMapping("/saveProduct")
  public @ResponseBody ResponseEntity<?> saveProduct(@RequestBody ProductRequest.NewProduct newProduct) {
    try {
      Product product = !Utility.isStringEmpty(newProduct.getId())
          ? productRepository.getById(newProduct.getId())
          : new Product();

      product.setName(newProduct.getName());
      product.setDescription(newProduct.getDescription());
      product.setImage(newProduct.getImage());

      List<Supplier> suppliers = new ArrayList<>();
      if (!newProduct.getSuppliers().isEmpty()) {
        suppliers = supplierRepository.findAllByIdIn(newProduct.getSuppliers());
      }
      product.setSuppliers(suppliers);

      productRepository.save(product);

      return ResponseEntity.status(200).body("Success");
    } catch (Exception ex) {
      return ResponseEntity.status(400).body(ex.getMessage());
    }
  }

  @GetMapping("/getProducts")
  public @ResponseBody ResponseEntity<?> getProducts(
      @RequestParam(value = "limit") Integer limit,
      @RequestParam(value = "offset") Integer offset,
      @RequestParam(value = "query", required = false) String query) {
    try {
      ProductResponse.ProductsPagination result = new ProductResponse.ProductsPagination();

      Pageable pageable = PageRequest.of(offset, limit);
      Page<Product> productsPage = Utility.isStringEmpty(query)
          ? productRepository.findAllByOrderByCreatedAtDesc(pageable)
          : productRepository.findByNameContainingOrderByCreatedAtDesc(query, pageable);

      List<Product> productContent = productsPage.getContent();

      List<ProductResponse.Product> products = new ArrayList<>();
      productContent.forEach(product -> {
        ProductResponse.Product productResponse = new ProductResponse.Product();
        productResponse.setId(product.getId());
        productResponse.setName(product.getName());
        productResponse.setDescription(product.getDescription());
        productResponse.setImage(product.getImage());
        productResponse.setPrice(product.getPrice());
        productResponse.setSuppliers(ProductService.getProductSuppliers(product.getSuppliers()));

        products.add(productResponse);
      });

      result = new ProductResponse.ProductsPagination(
          (int) productsPage.getTotalElements(),
          products);

      return ResponseEntity.status(200).body(result);
    } catch (Exception ex) {
      return ResponseEntity.status(400).body(ex.getMessage());
    }
  }

  @DeleteMapping("/deleteProduct")
  public @ResponseBody ResponseEntity<?> deleteProduct(@RequestParam("productId") String productId) {
    if (productId == null) {
      return ResponseEntity.status(400).body("Not enough info provided!");
    }

    if (!productRepository.existsById(productId)) {
      return ResponseEntity.status(400).body("Product not exists!");
    }

    try {
      List<SupplyRequestItem> supplyRequestItems = supplyRequestItemRepository.getAllByProductId(productId);
      if (!supplyRequestItems.isEmpty()) {
        return ResponseEntity.status(400).body("Can not remove product due to it connected with request.");
      }

      productRepository.deleteById(productId);
    } catch (Exception ex) {
      return ResponseEntity.status(400).body(ex.getMessage());
    }

    return ResponseEntity.status(200).body("Success");
  }
}
