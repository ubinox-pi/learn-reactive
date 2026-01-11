package com.learnreactive.reactive.sec08;

import com.learnreactive.reactive.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/*
 * Copyright (c) 2026 Ramjee Prasad
 * Licensed under a custom Non-Commercial, Attribution, Share-Alike License.
 * See the LICENSE file in the project root for full license information.
 *
 * Project: reactive
 * Package: com.learnreactive.reactive.sec08
 * Created by: Ashish Kushwaha on 11-01-2026 19:30
 * File: Lec01BackPressureHandling
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
 * Reactor automatically handle the back pressure
 * System.setProperty("reactor.bufferSize.small", "16");
 * */
public class Lec01BackPressureHandling {
    private static final Logger log = LoggerFactory.getLogger(Lec01BackPressureHandling.class);

    static void main() {

        System.setProperty("reactor.bufferSize.small", "16");

        var producer = Flux.generate(
                        () -> 1,
                        (state, sink) -> {
                            log.info("Generation {}", state);
                            sink.next(state);
                            return ++state;
                        }
                ).cast(Integer.class)
                .subscribeOn(Schedulers.boundedElastic());

        producer
                .publishOn(Schedulers.boundedElastic())
                .map(Lec01BackPressureHandling::process)
                .subscribe(Util.subscriber("Ramjee"));


        Util.sleep(60);
    }

    private static int process(int i) {
        log.info("time consuming task {}", i);
        Util.sleep(1);
        return i;
    }
}
