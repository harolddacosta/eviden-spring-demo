/* EVIDEN (C)2024 */
package com.eviden.demo.application.ports.incoming.orders;

@FunctionalInterface
public interface CancelPurchaseOrderUseCase {

    void execute(String orderId);
}
