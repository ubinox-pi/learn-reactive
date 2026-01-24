package com.learnreactive.reactive.sec10;

import com.learnreactive.reactive.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

/*
 * Copyright (c) 2026 Ramjee Prasad
 * Licensed under a custom Non-Commercial, Attribution, Share-Alike License.
 * See the LICENSE file in the project root for full license information.
 *
 * Project: reactive
 * Package: com.learnreactive.reactive.aec10
 * Created by: Ashish Kushwaha on 23-01-2026 21:55
 * File: Lec01Buffer
 *
 * This source code is intended for educational and non-commercial purposes only.
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *   - Attribution must be given to the original author.
 *   - The code must be shared under the same license.
 *   - Commercial use is strictly prohibited.
 *
 */
public class Lec01Buffer {
    static void main() {
        demo6();

        Util.sleep(60);
    }

    public static void demo1() {
        eventStream()
                .buffer()// waits till int max value or source to complete
                .subscribe(Util.subscriber("Ashish"));
    }

    public static void demo2() {
        eventStream()
                .buffer(3) // collects 3 items and then emits as List
                .subscribe(Util.subscriber("Ashish"));
    }

    public static void demo3() {
        eventStream2()
                .buffer(Duration.ofMillis(500)) // collects items for 500ms and then emits as List
                .subscribe(Util.subscriber("Ashish"));
    }

    public static void demo4() {
        eventStream2()
                .buffer(Duration.ofMillis(500)) // collects items for 500ms and then emits as List
                .subscribe(Util.subscriber("Ashish"));
    }

    public static void demo5() {
        eventStream3()
                .buffer(3)
                .subscribe(Util.subscriber("Ashish"));
    }

    public static void demo6() {
        eventStream3()
                .bufferTimeout(3, Duration.ofSeconds(1)) // emits after every 3 items or 1 second whichever is earlier
                .subscribe(Util.subscriber("Ashish"));
    }

    private static Flux<String> eventStream() {
        return Flux.interval(Duration.ofMillis(200))
                .take(10)
                .map(i -> "Event-" + (i + 1));
    }


    private static Flux<String> eventStream2() {
        return Flux.interval(Duration.ofMillis(200))
                .map(i -> "Event-" + (i + 1));
    }

    private static Flux<String> eventStream3() {
        return Flux.interval(Duration.ofMillis(200))
                .take(10)
                .concatWith(Flux.never()) // this never going to emit
                .map(i -> "Event-" + (i + 1));
    }
}
