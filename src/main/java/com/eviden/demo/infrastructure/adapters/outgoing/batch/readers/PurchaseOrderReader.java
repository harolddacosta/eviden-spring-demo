/* EVIDEN (C)2024 */
package com.eviden.demo.infrastructure.adapters.outgoing.batch.readers;

import com.eviden.demo.infrastructure.adapters.outgoing.persistence.jpa.entities.PurchaseOrderEntity;
import com.eviden.demo.infrastructure.adapters.outgoing.persistence.jpa.repositories.PurchaseOrderJpaRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import java.util.List;

@RequiredArgsConstructor
public class PurchaseOrderReader implements ItemReader<PurchaseOrderEntity> {

    private final PurchaseOrderJpaRepository purchaseOrderJpaRepository;

    int readHeaderIndex = 0;
    List<PurchaseOrderEntity> list = List.of();

    @Override
    public PurchaseOrderEntity read()
            throws Exception, UnexpectedInputException, ParseException,
                    NonTransientResourceException {
        if (list.isEmpty()) {
            list = purchaseOrderJpaRepository.findFirst10ByStatusOrderById("PENDING");
        }

        if (readHeaderIndex > 9) {
            return null;
        }

        try {
            return list.get(readHeaderIndex++);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }
}
