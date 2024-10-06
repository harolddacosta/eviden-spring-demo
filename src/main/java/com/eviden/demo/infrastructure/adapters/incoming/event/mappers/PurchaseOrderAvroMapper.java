/* EVIDEN (C)2024 */
package com.eviden.demo.infrastructure.adapters.incoming.event.mappers;

import com.eviden.demo.domain.avro.PurchaseOrderAvro;
import com.eviden.demo.domain.model.PurchaseOrder;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.time.OffsetDateTime;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PurchaseOrderAvroMapper {

    PurchaseOrderAvroMapper INSTANCE = Mappers.getMapper(PurchaseOrderAvroMapper.class);

    PurchaseOrder fromAvro(PurchaseOrderAvro avro);

    default OffsetDateTime map(String dateTimeString) {
        return OffsetDateTime.now();
    }
}
