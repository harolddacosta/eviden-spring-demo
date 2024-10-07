/* EVIDEN (C)2023 */
package com.eviden.demo.infrastructure.adapters.outgoing.persistence.jpa;

import com.eviden.demo.application.ports.outgoing.repositories.PurchaseOrderRepository;
import com.eviden.demo.domain.model.PurchaseOrder;
import com.eviden.demo.domain.model.PurchaseOrderEnum;
import com.eviden.demo.infrastructure.adapters.outgoing.persistence.jpa.entities.PurchaseOrderEntity;
import com.eviden.demo.infrastructure.adapters.outgoing.persistence.jpa.mappers.PurchaseOrderEntityMapper;
import com.eviden.demo.infrastructure.adapters.outgoing.persistence.jpa.repositories.ProductJpaRepository;
import com.eviden.demo.infrastructure.adapters.outgoing.persistence.jpa.repositories.PurchaseOrderJpaRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DefaultPurchaseOrdersAdapter implements PurchaseOrderRepository {

    private final PurchaseOrderJpaRepository jpaRepository;
    private final ProductJpaRepository productJpaRepository;

    @Override
    @Transactional(readOnly = false)
    public String createOrder(PurchaseOrder order) {
        PurchaseOrderEntity entity = PurchaseOrderEntityMapper.INSTANCE.fromModel(order);

        entity.getItems().stream()
                .forEach(
                        item -> {
                            item.setPurchaseOrder(entity);
                            item.setProduct(
                                    productJpaRepository
                                            .findByProductId(item.getProduct().getProductId())
                                            .orElseGet(
                                                    () ->
                                                            productJpaRepository.save(
                                                                    item.getProduct())));
                        });

        return jpaRepository.save(entity).getPurchaseOrderId();
    }

    @Override
    public Page<PurchaseOrder> listAllOrders(PageRequest pageRequest) {
        Page<PurchaseOrderEntity> paginatedItems =
                jpaRepository.findAll(pageRequest.withSort(Sort.by("purchaseOrderId").ascending()));

        return new PageImpl<>(
                PurchaseOrderEntityMapper.INSTANCE.fromEntities(paginatedItems.getContent()),
                pageRequest,
                paginatedItems.getTotalElements());
    }

    @Override
    @Transactional(readOnly = false)
    public void cancelOrder(String orderId) {
        Optional<PurchaseOrderEntity> purchaseOrder =
                Optional.ofNullable(jpaRepository.findByPurchaseOrderId(orderId));

        purchaseOrder.ifPresent(order -> order.setStatus(PurchaseOrderEnum.CANCELLED.toString()));
    }

    @Override
    public Optional<PurchaseOrder> findOrderById(String orderId) {
        return Optional.ofNullable(
                PurchaseOrderEntityMapper.INSTANCE.fromEntity(
                        jpaRepository.findByPurchaseOrderId(orderId)));
    }
}
