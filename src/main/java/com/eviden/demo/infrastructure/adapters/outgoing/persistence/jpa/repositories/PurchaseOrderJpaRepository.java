/* EVIDEN (C)2021 */
package com.eviden.demo.infrastructure.adapters.outgoing.persistence.jpa.repositories;

import com.eviden.demo.infrastructure.adapters.outgoing.persistence.jpa.entities.PurchaseOrderEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseOrderJpaRepository extends JpaRepository<PurchaseOrderEntity, Long> {

    PurchaseOrderEntity findByPurchaseOrderId(String purchaseOrderId);

    List<PurchaseOrderEntity> findFirst10ByStatusOrderById(String status);
}
