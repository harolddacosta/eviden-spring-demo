/* EVIDEN (C)2024 */
package com.eviden.demo.infrastructure.adapters.outgoing.persistence.jpa.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.OffsetDateTime;
import java.util.Set;

@Entity
@SuperBuilder
@ToString(onlyExplicitlyIncluded = true, callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(
        name = "purchase_order",
        uniqueConstraints = {
            @UniqueConstraint(
                    name = "uq_for_purchaseOrderId",
                    columnNames = {"purchaseOrderId"})
        })
public class PurchaseOrderEntity extends BaseEntity {

    private static final long serialVersionUID = -4980325869288917221L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @ToString.Include
    private Long id;

    @EqualsAndHashCode.Include
    @ToString.Include
    @Column(nullable = false)
    private String purchaseOrderId;

    @Column private OffsetDateTime purchaseOrderDate;
    @Column private String customerId;
    @Column private String status;
    @Column private Double totalAmount;

    @Column private String street;
    @Column private String city;
    @Column private String state;
    @Column private String zipCode;
    @Column private String country;

    @Column
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "purchaseOrder")
    private Set<PurchaseOrderItemEntity> items;
}
