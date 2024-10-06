/* EVIDEN (C)2024 */
package com.eviden.demo.application.ports.incoming.orders;

import com.eviden.demo.domain.model.PurchaseOrder;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@FunctionalInterface
public interface ListAllPurchaseOrdersUseCase {

    Page<PurchaseOrder> execute(PageRequest pageRequest);
}
