package com.learnreactive.reactive.sec11;

/*
 * Copyright (c) 2026 Ramjee Prasad
 * Licensed under a custom Non-Commercial, Attribution, Share-Alike License.
 * See the LICENSE file in the project root for full license information.
 *
 * Project: reactive
 * Package: com.learnreactive.reactive.sec11
 * Created by: Ashish Kushwaha on 24-01-2026 19:56
 * File: Lec02Retry
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
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

/*
 * retry operator simply resubscribe when it sees an error signal,
 * it does not like complete signal.
 * */
public class Lec02Retry {

    private static final Logger log = LoggerFactory.getLogger(Lec02Retry.class);

    static void main() {
        demo2();

        Util.sleep(10);
    }

    private static void demo1() {
        getCountry()
                .retry(3)
                .subscribe(Util.subscriber("Ashish"));
    }

    private static void demo2() {
        getCountry()
                .retryWhen(
                        Retry.fixedDelay(3, Duration.ofSeconds(1))
                                .doBeforeRetry(retrySignal -> log.info("Retrying {} time", retrySignal))
                                .filter(ex -> RuntimeException.class.equals(ex.getClass()))
                                .onRetryExhaustedThrow((spec, signal) -> signal.failure())
                )
                .subscribe(Util.subscriber("Ashish"));
    }

    private static Mono<String> getCountry() {
        var atomicInteger = new AtomicInteger(0);
        return Mono.fromSupplier(() -> {
                    if (atomicInteger.getAndIncrement() < 3) {
                        throw new RuntimeException("Error");
                    }
                    return Util.getFaker().country().name();
                }).doOnError(err -> log.error("Error {}", err.getMessage()))
                .doOnSubscribe(s -> log.info("Subscribing {}", s)); //non-blocking IO
    }
}
