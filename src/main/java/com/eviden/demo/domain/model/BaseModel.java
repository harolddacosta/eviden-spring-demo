/* EVIDEN (C)2023 */
package com.eviden.demo.domain.model;

import lombok.Value;

import java.util.UUID;

@Value
public class BaseModel {

    private Long id;
    private UUID code = UUID.randomUUID();
}
