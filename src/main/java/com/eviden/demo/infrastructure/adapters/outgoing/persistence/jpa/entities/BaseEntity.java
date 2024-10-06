/* EVIDEN (C)2023 */
package com.eviden.demo.infrastructure.adapters.outgoing.persistence.jpa.entities;

import com.eviden.demo.infrastructure.adapters.outgoing.persistence.jpa.types.Auditable;
import com.eviden.demo.infrastructure.adapters.outgoing.persistence.jpa.types.Identifiable;
import com.eviden.demo.infrastructure.adapters.outgoing.persistence.jpa.types.Versionable;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@SuperBuilder
@EntityListeners({AuditingEntityListener.class})
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public abstract class BaseEntity
        implements Identifiable, Versionable, Auditable, java.io.Serializable {

    private static final long serialVersionUID = 1L;

    @Version
    @Column(columnDefinition = "bigint default 0")
    @EqualsAndHashCode.Include
    @ToString.Include
    private long version;

    @Column(updatable = false)
    @CreatedDate
    private LocalDateTime createdDate;

    @Column @LastModifiedDate private LocalDateTime lastModifiedDate;

    @Column(updatable = false)
    @CreatedBy
    private String createdBy;

    @Column @LastModifiedBy private String lastModifiedBy;
}
