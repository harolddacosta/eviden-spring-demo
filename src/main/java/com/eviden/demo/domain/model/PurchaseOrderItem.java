/* EVIDEN (C)2024 */
package com.eviden.demo.domain.model;

import lombok.Value;

@Value
public class PurchaseOrderItem {

    private String productId;
    private String productName;
    private Integer quantity;
    private Double unitPrice;
}
