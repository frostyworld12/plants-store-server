package com.example.plantsstore.controllers;

import java.util.*;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.example.plantsstore.model.*;
import com.example.plantsstore.DTO.responses.*;
import com.example.plantsstore.DTO.requests.*;
import com.example.plantsstore.repository.*;
import com.example.plantsstore.utility.*;

@Controller
@RequestMapping(path = "/supplyrequest")
public class SupplyRequestController {
  @Autowired
  private SupplierRepository supplierRepository;
  @Autowired
  private EmployeeRepository employeeRepository;
  @Autowired
  private DocumentRepository documentRepository;
  @Autowired
  private SupplyRequestRepository supplyRequestRepository;
  @Autowired
  private ProductRepository productRepository;
  @Autowired
  private SupplyRequestItemRepository supplyRequestItemRepository;
  @Autowired
  private SimpMessagingTemplate messagingTemplate;
  @Autowired
  private NotificationRepository notificationRepository;

  @PostMapping("/saveSupplyRequest")
  public ResponseEntity<?> saveSupplyRequest(@RequestBody SupplyRequestRequest.NewSupplyRequest supplyrequest) {
    try {
      if (Utility.isStringEmpty(supplyrequest.getSupplierId()) ||
          Utility.isStringEmpty(supplyrequest.getEmployeeId()) ||
          Utility.isStringEmpty(supplyrequest.getDocumentData())) {
        throw new Exception("Not enough data provided!");
      }

      Supplier supplier = supplierRepository.getById(supplyrequest.getSupplierId());
      if (supplier == null) {
        throw new Exception("Could not find selected supplier");
      }
      Employee employee = employeeRepository.getById(supplyrequest.getEmployeeId());
      if (employee == null) {
        throw new Exception("Could not find selected employee");
      }

      Document document = new Document(
          supplyrequest.getDocumentData(),
          supplyrequest.getDocumentName());
      documentRepository.save(document);

      SupplyRequest newSupplyRequest = new SupplyRequest(
          true,
          false,
          supplier,
          employee,
          document);
      supplyRequestRepository.save(newSupplyRequest);

      if (!supplyrequest.getRequestItems().isEmpty()) {
        List<SupplyRequestItem> requestItems = new ArrayList<>();
        supplyrequest.getRequestItems().forEach(item -> {
          requestItems.add(new SupplyRequestItem(
              newSupplyRequest,
              productRepository.getById(item.getProductId()),
              item.getQuantity(),
              item.getCostPerUnit(),
              item.getQuantity() * item.getCostPerUnit()));
        });
        supplyRequestItemRepository.saveAll(requestItems);
      }

      Notification notification = new Notification(
          "Supply request",
          newSupplyRequest.getId(),
          false,
          supplier.getUser());
      notificationRepository.save(notification);

      messagingTemplate.convertAndSend(
          "/topic/notif/" + supplier.getUser().getId(),
          "Supply request");

      return ResponseEntity.status(200).body("Success");
    } catch (Exception ex) {
      return ResponseEntity.status(400).body(ex.getMessage());
    }
  }

  @PostMapping("/saveSupplierSupplyRequest")
  public ResponseEntity<?> postMethodName(@RequestBody SupplyRequestRequest.NewSupplyRequest newSupplyRequest) {
    try {
      if (Utility.isStringEmpty(newSupplyRequest.getSupplierId()) ||
          Utility.isStringEmpty(newSupplyRequest.getEmployeeId()) ||
          Utility.isStringEmpty(newSupplyRequest.getDocumentData()) ||
          Utility.isStringEmpty(newSupplyRequest.getParentRequestId())) {
        throw new Exception("Not enough data provided!");
      }

      Supplier supplier = supplierRepository.getById(newSupplyRequest.getSupplierId());
      if (supplier == null) {
        throw new Exception("Could not find selected supplier");
      }
      Employee employee = employeeRepository.getById(newSupplyRequest.getEmployeeId());
      if (employee == null) {
        throw new Exception("Could not find selected employee");
      }
      SupplyRequest parenRequest = supplyRequestRepository.getById(newSupplyRequest.getParentRequestId());
      if (parenRequest == null) {
        throw new Exception("Could not find parent request");
      }

      Document document = new Document(
          newSupplyRequest.getDocumentData(),
          newSupplyRequest.getDocumentName());
      documentRepository.save(document);

      SupplyRequest supplyRequest = new SupplyRequest(
          false,
          true,
          supplier,
          employee,
          document,
          parenRequest);
      supplyRequestRepository.save(supplyRequest);

      Notification notification = new Notification(
          "Supply request from supplier",
          supplyRequest.getId(),
          false,
          employee.getUser());
      notificationRepository.save(notification);

      messagingTemplate.convertAndSend(
          "/topic/notif/" + employee.getUser().getId(),
          "Supply request from supplier");

      return ResponseEntity.status(200).body("Success");
    } catch (Exception ex) {
      return ResponseEntity.status(400).body(ex.getMessage());
    }
  }

