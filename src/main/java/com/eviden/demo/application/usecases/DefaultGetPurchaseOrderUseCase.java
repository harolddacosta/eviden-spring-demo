/* EVIDEN (C)2024 */
package com.eviden.demo.application.usecases;

import com.eviden.demo.application.ports.incoming.orders.GetPurchaseOrderUseCase;
import com.eviden.demo.application.ports.outgoing.repositories.PurchaseOrderRepository;
import com.eviden.demo.domain.model.PurchaseOrder;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DefaultGetPurchaseOrderUseCase implements GetPurchaseOrderUseCase {

    private final PurchaseOrderRepository ordersRepository;

    @Override
    public Optional<PurchaseOrder> execute(String purchaseOrderId) {
        return ordersRepository.findOrderById(purchaseOrderId);
    }
}
