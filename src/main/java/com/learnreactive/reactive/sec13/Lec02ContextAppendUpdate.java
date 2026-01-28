package com.learnreactive.reactive.sec13;

/*
 * Copyright (c) 2026 Ramjee Prasad
 * Licensed under a custom Non-Commercial, Attribution, Share-Alike License.
 * See the LICENSE file in the project root for full license information.
 *
 * Project: reactive
 * Package: com.learnreactive.reactive.sec13
 * Created by: Ashish Kushwaha on 28-01-2026 23:11
 * File: Lec02ContextAppendUpdate
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
 * Context is an immutable map. we can attach or update values in the context, but we cannot modify the existing context.
 * */
public class Lec02ContextAppendUpdate {

    private static final Logger log = LoggerFactory.getLogger(Lec02ContextAppendUpdate.class);

    static void main() {
        getWelcomeMessage()
                .contextWrite(ctx -> ctx.delete("user"))
                .contextWrite(Context.of("a", "b"))
                .contextWrite(Context.of("user", "Ramjee"))
                .subscribe(Util.subscriber("Ashish"));
    }

    // example for removing a key from the context
    private static void removeAKey() {
        getWelcomeMessage()
                .contextWrite(ctx -> ctx.delete("user"))
                .contextWrite(Context.of("a", "b"))
                .contextWrite(Context.of("user", "Ramjee"))
                .subscribe(Util.subscriber("Ashish"));
    }

    // example for updating values in the context
    private static void updating() {
        getWelcomeMessage()
                .contextWrite(ctx -> Context.of("user", "Ashish"))
                .contextWrite(Context.of("a", "b"))
                .contextWrite(Context.of("user", "Ramjee"))
                .subscribe(Util.subscriber("Ashish"));
    }

    // example for removing values from the context
    private static void removing() {
        getWelcomeMessage()
                .contextWrite(ctx -> Context.empty())
                .contextWrite(Context.of("a", "b"))
                .contextWrite(Context.of("user", "Ramjee"))
                .subscribe(Util.subscriber("Ashish"));
    }

    // example for appending values to the context
    private static void append() {
        getWelcomeMessage()
                .contextWrite(Context.of("a", "b"))
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
