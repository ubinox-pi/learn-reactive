package com.learnreactive.reactive.sec13;

import com.learnreactive.reactive.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.context.Context;

/*
 * Copyright (c) 2026 Ramjee Prasad
 * Licensed under a custom Non-Commercial, Attribution, Share-Alike License.
 * See the LICENSE file in the project root for full license information.
 *
 * Project: reactive
 * Package: com.learnreactive.reactive.sec13
 * Created by: Ashish Kushwaha on 28-01-2026 23:20
 * File: Lec03ContextPropogation
 *
 * This source code is intended for educational and non-commercial purposes only.
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *   - Attribution must be given to the original author.
 *   - The code must be shared under the same license.
 *   - Commercial use is strictly prohibited.
 *
 */
public class Lec03ContextPropagation {

    private static final Logger log = LoggerFactory.getLogger(Lec03ContextPropagation.class);

    static void main() {
        getWelcomeMessage()
                .concatWith(Flux.merge(producer1(), producer2().contextWrite(_ -> Context.empty()))) // overriding context for producer2
                .contextWrite(Context.of("user", "Ramjee"))
                .subscribe(Util.subscriber("Ashish"));

        Util.sleep(2);
    }

    public static Mono<String> getWelcomeMessage() {
        return Mono.deferContextual(context -> {
            log.info("Context: {}", context);
            if (context.hasKey("user")) {
                return Mono.just("Welcome %s to the Reactive World!".formatted(context.get("user").toString()));
            }
            return Mono.error(new RuntimeException("unauthenticated"));
        });
    }

    private static Mono<String> producer1() {
        return Mono.<String>deferContextual(contextView -> {
                    log.info("Producer1: {}", contextView);
                    return Mono.empty();
                })
                .subscribeOn(Schedulers.boundedElastic());
    }

    private static Mono<String> producer2() {
        return Mono.<String>deferContextual(contextView -> {
                    log.info("Producer2: {}", contextView);
                    return Mono.empty();
                })
                .subscribeOn(Schedulers.parallel());
    }
}
