package com.learnreactive.reactive.sec07;

/*
 * Copyright (c) 2026 Ramjee Prasad
 * Licensed under a custom Non-Commercial, Attribution, Share-Alike License.
 * See the LICENSE file in the project root for full license information.
 *
 * Project: reactive
 * Package: com.learnreactive.reactive.sec07
 * Created by: Ashish Kushwaha on 10-01-2026 18:47
 * File: Lec03MultipleSubscribeOn
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
import reactor.core.scheduler.Schedulers;

/*
 * we can have multiple subscribeOn
 * the closest to the source will take the precedence!
 * */
public class Lec03MultipleSubscribeOn {
    private static final Logger log = LoggerFactory.getLogger(Lec03MultipleSubscribeOn.class);

    static void main() {
        var flux = Flux.create(fluxSink -> {
                    for (int i = 0; i < 3; i++) {
                        log.info("Emitting {}", i);
                        fluxSink.next(i);
                    }
                    fluxSink.complete();
                })
                .subscribeOn(Schedulers.newParallel("my-parallel-scheduler"))
//                .subscribeOn(Schedulers.immediate())
                .doOnNext(i -> log.info("Processing {}", i))
                .doFirst(() -> log.info("First 1"))
                .subscribeOn(Schedulers.boundedElastic())
                .doFirst(() -> log.info("First 2"));

        Runnable runnable = () -> flux.subscribe(Util.subscriber("Ramjee"));
        Runnable runnable2 = () -> flux.subscribe(Util.subscriber("Ashish"));

        Thread.ofPlatform().start(runnable);

        Util.sleep(2);
    }
}
