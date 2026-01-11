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
 * Created by: Ashish Kushwaha on 11-01-2026 20:03
 * File: Lec03MultipleSubscriber
 *
 * This source code is intended for educational and non-commercial purposes only.
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *   - Attribution must be given to the original author.
 *   - The code must be shared under the same license.
 *   - Commercial use is strictly prohibited.
 *
 */
public class Lec03MultipleSubscriber {
    private static final Logger log = LoggerFactory.getLogger(Lec03MultipleSubscriber.class);

    static void main() {
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
                .limitRate(5)
                .publishOn(Schedulers.boundedElastic())
                .map(Lec03MultipleSubscriber::process)
                .subscribe(Util.subscriber("Ramjee"));
        producer
                .take(100)
                .publishOn(Schedulers.boundedElastic())
                .subscribe(Util.subscriber("Ashish"));


        Util.sleep(60);
    }

    private static int process(int i) {
        log.info("time consuming task {}", i);
        Util.sleep(1);
        return i;
    }
}
