/* EVIDEN (C)2021 */
package com.eviden.demo.infrastructure.adapters.outgoing.persistence.jpa.repositories;

import com.eviden.demo.infrastructure.adapters.outgoing.persistence.jpa.entities.ProductEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductJpaRepository extends JpaRepository<ProductEntity, Long> {

    Optional<ProductEntity> findByProductId(String productId);
}
