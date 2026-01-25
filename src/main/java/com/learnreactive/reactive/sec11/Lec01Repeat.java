package com.learnreactive.reactive.sec11;

import com.learnreactive.reactive.common.Util;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

/*
 * Copyright (c) 2026 Ramjee Prasad
 * Licensed under a custom Non-Commercial, Attribution, Share-Alike License.
 * See the LICENSE file in the project root for full license information.
 *
 * Project: reactive
 * Package: com.learnreactive.reactive.sec11
 * Created by: Ashish Kushwaha on 24-01-2026 19:29
 * File: Lec01Repeat
 *
 * This source code is intended for educational and non-commercial purposes only.
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *   - Attribution must be given to the original author.
 *   - The code must be shared under the same license.
 *   - Commercial use is strictly prohibited.
 *
 */

/*
 * a repeat operator simply resubscribes when it sees a complete signal,
 * it does not like error signal.
 * */
public class Lec01Repeat {
    static void main() {
        demo4();

        Util.sleep(60);
    }

    private static void demo1() {
        getCountry()
                .repeat(2)
                .subscribe(Util.subscriber("Ashish"));
    }

    private static void demo2() {
        getCountry()
                .repeat()
                .takeUntil(c -> c.equalsIgnoreCase("canada"))
                .subscribe(Util.subscriber("Ashish"));
    }

    private static void demo3() {
        var atomicInteger = new AtomicInteger(0);
        getCountry()
                .repeat(() -> atomicInteger.getAndIncrement() < 3)
                .subscribe(Util.subscriber("Ashish"));
    }

    private static void demo4() {
        getCountry()
                .repeatWhen(flux -> flux.delayElements(Duration.ofMillis(2000)).take(2)) // repeat after every 2 sec for 2 items
                .subscribe(Util.subscriber("Ashish"));
    }

    private static Mono<String> getCountry() {
        return Mono.fromSupplier(() -> Util.getFaker().country().name()); //non-blocking IO
    }
}
