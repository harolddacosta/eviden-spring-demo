/* EVIDEN (C)2024 */
package com.eviden.demo.infrastructure.adapters.outgoing.persistence.jpa.mappers;

import com.eviden.demo.domain.model.PurchaseOrder;
import com.eviden.demo.domain.model.PurchaseOrderItem;
import com.eviden.demo.infrastructure.adapters.outgoing.persistence.jpa.entities.PurchaseOrderEntity;
import com.eviden.demo.infrastructure.adapters.outgoing.persistence.jpa.entities.PurchaseOrderItemEntity;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PurchaseOrderEntityMapper {

    PurchaseOrderEntityMapper INSTANCE = Mappers.getMapper(PurchaseOrderEntityMapper.class);

    @Mapping(source = "shippingAddress.city", target = "city")
    @Mapping(source = "shippingAddress.street", target = "street")
    @Mapping(source = "shippingAddress.state", target = "state")
    @Mapping(source = "shippingAddress.zipCode", target = "zipCode")
    @Mapping(source = "shippingAddress.country", target = "country")
    PurchaseOrderEntity fromModel(PurchaseOrder model);

    @Mapping(source = "productId", target = "product.productId")
    @Mapping(source = "productName", target = "product.productName")
    PurchaseOrderItemEntity fromModel(PurchaseOrderItem model);

    @InheritInverseConfiguration(name = "fromModel")
    PurchaseOrder fromEntity(PurchaseOrderEntity entity);

    @InheritInverseConfiguration(name = "fromModel")
    PurchaseOrderItem fromEntity(PurchaseOrderItemEntity entity);

    List<PurchaseOrder> fromEntities(List<PurchaseOrderEntity> entities);
}
