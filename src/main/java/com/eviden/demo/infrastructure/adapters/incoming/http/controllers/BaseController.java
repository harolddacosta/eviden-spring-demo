/* EVIDEN (C)2023 */
package com.eviden.demo.infrastructure.adapters.incoming.http.controllers;

import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.net.URI;

public class BaseController {

    protected URI buildURIforCreatedResource(String resource, String id) {
        return MvcUriComponentsBuilder.fromController(getClass())
                .path(resource + "/{id}")
                .buildAndExpand(id)
                .toUri();
    }
}
