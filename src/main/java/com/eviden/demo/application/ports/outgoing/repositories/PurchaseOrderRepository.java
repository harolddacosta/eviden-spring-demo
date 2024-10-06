/* EVIDEN (C)2024 */
package com.eviden.demo.application.ports.outgoing.repositories;

import com.eviden.demo.domain.model.PurchaseOrder;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

public interface PurchaseOrderRepository {

    String createOrder(PurchaseOrder order);

    void cancelOrder(String orderId);

    Page<PurchaseOrder> listAllOrders(PageRequest pageRequest);

    Optional<PurchaseOrder> findOrderById(String orderId);
}
