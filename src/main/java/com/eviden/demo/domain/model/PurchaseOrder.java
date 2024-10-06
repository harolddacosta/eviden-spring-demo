/* EVIDEN (C)2024 */
package com.eviden.demo.domain.model;

import lombok.Builder;
import lombok.Setter;
import lombok.Value;
import lombok.experimental.NonFinal;

import java.time.OffsetDateTime;
import java.util.List;

@Value
@Builder
public class PurchaseOrder {

    private Long id;
    private String purchaseOrderId;
    private String customerId;
    private OffsetDateTime purchaseOrderDate;
    @NonFinal @Setter private String status;
    private List<PurchaseOrderItem> items;
    private Double totalAmount;
    private ShippingAddress shippingAddress;
    private long version;
}
