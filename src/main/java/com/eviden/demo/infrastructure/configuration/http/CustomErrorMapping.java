/* EVIDEN (C)2023 */
package com.eviden.demo.infrastructure.configuration.http;

import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.zalando.problem.spring.web.advice.ProblemHandling;

@RestControllerAdvice
public class CustomErrorMapping implements ProblemHandling {

    // Note: si las excepciones se declaran como AbstractThrowableProblem, no es necesaria mapearlas
    // aqui, solo se mapearan las excepciones que no extiendan de AbstractThrowableProblem, y
    // luego aqui es donde se transformaran a Problem

}
