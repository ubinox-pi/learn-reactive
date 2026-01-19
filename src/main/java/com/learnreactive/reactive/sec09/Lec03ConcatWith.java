package com.learnreactive.reactive.sec09;

import com.learnreactive.reactive.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

/*
 * Copyright (c) 2026 Ramjee Prasad
 * Licensed under a custom Non-Commercial, Attribution, Share-Alike License.
 * See the LICENSE file in the project root for full license information.
 *
 * Project: reactive
 * Package: com.learnreactive.reactive.sec09
 * Created by: Ashish Kushwaha on 13-01-2026 18:30
 * File: Lec03ConcatWith
 *
 * This source code is intended for educational and non-commercial purposes only.
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *   - Attribution must be given to the original author.
 *   - The code must be shared under the same license.
 *   - Commercial use is strictly prohibited.
 *
 */
public class Lec03ConcatWith {

    private static final Logger log = LoggerFactory.getLogger(Lec03ConcatWith.class);

    private static Flux<Integer> producer1() {
        return Flux.just(1, 2, 3)
                .doOnSubscribe(s -> log.info("Subscribed to producer1"))
                .delayElements(Duration.ofMillis(10));
    }

    private static Flux<Integer> producer2() {
        return Flux.just(51, 52, 53)
                .doOnSubscribe(s -> log.info("Subscribed to producer2"))
                .delayElements(Duration.ofMillis(10))
                .cast(Integer.class);
    }

    static void main() {

        demo3();


        Util.sleep(3);
    }

    private static void demo() {
        producer1()
                .concatWithValues(-1, 0)
                .subscribe(Util.subscriber("Ashish"));
    }

    private static void demo2() {
        producer1()
                .concatWith(producer2())
                .subscribe(Util.subscriber("Ashish"));
    }

    private static void demo3() {
        Flux.concat(producer1(), producer2())
                .subscribe(Util.subscriber("Ashish"));
    }

}
