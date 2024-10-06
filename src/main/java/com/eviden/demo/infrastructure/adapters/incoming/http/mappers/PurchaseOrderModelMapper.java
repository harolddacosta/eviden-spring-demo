/* EVIDEN (C)2023 */
package com.eviden.demo.infrastructure.adapters.incoming.http.mappers;

import com.eviden.demo.domain.model.PurchaseOrder;
import com.eviden.demo.infrastructure.adapters.incoming.http.mappers.utils.PaginationUtils;
import com.eviden.demo.infrastructure.adapters.incoming.http.model.order.PurchaseOrderPostRequest;
import com.eviden.demo.infrastructure.adapters.incoming.http.model.order.PurchaseOrderResponse;
import com.eviden.demo.infrastructure.adapters.incoming.http.model.order.PurchaseOrdersResponse;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper
public interface PurchaseOrderModelMapper {

    PurchaseOrderModelMapper INSTANCE = Mappers.getMapper(PurchaseOrderModelMapper.class);

    PurchaseOrderResponse fromModel(PurchaseOrder model);

    List<PurchaseOrderResponse> fromModelList(List<PurchaseOrder> modelList);

    PurchaseOrder fromRequest(PurchaseOrderPostRequest requestBody);

    default PurchaseOrdersResponse toPaginationResponse(Page<PurchaseOrder> page) {
        return new PurchaseOrdersResponse(
                fromModelList(page.getContent()), PaginationUtils.getPaginationDetails(page));
    }
}
