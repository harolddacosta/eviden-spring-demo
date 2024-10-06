/* EVIDEN (C)2024 */
package com.eviden.demo.infrastructure.adapters.incoming.http.controllers;

import com.eviden.demo.application.ports.incoming.orders.CancelPurchaseOrderUseCase;
import com.eviden.demo.application.ports.incoming.orders.CreatePurchaseOrderUseCase;
import com.eviden.demo.application.ports.incoming.orders.GetPurchaseOrderUseCase;
import com.eviden.demo.application.ports.incoming.orders.ListAllPurchaseOrdersUseCase;
import com.eviden.demo.domain.model.PurchaseOrder;
import com.eviden.demo.infrastructure.adapters.incoming.http.api.OrdersApi;
import com.eviden.demo.infrastructure.adapters.incoming.http.mappers.PurchaseOrderModelMapper;
import com.eviden.demo.infrastructure.adapters.incoming.http.model.order.PurchaseOrderPostRequest;
import com.eviden.demo.infrastructure.adapters.incoming.http.model.order.PurchaseOrderResponse;
import com.eviden.demo.infrastructure.adapters.incoming.http.model.order.PurchaseOrdersResponse;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PurchaseOrdersController extends BaseController implements OrdersApi {

    private final GetPurchaseOrderUseCase getOrderUseCase;
    private final ListAllPurchaseOrdersUseCase listAllOrdersUseCase;
    private final CreatePurchaseOrderUseCase createOrderUseCase;
    private final CancelPurchaseOrderUseCase deleteOrderUseCase;

    @Override
    public ResponseEntity<Void> createOrder(PurchaseOrderPostRequest orderPostRequest) {
        return ResponseEntity.created(
                        buildURIforCreatedResource(
                                "/orders",
                                createOrderUseCase.execute(
                                        PurchaseOrderModelMapper.INSTANCE.fromRequest(
                                                orderPostRequest))))
                .build();
    }

    @Override
    public ResponseEntity<PurchaseOrdersResponse> listOrders(Integer page, Integer size) {
        Page<PurchaseOrder> pageableResults =
                listAllOrdersUseCase.execute(PageRequest.of(page, size));

        return ResponseEntity.ok(
                PurchaseOrderModelMapper.INSTANCE.toPaginationResponse(pageableResults));
    }

    @Override
    public ResponseEntity<PurchaseOrderResponse> getOrder(String purchaseOrderId) {
        return getOrderUseCase
                .execute(purchaseOrderId)
                .map(
                        order ->
                                new ResponseEntity<>(
                                        PurchaseOrderModelMapper.INSTANCE.fromModel(order),
                                        HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<Void> cancelPurchaseOrder(String id) {
        deleteOrderUseCase.execute(id);

        return ResponseEntity.noContent().build();
    }
}
