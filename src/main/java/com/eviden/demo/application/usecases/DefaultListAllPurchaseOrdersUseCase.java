/* EVIDEN (C)2024 */
package com.eviden.demo.application.usecases;

import com.eviden.demo.application.ports.incoming.orders.ListAllPurchaseOrdersUseCase;
import com.eviden.demo.application.ports.outgoing.repositories.PurchaseOrderRepository;
import com.eviden.demo.domain.model.PurchaseOrder;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultListAllPurchaseOrdersUseCase implements ListAllPurchaseOrdersUseCase {

    private final PurchaseOrderRepository ordersRepository;

    @Override
    public Page<PurchaseOrder> execute(PageRequest pageRequest) {
        return ordersRepository.listAllOrders(pageRequest);
    }
}
