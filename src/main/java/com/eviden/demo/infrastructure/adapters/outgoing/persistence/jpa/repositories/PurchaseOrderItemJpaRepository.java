/* EVIDEN (C)2021 */
package com.eviden.demo.infrastructure.adapters.outgoing.persistence.jpa.repositories;

import com.eviden.demo.infrastructure.adapters.outgoing.persistence.jpa.entities.PurchaseOrderItemEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseOrderItemJpaRepository
        extends JpaRepository<PurchaseOrderItemEntity, Long> {}
