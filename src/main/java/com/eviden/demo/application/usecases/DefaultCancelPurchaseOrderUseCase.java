/* EVIDEN (C)2024 */
package com.eviden.demo.application.usecases;

import com.eviden.demo.application.ports.incoming.orders.CancelPurchaseOrderUseCase;
import com.eviden.demo.application.ports.outgoing.repositories.PurchaseOrderRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultCancelPurchaseOrderUseCase implements CancelPurchaseOrderUseCase {

    private final PurchaseOrderRepository ordersRepository;

    @Override
    public void execute(String orderId) {
        ordersRepository.cancelOrder(orderId);
    }
}
