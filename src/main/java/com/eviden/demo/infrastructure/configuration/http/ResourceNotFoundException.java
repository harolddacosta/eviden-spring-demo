/* EVIDEN (C)2023 */
package com.eviden.demo.infrastructure.configuration.http;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Indicates a resource was not found.
 *
 * @author Jon Brisbin
 * @author Oliver Gierke
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 7992904489502842099L;

    public ResourceNotFoundException() {
        this("EntityRepresentationModel not found!");
    }

    public ResourceNotFoundException(String message) {
        this(message, null);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
