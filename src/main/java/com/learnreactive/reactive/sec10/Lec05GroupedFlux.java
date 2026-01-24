package com.learnreactive.reactive.sec10;

import com.learnreactive.reactive.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.GroupedFlux;
import reactor.core.publisher.Mono;

import java.time.Duration;

/*
 * Copyright (c) 2026 Ramjee Prasad
 * Licensed under a custom Non-Commercial, Attribution, Share-Alike License.
 * See the LICENSE file in the project root for full license information.
 *
 * Project: reactive
 * Package: com.learnreactive.reactive.sec10
 * Created by: Ashish Kushwaha on 24-01-2026 17:55
 * File: Lec05GroupedFlux
 *
 * This source code is intended for educational and non-commercial purposes only.
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *   - Attribution must be given to the original author.
 *   - The code must be shared under the same license.
 *   - Commercial use is strictly prohibited.
 *
 */
public class Lec05GroupedFlux {

    private static final Logger log = LoggerFactory.getLogger(Lec05GroupedFlux.class);

    static void main() {
        Flux.range(1, 30)
                .delayElements(Duration.ofSeconds(1))
//                .map(i -> i * 2) // for example purpose only
                .groupBy(i -> i % 2)
                .flatMap(Lec05GroupedFlux::processEvent)
                .subscribe(Util.subscriber("Ramjee"));

        Util.sleep(60);
    }

    private static Mono<Void> processEvent(GroupedFlux<Integer, Integer> groupedFlux) {
        log.info("Processing events for group {}", groupedFlux.key());
        return groupedFlux
                .doOnNext(i -> log.info("Group: {}, Value: {}", groupedFlux.key(), i))
                .then();
    }
}
