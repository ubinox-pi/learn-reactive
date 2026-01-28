package com.learnreactive.reactive.sec13;

/*
 * Copyright (c) 2026 Ramjee Prasad
 * Licensed under a custom Non-Commercial, Attribution, Share-Alike License.
 * See the LICENSE file in the project root for full license information.
 *
 * Project: reactive
 * Package: com.learnreactive.reactive.sec13
 * Created by: Ashish Kushwaha on 28-01-2026 22:59
 * File: Lec01Context
 *
 * This source code is intended for educational and non-commercial purposes only.
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *   - Attribution must be given to the original author.
 *   - The code must be shared under the same license.
 *   - Commercial use is strictly prohibited.
 *
 */

import com.learnreactive.reactive.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

/*
 * Context is for providing metadata about the request (similar to HTTP headers)
 * */
public class Lec01Context {

    private static final Logger log = LoggerFactory.getLogger(Lec01Context.class);

    static void main() {
        getWelcomeMessage()
                .contextWrite(Context.of("user", "Ramjee"))
                .subscribe(Util.subscriber("Ashish"));
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
}