  @GetMapping("/getSupplyRequestsForSupplier")
  public ResponseEntity<?> getSupplyRequestsSupplier(
      @RequestParam(value = "supplierId", required = false) String supplierId,
      @RequestParam(value = "limit") Integer limit,
      @RequestParam(value = "offset") Integer offset,
      @RequestParam(value = "query", required = false) String query) {
    try {
      Pageable pageable = PageRequest.of(offset, limit);
      Page<SupplyRequest> supplyRequestPage = null;

      if (Utility.isStringEmpty(query)) {
        supplyRequestPage = supplyRequestRepository
            .findAllBySupplierIdAndParentRequestIdNullAndApprovedByEmployeeAndApprovedBySupplierAndHasChildOrderByCreatedAtDesc(
                supplierId,
                true,
                true,
                false,
                pageable);
      } else {
        supplyRequestPage = supplyRequestRepository
            .findAllBySupplierIdAndParentRequestIdNullAndApprovedByEmployeeAndApprovedBySupplierAndHasChildAndNameContainingOrderByCreatedAtDesc(
                supplierId,
                true,
                true,
                false,
                query,
                pageable);
      }
      List<SupplyRequest> supplyRequestsContent = supplyRequestPage.getContent();

      List<SupplyRequestResponse.SupplyRequestAll> supplyRequests = new ArrayList<>();
      supplyRequestsContent.forEach(request -> {
        supplyRequests.add(new SupplyRequestResponse.SupplyRequestAll(
            request.getId(),
            request.getApprovedBySupplier(),
            request.getApprovedByEmployee(),
            new EmployeeResponse.Employee(
                request.getEmployee().getId(),
                request.getEmployee().getFirstName(),
                request.getEmployee().getLastName(),
                request.getEmployee().getPosition()),
            new SupplierResponse.Supplier(
                request.getSupplier().getId(),
                request.getSupplier().getName(),
                request.getSupplier().getContactPerson(),
                request.getSupplier().getPhone(),
                request.getSupplier().getEmail()),
            request.getName(),
            request.getDocument().getName(),
            request.getDocument().getDocument(),
            request.getCreatedAt()));
      });

      SupplyRequestResponse.SupplyRequestsPagination result = new SupplyRequestResponse.SupplyRequestsPagination(
          (int) supplyRequestPage.getTotalElements(),
          supplyRequests);
      return ResponseEntity.status(200).body(result);
    } catch (Exception ex) {
      return ResponseEntity.status(400).body(ex.getMessage());
    }
  }

  @GetMapping("/getSupplyRequests")
  public ResponseEntity<?> getSupplyRequests(
      @RequestParam(value = "employeeId", required = false) String employeeId,
      @RequestParam(value = "supplierId", required = false) String supplierId,
      @RequestParam(value = "limit") Integer limit,
      @RequestParam(value = "offset") Integer offset,
      @RequestParam(value = "isApprovedBySupplier", required = false) Boolean isApprovedBySupplier,
      @RequestParam(value = "isApprovedByEmployee", required = false) Boolean isApprovedByEmployee,
      @RequestParam(value = "isRequestToEmployee", required = false) Boolean isRequestToEmployee,
      @RequestParam(value = "isRequestToSupplier", required = false) Boolean isRequestToSupplier) {
    try {
      Pageable pageable = PageRequest.of(offset, limit);
      Page<SupplyRequest> supplyRequestPage = null;

      if (!Utility.isStringEmpty(employeeId)) {
        if (isRequestToSupplier) {
          supplyRequestPage = supplyRequestRepository
              .findAllByEmployeeIdAndParentRequestIdNullAndApprovedByEmployeeAndApprovedBySupplierOrderByCreatedAtDesc(
                  employeeId,
                  true,
                  isApprovedBySupplier,
                  pageable);
        } else if (isRequestToEmployee) {
          supplyRequestPage = supplyRequestRepository
              .findAllByEmployeeIdAndParentRequestIdNotNullAndApprovedByEmployeeAndApprovedBySupplierOrderByCreatedAtDesc(
                  employeeId,
                  isApprovedByEmployee,
                  true,
                  pageable);
        }
      } else if (!Utility.isStringEmpty(supplierId)) {
        if (isRequestToSupplier) {
          supplyRequestPage = supplyRequestRepository
              .findAllBySupplierIdAndParentRequestIdNullAndApprovedByEmployeeAndApprovedBySupplierOrderByCreatedAtDesc(
                  supplierId,
                  true,
                  isApprovedBySupplier,
                  pageable);
        } else if (isRequestToEmployee) {
          supplyRequestPage = supplyRequestRepository
              .findAllBySupplierIdAndParentRequestIdNotNullAndApprovedByEmployeeAndApprovedBySupplierOrderByCreatedAtDesc(
                  supplierId,
                  isApprovedByEmployee,
                  true,
                  pageable);
        }
      }

      List<SupplyRequest> supplyRequestsContent = supplyRequestPage.getContent();

      List<SupplyRequestResponse.SupplyRequestAll> supplyRequests = new ArrayList<>();
      supplyRequestsContent.forEach(request -> {
        supplyRequests.add(new SupplyRequestResponse.SupplyRequestAll(
            request.getId(),
            request.getApprovedBySupplier(),
            request.getApprovedByEmployee(),
            new EmployeeResponse.Employee(
                request.getEmployee().getId(),
                request.getEmployee().getFirstName(),
                request.getEmployee().getLastName(),
                request.getEmployee().getPosition()),
            new SupplierResponse.Supplier(
                request.getSupplier().getId(),
                request.getSupplier().getName(),
                request.getSupplier().getContactPerson(),
                request.getSupplier().getPhone(),
                request.getSupplier().getEmail()),
            request.getName(),
            request.getDocument().getName(),
            request.getDocument().getDocument(),
            request.getCreatedAt()));
      });

      SupplyRequestResponse.SupplyRequestsPagination result = new SupplyRequestResponse.SupplyRequestsPagination(
          (int) supplyRequestPage.getTotalElements(),
          supplyRequests);

      return ResponseEntity.status(200).body(result);
    } catch (Exception ex) {
      return ResponseEntity.status(400).body(ex.getMessage());
    }
  }

