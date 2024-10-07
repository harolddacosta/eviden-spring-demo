/* EVIDEN (C)2024 */
package com.eviden.demo.domain.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PurchaseOrderItem {

    private String productId;
    private String productName;
    private Integer quantity;
    private Double unitPrice;
}
