package com.solvd.mailservice.service;


import reactor.core.publisher.Mono;

import java.util.Map;

public interface Mail {

    /**
     * Sends an email.
     *
     * @param params parameters for the email
     * @return empty response
     */
    Mono<Void> send(Map<String, Object> params);

}
