/* EVIDEN (C)2024 */
package com.eviden.demo.infrastructure.adapters.incoming.http.mappers.utils;

import com.eviden.demo.infrastructure.adapters.incoming.http.model.order.PaginationResponse;

import org.springframework.data.domain.Page;

public class PaginationUtils {

    private PaginationUtils() {
        // Utility class
    }

    public static <T> PaginationResponse getPaginationDetails(Page<T> pageEntity) {
        PaginationResponse retValue = new PaginationResponse();

        retValue.setCurrentPage(pageEntity.getPageable().getPageNumber());
        retValue.setIsLastPage(pageEntity.isLast());
        retValue.setPageElements(pageEntity.getNumberOfElements());
        retValue.setPageSize(pageEntity.getPageable().getPageSize());
        retValue.setTotalElements(pageEntity.getTotalElements());
        retValue.setTotalPages(pageEntity.getTotalPages());

        return retValue;
    }
}
