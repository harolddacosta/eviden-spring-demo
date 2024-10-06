/* EVIDEN (C)2024 */
package com.eviden.demo.infrastructure.adapters.outgoing.batch.processors;

import com.eviden.demo.infrastructure.adapters.outgoing.persistence.jpa.entities.PurchaseOrderEntity;

import org.springframework.batch.item.ItemProcessor;

public class PurchaseOrderProcessor
        implements ItemProcessor<PurchaseOrderEntity, PurchaseOrderEntity> {

    @Override
    public PurchaseOrderEntity process(final PurchaseOrderEntity purchaseOrder) {
        purchaseOrder.setStatus("COMPLETED");

        return purchaseOrder;
    }
}
