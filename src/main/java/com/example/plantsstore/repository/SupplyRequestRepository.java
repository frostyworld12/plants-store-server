package com.example.plantsstore.repository;

import org.springframework.data.domain.*;
import org.springframework.data.repository.*;
import com.example.plantsstore.model.*;

public interface SupplyRequestRepository extends CrudRepository<SupplyRequest, String> {
    SupplyRequest getById(String id);
    Page<SupplyRequest> findAllByEmployeeIdAndParentRequestIdNotNullAndApprovedByEmployeeAndApprovedBySupplierOrderByCreatedAtDesc(
        String employeeId,
        Boolean approvedByEmployee,
        Boolean approvedBySupplier,
        Pageable pageable
    );
    Page<SupplyRequest> findAllByEmployeeIdAndParentRequestIdNullAndApprovedByEmployeeAndApprovedBySupplierOrderByCreatedAtDesc(
        String employeeId,
        Boolean approvedByEmployee,
        Boolean approvedBySupplier,
        Pageable pageable
    );

    Page<SupplyRequest> findAllBySupplierIdAndParentRequestIdNotNullAndApprovedByEmployeeAndApprovedBySupplierOrderByCreatedAtDesc(
        String supplierId,
        Boolean approvedByEmployee,
        Boolean approvedBySupplier,
        Pageable pageable
    );
    Page<SupplyRequest> findAllBySupplierIdAndParentRequestIdNullAndApprovedByEmployeeAndApprovedBySupplierOrderByCreatedAtDesc(
        String supplierId,
        Boolean approvedByEmployee,
        Boolean approvedBySupplier,
        Pageable pageable
    );
    Page<SupplyRequest> findAllBySupplierIdAndParentRequestIdNullAndApprovedByEmployeeAndApprovedBySupplierAndHasChildOrderByCreatedAtDesc(
        String supplierId,
        Boolean approvedByEmployee,
        Boolean approvedBySupplier,
        Boolean hasChild,
        Pageable pageable
    );
    Page<SupplyRequest> findAllBySupplierIdAndParentRequestIdNullAndApprovedByEmployeeAndApprovedBySupplierAndHasChildAndNameContainingOrderByCreatedAtDesc(
        String supplierId,
        Boolean approvedByEmployee,
        Boolean approvedBySupplier,
        Boolean hasChild,
        String name,
        Pageable pageable
    );
}
