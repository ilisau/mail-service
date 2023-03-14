package com.solvd.mailservice.service;


import reactor.core.publisher.Mono;

import java.util.Map;

public interface Mail {

    Mono<Void> send(Map<String, Object> params);

}
