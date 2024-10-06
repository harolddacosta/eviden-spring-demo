/* EVIDEN (C)2024 */
package com.eviden.demo.infrastructure.adapters.incoming.event;

import com.eviden.demo.application.ports.incoming.orders.CreatePurchaseOrderUseCase;
import com.eviden.demo.domain.avro.PurchaseOrderAvro;
import com.eviden.demo.infrastructure.adapters.incoming.event.mappers.PurchaseOrderAvroMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DefaultEventConsumerHandler {

    private final CreatePurchaseOrderUseCase createPurchaseOrderUseCase;

    @KafkaListener(
            topics = "v1.public.eviden.event-producer",
            groupId = "group-consumer-order-purchase",
            containerFactory = "kafkaListenerContainerFactoryForOrderPurchase")
    public void listenUsers(@Payload PurchaseOrderAvro purchaseOrderAvro) {
        log.info("Receiving event topic:{}", purchaseOrderAvro);

        createPurchaseOrderUseCase.execute(
                PurchaseOrderAvroMapper.INSTANCE.fromAvro(purchaseOrderAvro));
    }
}
