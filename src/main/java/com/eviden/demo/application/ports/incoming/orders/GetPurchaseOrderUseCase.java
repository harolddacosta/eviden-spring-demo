/* EVIDEN (C)2024 */
package com.eviden.demo.application.ports.incoming.orders;

import com.eviden.demo.domain.model.PurchaseOrder;

import java.util.Optional;

@FunctionalInterface
public interface GetPurchaseOrderUseCase {

    Optional<PurchaseOrder> execute(String id);
}
