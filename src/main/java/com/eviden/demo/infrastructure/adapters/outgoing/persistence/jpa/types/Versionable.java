/* EVIDEN (C)2023 */
package com.eviden.demo.infrastructure.adapters.outgoing.persistence.jpa.types;

public interface Versionable {

    long getVersion();

    void setVersion(long version);
}
