/* EVIDEN (C)2023 */
package com.eviden.demo.infrastructure.adapters.outgoing.persistence.jpa.transformer;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CustomConstraintNameResolver implements ContraintsNameResolver {

    private static Map<String, String> constraintCodeMap = new HashMap<>();

    static {
        constraintCodeMap.put("uq_for_productid", "exception.product.id.already.exists");
        constraintCodeMap.put("uq_for_productname", "exception.product.name.already.exists");
        constraintCodeMap.put("uq_for_purchaseorderid", "exception.purchaseoder.id.exists");
    }

    @Override
    public Map<String, String> getConstraintCodeMap() {
        return constraintCodeMap;
    }
}
