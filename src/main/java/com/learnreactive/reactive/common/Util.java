package com.learnreactive.reactive.common;

import com.github.javafaker.Faker;
import org.reactivestreams.Subscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.function.UnaryOperator;

/*
 * Copyright (c) 2025 Ramjee Prasad
 * Licensed under a custom Non-Commercial, Attribution, Share-Alike License.
 * See the LICENSE file in the project root for full license information.
 *
 * Project: reactive
 * Package: com.learnreactive.reactive.common
 * Created by: Ashish Kushwaha on 22-10-2025 05:19
 * File: Utli
 *
 * This source code is intended for educational and non-commercial purposes only.
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *   - Attribution must be given to the original author.
 *   - The code must be shared under the same license.
 *   - Commercial use is strictly prohibited.
 *
 */
public class Util {

    private static final Faker faker = Faker.instance();
    private static final Logger log = LoggerFactory.getLogger(Util.class);

    static void main(String[] args) {
        Mono<String> mono = Mono.just("Hello, World!");
        mono.subscribe(subscriber("Ashish"));
        mono.subscribe(subscriber("Ramjee"));
    }

    public static <T> Subscriber<T> subscriber(String name) {
        return new DefaultSubscriber<>(name);
    }

    public static Faker getFaker() {
        return faker;
    }

    public static void sleep(long seconds) {
        try {
            Thread.sleep(Duration.ofSeconds(seconds));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void sleep(Duration duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> UnaryOperator<Flux<T>> fluxLogger(String name) {
        return flux -> flux
                .doOnSubscribe(s -> log.info("{} subscribed to {}", name, flux))
                .doOnCancel(() -> log.info("{} cancelled", name))
                .doOnComplete(() -> log.info("{} completed", name));
    }
}
