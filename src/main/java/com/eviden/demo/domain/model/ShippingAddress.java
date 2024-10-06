/* EVIDEN (C)2024 */
package com.eviden.demo.domain.model;

import lombok.Value;

@Value
public class ShippingAddress {

    private String street;
    private String city;
    private String state;
    private String zipCode;
    private String country;
}