  @PostMapping("/signSupplyRequest")
  public ResponseEntity<?> signSupplyRequest(@RequestBody SupplyRequestRequest.SupplyRequestSigning requestSigning) {
    try {
      if (Utility.isStringEmpty(requestSigning.getRequestId()) ||
          Utility.isStringEmpty(requestSigning.getDocumentData())) {
        throw new Exception("Not enough info provided!");
      }

      SupplyRequest request = supplyRequestRepository.getById(requestSigning.getRequestId());
      if (request == null) {
        throw new Exception("Could not find request!");
      }

      User userForNotif = null;
      String notifcationType = "";
      SupplyRequest parentRequest = null;
      if (!Utility.isStringEmpty(requestSigning.getSupplierId())) {
        Supplier supplier = supplierRepository.getById(requestSigning.getSupplierId());
        if (supplier == null) {
          throw new Exception("Could not find related supplier!");
        }
        request.setApprovedBySupplier(true);
        userForNotif = request.getEmployee().getUser();
        notifcationType = "Supply request from supplier approved";
      } else if (!Utility.isStringEmpty(requestSigning.getEmployeeId())) {
        Employee employee = employeeRepository.getById(requestSigning.getEmployeeId());
        if (employee == null) {
          throw new Exception("Could not find related employee!");
        }
        parentRequest = request.getParentRequest();

        request.setApprovedByEmployee(true);
        userForNotif = request.getSupplier().getUser();
        notifcationType = "Supply request approved";
      }
      supplyRequestRepository.save(request);

      if (parentRequest != null) {
        parentRequest.setHasChild(true);
        supplyRequestRepository.save(parentRequest);
      }

      Document document = request.getDocument();
      if (document == null) {
        throw new Exception("Could not find related document!");
      }
      document.setDocument(requestSigning.getDocumentData());
      documentRepository.save(document);

      if (userForNotif != null) {
        Notification notification = new Notification(
            notifcationType,
            request.getId(),
            false,
            userForNotif);
        notificationRepository.save(notification);

        messagingTemplate.convertAndSend(
            "/topic/notif/" + userForNotif.getId(),
            notifcationType);
      }

      return ResponseEntity.status(200).body("Success");
    } catch (Exception ex) {
      return ResponseEntity.status(400).body(ex.getMessage());
    }
  }

  @GetMapping("/getRequestById")
  public ResponseEntity<?> getRequestById(@RequestParam(value = "requestId", required = true) String requestId) {
    try {
      SupplyRequest request = supplyRequestRepository.getById(requestId);
      if (request == null) {
        throw new Exception("Could not get request");
      }

      List<SupplyRequestItem> requestItems = supplyRequestItemRepository.getAllBySupplyRequestId(requestId);
      List<SupplyRequestResponse.SupplyRequestItem> requestItemsResult = new ArrayList<>();
      if (!requestItems.isEmpty()) {
        requestItems.forEach(item -> {
          requestItemsResult.add(new SupplyRequestResponse.SupplyRequestItem(
              item.getProduct().getName(),
              item.getAmount(),
              item.getCostPerUnit(),
              item.getTotal()));
        });
      }

      SupplyRequestResponse.SupplyRequestAll result = new SupplyRequestResponse.SupplyRequestAll(
          request.getId(),
          request.getApprovedBySupplier(),
          request.getApprovedByEmployee(),
          new EmployeeResponse.Employee(
              request.getEmployee().getId(),
              request.getEmployee().getFirstName(),
              request.getEmployee().getLastName(),
              request.getEmployee().getPosition()),
          new SupplierResponse.Supplier(
              request.getSupplier().getId(),
              request.getSupplier().getName(),
              request.getSupplier().getContactPerson(),
              request.getSupplier().getPhone(),
              request.getSupplier().getEmail()),
          request.getName(),
          request.getDocument().getName(),
          request.getDocument().getDocument(),
          requestItemsResult,
          request.getCreatedAt());

      return ResponseEntity.status(200).body(result);
    } catch (Exception ex) {
      return ResponseEntity.status(400).body(ex.getMessage());
    }
  }
}
