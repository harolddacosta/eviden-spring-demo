/* EVIDEN (C)2023 */
package com.eviden.demo.infrastructure.adapters.outgoing.persistence.jpa.types;

import java.time.LocalDateTime;

public interface Auditable {

    String getCreatedBy();

    void setCreatedBy(String usuarioCreacion);

    LocalDateTime getCreatedDate();

    void setCreatedDate(LocalDateTime fechaCreacion);

    String getLastModifiedBy();

    void setLastModifiedBy(String usuarioModificacion);

    LocalDateTime getLastModifiedDate();

    void setLastModifiedDate(LocalDateTime fechaModificacion);
}
