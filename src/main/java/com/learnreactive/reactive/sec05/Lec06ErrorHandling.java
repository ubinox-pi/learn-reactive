package com.learnreactive.reactive.sec05;

import com.learnreactive.reactive.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/*
 * Copyright (c) 2025 Ramjee Prasad
 * Licensed under a custom Non-Commercial, Attribution, Share-Alike License.
 * See the LICENSE file in the project root for full license information.
 *
 * Project: reactive
 * Package: com.learnreactive.reactive.sec05
 * Created by: Ashish Kushwaha on 21-11-2025 22:09
 * File: Lec06ErrorHandling
 *
 * This source code is intended for educational and non-commercial purposes only.
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *   - Attribution must be given to the original author.
 *   - The code must be shared under the same license.
 *   - Commercial use is strictly prohibited.
 *
 */
public class Lec06ErrorHandling {

    /*
     * How to handle error in reactive programming
     * Flux.(...)
     *       ...
     *       ...
     *       ...
     *       ...
     *
     */
    private static final Logger log = LoggerFactory.getLogger(Lec06ErrorHandling.class);

    static void main() {
        Flux.range(1, 10)
                .map(i -> i == 5 ? 5 / 0 : i)
                .onErrorContinue((error, obj) -> log.info("Error occurred: /{} for /{}", error.getMessage(), obj))
                .subscribe(Util.subscriber("Ashish"));
    }

    // In case of error, complete the stream
    private static void onErrorComplete() {
        Mono.just(1)
                .onErrorComplete()
                .subscribe(Util.subscriber("Ashish"));
    }

    // When you want to return a hardcoded value / simple computation
    private static void onErrorReturn() {
        Mono.just(5)
                .map(i -> i == 5 ? 5 / 0 : i)
                .onErrorReturn(IllegalArgumentException.class, -1)
                .onErrorReturn(ArithmeticException.class, -2)
                .onErrorReturn(-3)
                .subscribe(Util.subscriber("Ashish"));
    }

    // When you have to use another publisher in case of error
    private static void onErrorResume() {
        Mono.error(new RuntimeException("Error"))
                .onErrorResume(RuntimeException.class, _ -> fallback())
                .onErrorResume(_ -> fallback2())
                .onErrorReturn(-5)
                .subscribe(Util.subscriber("Ashish"));
    }

    private static Mono<Integer> fallback() {
        return Mono.fromSupplier(() -> Util.getFaker().random().nextInt(10, 100));
    }

    private static Mono<Integer> fallback2() {
        return Mono.fromSupplier(() -> Util.getFaker().random().nextInt(100, 10000));
    }
}
