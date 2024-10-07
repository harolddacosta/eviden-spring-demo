/* EVIDEN (C)2024 */
package com.eviden.demo.infrastructure.adapters.incoming.event;

import com.eviden.demo.domain.avro.PurchaseOrderAvro;
import com.eviden.demo.domain.avro.PurchaseOrderItemAvro;
import com.eviden.demo.domain.avro.ShippingAddressAvro;
import com.eviden.demo.domain.model.PurchaseOrderEnum;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import net.datafaker.Faker;

import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.random.RandomGenerator;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaEventProducerMock {

    private final KafkaTemplate<String, PurchaseOrderAvro> kafkaEventTemplate;

    Faker faker = new Faker();

    @Scheduled(cron = "0/20 * * * * *")
    public void runKafkaEventProducer()
            throws JobParametersInvalidException, JobExecutionAlreadyRunningException,
                    JobRestartException, JobInstanceAlreadyCompleteException {
        kafkaEventTemplate.send(
                "v1.public.eviden.event-producer",
                PurchaseOrderAvro.newBuilder()
                        .setItems(
                                List.of(
                                        PurchaseOrderItemAvro.newBuilder()
                                                .setProductId(
                                                        "kafka_pr_"
                                                                + RandomGenerator.getDefault()
                                                                        .nextInt(1, 30000))
                                                .setProductName(
                                                        faker.book().title()
                                                                + " - "
                                                                + faker.book().author())
                                                .setQuantity(2)
                                                .setUnitPrice(123)
                                                .build()))
                        .setTotalAmount(4000)
                        .setStatus(PurchaseOrderEnum.PROCESSING.toString())
                        .setPurchaseOrderDate(LocalDateTime.now().toString())
                        .setPurchaseOrderId(
                                "kafka_order_" + RandomGenerator.getDefault().nextInt(1, 30000))
                        .setCustomerId(
                                "kafka_customer_" + RandomGenerator.getDefault().nextInt(1, 30000))
                        .setShippingAddress(
                                ShippingAddressAvro.newBuilder()
                                        .setCity("Caracas")
                                        .setCountry("Venezuela")
                                        .setState("Miranda")
                                        .setStreet("Los Teques")
                                        .setZipCode("008940")
                                        .build())
                        .build());
    }
}
