/* EVIDEN (C)2024 */
package com.eviden.demo.infrastructure.adapters.outgoing.persistence.jpa.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder
@ToString(onlyExplicitlyIncluded = true, callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(
        name = "product",
        uniqueConstraints = {
            @UniqueConstraint(
                    name = "uq_for_productId",
                    columnNames = {"productId"}),
            @UniqueConstraint(
                    name = "uq_for_productName",
                    columnNames = {"productName"})
        })
public class ProductEntity extends BaseEntity {

    private static final long serialVersionUID = -3859316078497457897L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @ToString.Include
    private Long id;

    @Column(nullable = false)
    @EqualsAndHashCode.Include
    @ToString.Include
    private String productId;

    @Column(nullable = false)
    private String productName;
}
