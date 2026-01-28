package com.learnreactive.reactive.sec13.client;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/*
 * Copyright (c) 2026 Ramjee Prasad
 * Licensed under a custom Non-Commercial, Attribution, Share-Alike License.
 * See the LICENSE file in the project root for full license information.
 *
 * Project: reactive
 * Package: com.learnreactive.reactive.sec13.client
 * Created by: Ashish Kushwaha on 28-01-2026 23:43
 * File: RateLimiter
 *
 * This source code is intended for educational and non-commercial purposes only.
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *   - Attribution must be given to the original author.
 *   - The code must be shared under the same license.
 *   - Commercial use is strictly prohibited.
 *
 */
public class RateLimiter {

    private static final Map<String, Integer> categoryAttempts = Collections.synchronizedMap(new HashMap<>());

    static {
        refresh();
    }

    public static <T> Mono<T> limitCalls() {
        return Mono.deferContextual(ctx -> {
            var allowCall = ctx.<String>getOrEmpty("category")
                    .map(RateLimiter::isAllowed)
                    .orElse(false);
            return allowCall ? Mono.empty() : Mono.error(new RuntimeException("exceed the given limit"));
        });
    }

    private static synchronized boolean isAllowed(String category) {
        var attempts = categoryAttempts.getOrDefault(category, 0);
        if (attempts > 0) {
            categoryAttempts.put(category, attempts - 1);
            return true;
        }
        return false;
    }

    private static void refresh() {
        Flux.interval(Duration.ofSeconds(5))
                .startWith(0L)
                .subscribe(i -> {
                    categoryAttempts.put("standard", 2);
                    categoryAttempts.put("premium", 3);
                });
    }
}
