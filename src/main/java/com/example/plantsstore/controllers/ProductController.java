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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.plantsstore.DTO.requests.ProductRequestDTO;
import com.example.plantsstore.DTO.responses.ProductResponseDTO;
import com.example.plantsstore.DTO.responses.SupplierResponseDTO;
import com.example.plantsstore.model.Product;
import com.example.plantsstore.model.Supplier;
import com.example.plantsstore.repository.ProductRepository;
import com.example.plantsstore.repository.SupplierRepository;
import com.example.plantsstore.utility.Utility;

@Controller
@RequestMapping(path = "/products")
public class ProductController {
  @Autowired
  private ProductRepository productRepository;
  @Autowired
  private SupplierRepository supplierRepository;

  @PostMapping("/saveProduct")
  public @ResponseBody ResponseEntity<?> saveProduct(@RequestBody ProductRequestDTO.Product newProduct) {
    Product existingProdcut = !Utility.isStringEmpty(newProduct.id)
      ? productRepository.findByNameAndIdNot(newProduct.name, newProduct.id)
      : productRepository.findByName(newProduct.name);

    try {
      if (existingProdcut != null) {
        throw new Exception("Such product already exists!");
      }

      Product product = !Utility.isStringEmpty(newProduct.id) ? productRepository.getById(newProduct.id) : null;

      if (product != null) {
        product.setName(newProduct.name);
        product.setImage(newProduct.image);
        product.setDescription(newProduct.description);
        product.setPrice(newProduct.price);
        product.setSuppliers(supplierRepository.findAllByIdIn(newProduct.suppliers));
      } else {
        product = new Product(
          newProduct.name,
          newProduct.description,
          newProduct.price,
          newProduct.image,
          supplierRepository.findAllByIdIn(newProduct.suppliers)
        );
      }

      productRepository.save(product);
    } catch (Exception ex) {
      return ResponseEntity.status(400).body(ex.getMessage());
    }

    return ResponseEntity.status(200).body("Success");
  }

  @GetMapping("/getProducts")
  public @ResponseBody ResponseEntity<?> getProducts(
    @RequestParam("limit") Integer limit,
    @RequestParam("offset") Integer offset,
    @RequestParam(value = "query", required = false) String query
  ) {
    ProductResponseDTO.Products result = new ProductResponseDTO.Products();
    try {
      Pageable pageable = PageRequest.of(offset, limit);
      Page<Product> productsPage = Utility.isStringEmpty(query)
        ? productRepository.findAllByOrderByCreatedAtDesc(pageable)
        : productRepository.findByNameContainingOrderByCreatedAtDesc(query, pageable);

      List<Product> productContent = productsPage.getContent();
      List<ProductResponseDTO.Product> products = new ArrayList<>();
      productContent.forEach(product -> {
        products.add(new ProductResponseDTO.Product(
          product.getId(),
          product.getName(),
          product.getDescription(),
          product.getPrice(),
          product.getImage(),
          processSuppliers(product.getSuppliers())
        ));
      });

      result = new ProductResponseDTO.Products(
        (int) productsPage.getTotalElements(),
        products
      );
    } catch (Exception ex) {
      return ResponseEntity.status(400).body(ex.getMessage());
    }
    return ResponseEntity.status(200).body(result);
  }

  private static List<SupplierResponseDTO.Supplier> processSuppliers(List<Supplier> suppliers) {
    List<SupplierResponseDTO.Supplier> result = new ArrayList<>();
    suppliers.forEach(supplier -> {
      result.add(new SupplierResponseDTO.Supplier(
        supplier.getId(),
        supplier.getEmail(),
        supplier.getName(),
        supplier.getPhone(),
        supplier.getContactPerson()
      ));
    });

    return result;
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
      productRepository.deleteById(productId);
    } catch (Exception ex) {
      return ResponseEntity.status(400).body(ex.getMessage());
    }

    return ResponseEntity.status(200).body("Success");
  }
}
