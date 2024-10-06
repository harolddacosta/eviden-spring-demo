/* EVIDEN (C)2024 */
package com.eviden.demo.application.usecases;

import com.eviden.demo.application.ports.incoming.orders.CreatePurchaseOrderUseCase;
import com.eviden.demo.application.ports.outgoing.repositories.PurchaseOrderRepository;
import com.eviden.demo.domain.model.PurchaseOrder;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultCreatePurchaseOrderUseCase implements CreatePurchaseOrderUseCase {

    private final PurchaseOrderRepository ordersRepository;

    @Override
    public String execute(PurchaseOrder order) {
        return ordersRepository.createOrder(order);
    }
}
