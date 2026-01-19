package com.learnreactive.reactive.sec09;

import com.learnreactive.reactive.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;

/*
 * Copyright (c) 2026 Ramjee Prasad
 * Licensed under a custom Non-Commercial, Attribution, Share-Alike License.
 * See the LICENSE file in the project root for full license information.
 *
 * Project: reactive
 * Package: com.learnreactive.reactive.sec09
 * Created by: Ashish Kushwaha on 12-01-2026 15:15
 * File: Lec01StartWith
 *
 * This source code is intended for educational and non-commercial purposes only.
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *   - Attribution must be given to the original author.
 *   - The code must be shared under the same license.
 *   - Commercial use is strictly prohibited.
 *
 */
public class Lec01StartWith {
    private static final Logger log = LoggerFactory.getLogger(Lec01StartWith.class);

    static void main() {

        assignment();


        Util.sleep(3);
    }

    private static void demo() {
        producer1()
                .startWith(-1, 0)
                .subscribe(Util.subscriber("Ashish"));
    }

    private static void demo2() {
        producer1()
                .startWith(List.of(-2, -1, 0))
                .subscribe(Util.subscriber("Ashish"));
    }

    private static void demo3() {
        producer1()
                .startWith(producer2())
                .subscribe(Util.subscriber("Ashish"));
    }

    private static void demo4() {
        producer1()
                .startWith(producer2())
                .startWith(1000)
                .subscribe(Util.subscriber("Ashish"));
    }

    // 49, 50, 51, 52, 53, 0, 1, 2, 3
    private static void assignment() {
        producer1()
                .startWith(0, 1, 2, 3)
                .startWith(producer2())
                .startWith(49, 50)
                .subscribe(Util.subscriber("Ashish"));
    }

    private static Flux<Integer> producer1() {
        return Flux.just(1, 2, 3)
                .doOnSubscribe(s -> log.info("Subscribed to producer1"))
                .delayElements(Duration.ofMillis(10));
    }

    private static Flux<Integer> producer2() {
        return Flux.just(51, 52, 53)
                .doOnSubscribe(s -> log.info("Subscribed to producer2"))
                .delayElements(Duration.ofMillis(10));
    }

}
