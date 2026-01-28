package com.learnreactive.reactive.sec12;

import com.learnreactive.reactive.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Sinks;

import java.time.Duration;

/*
 * Copyright (c) 2026 Ramjee Prasad
 * Licensed under a custom Non-Commercial, Attribution, Share-Alike License.
 * See the LICENSE file in the project root for full license information.
 *
 * Project: reactive
 * Package: com.learnreactive.reactive.sec12
 * Created by: Ashish Kushwaha on 28-01-2026 21:59
 * File: Lec05MulticastDirectBestEffort
 *
 * This source code is intended for educational and non-commercial purposes only.
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *   - Attribution must be given to the original author.
 *   - The code must be shared under the same license.
 *   - Commercial use is strictly prohibited.
 *
 */
public class Lec05MulticastDirectBestEffort {

    private static final Logger log = LoggerFactory.getLogger(Lec05MulticastDirectBestEffort.class);

    static void main() {
        demo3();
    }

    /*
     * When we have multiple, subscribers, is one subscriber is slow,
     * we might not able to safely deliver messages to all the subscribers /
     * other fast subscribers may miss messages.
     * */
    public static void demo1() {

        System.setProperty("reactor.bufferSize.small", "16");

        // handle through which we would push items
        // onBackPressureBuffer - unbounded queue
        var sink = Sinks.many().multicast().onBackpressureBuffer();

        // handle through which subscribers will receive items.
        var flux = sink.asFlux();

        flux.subscribe(Util.subscriber("Ashish"));
        flux.delayElements(Duration.ofMillis(200)).subscribe(Util.subscriber("Kumar"));
        for (int i = 0; i < 100; i++) {
            var result = sink.tryEmitNext(i);
            log.info("Emitted: item: {} - result: {}", i, result);
        }

        Util.sleep(10);

    }

    public static void demo2() {

        System.setProperty("reactor.bufferSize.small", "16");

        // handle through which we would push items
        // onBackPressureBuffer - unbounded queue
        var sink = Sinks.many().multicast().directBestEffort();

        // handle through which subscribers will receive items.
        var flux = sink.asFlux();

        flux.subscribe(Util.subscriber("Ashish"));
        flux.delayElements(Duration.ofMillis(200)).subscribe(Util.subscriber("Kumar"));
        for (int i = 0; i < 100; i++) {
            var result = sink.tryEmitNext(i);
            log.info("Emitted: item: {} - result: {}", i, result);
        }

        Util.sleep(10);

    }

    public static void demo3() {

        System.setProperty("reactor.bufferSize.small", "16");

        // handle through which we would push items
        // onBackPressureBuffer - unbounded queue
        var sink = Sinks.many().multicast().directBestEffort();

        // handle through which subscribers will receive items.
        var flux = sink.asFlux();

        flux.subscribe(Util.subscriber("Ashish"));
        flux.onBackpressureBuffer().delayElements(Duration.ofMillis(200)).subscribe(Util.subscriber("Kumar"));
        for (int i = 0; i < 100; i++) {
            var result = sink.tryEmitNext(i);
            log.info("Emitted: item: {} - result: {}", i, result);
        }

        Util.sleep(10);

    }
}
