package com.learnreactive.reactive.sec08;

import com.learnreactive.reactive.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

/*
 * Copyright (c) 2026 Ramjee Prasad
 * Licensed under a custom Non-Commercial, Attribution, Share-Alike License.
 * See the LICENSE file in the project root for full license information.
 *
 * Project: reactive
 * Package: com.learnreactive.reactive.sec08
 * Created by: Ashish Kushwaha on 11-01-2026 20:07
 * File: Lec04FluxCreate
 *
 * This source code is intended for educational and non-commercial purposes only.
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *   - Attribution must be given to the original author.
 *   - The code must be shared under the same license.
 *   - Commercial use is strictly prohibited.
 *
 */
public class Lec04FluxCreate {
    private static final Logger log = LoggerFactory.getLogger(Lec04FluxCreate.class);

    static void main() {
        System.setProperty("reactor.bufferSize.small", "16");

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
//                .limitRate(5) // this cant work in this case where the producer can't handle backpressure automatically
                .publishOn(Schedulers.boundedElastic())
                .map(Lec04FluxCreate::process)
                .subscribe(Util.subscriber("Ramjee"));


        Util.sleep(60);
    }

    private static int process(int i) {
        log.info("time consuming task {}", i);
        Util.sleep(1);
        return i;
    }
}
