/* EVIDEN (C)2024 */
package com.eviden.demo.application.ports.incoming.orders;

import com.eviden.demo.domain.model.PurchaseOrder;

@FunctionalInterface
public interface CreatePurchaseOrderUseCase {

    String execute(PurchaseOrder order);
}
