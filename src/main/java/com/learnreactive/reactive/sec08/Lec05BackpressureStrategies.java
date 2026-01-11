package com.learnreactive.reactive.sec08;

/*
 * Copyright (c) 2026 Ramjee Prasad
 * Licensed under a custom Non-Commercial, Attribution, Share-Alike License.
 * See the LICENSE file in the project root for full license information.
 *
 * Project: reactive
 * Package: com.learnreactive.reactive.sec08
 * Created by: Ashish Kushwaha on 11-01-2026 22:54
 * File: Lec05BackpressureStrategies
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
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

/*
 * Reactor provides backpressure strategies
 * - buffer
 * - drop
 * - latest
 * - error
 * */
public class Lec05BackpressureStrategies {
    private static final Logger log = LoggerFactory.getLogger(Lec05BackpressureStrategies.class);

    static void main() {
        var producer = Flux.create(
                        sink -> {
                            for (int i = 0; i < 500 && !sink.isCancelled(); i++) {
                                log.info("Generating {}", i);
                                sink.next(i);
                                Util.sleep(Duration.ofMillis(50));
                            }
                            sink.complete();
                        }
                ).cast(Integer.class)
                .subscribeOn(Schedulers.boundedElastic());

        producer
//                .onBackpressureBuffer() // example one
//                .onBackpressureError() //example two
//                .onBackpressureBuffer(10) // example three - beyond this number error will occur
//                .onBackpressureDrop() // example four - drop the elements
//                .onBackpressureDrop(con -> {
//                    log.info("Dropped element {}", con);
//                    log.info("Total dropped elements {}", con);
//                })
                .onBackpressureLatest() // example five - keep only the latest element (similar to drop)
                .limitRate(1)
                .publishOn(Schedulers.boundedElastic())
                .map(Lec05BackpressureStrategies::process)
                .subscribe(Util.subscriber("Ramjee"));


        Util.sleep(60);
    }

    private static int process(int i) {
        log.info("time consuming task {}", i);
        Util.sleep(1);
        return i;
    }
}
