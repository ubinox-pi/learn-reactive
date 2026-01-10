package com.learnreactive.reactive.sec07;

/*
 * Copyright (c) 2026 Ramjee Prasad
 * Licensed under a custom Non-Commercial, Attribution, Share-Alike License.
 * See the LICENSE file in the project root for full license information.
 *
 * Project: reactive
 * Package: com.learnreactive.reactive.sec07
 * Created by: Ashish Kushwaha on 10-01-2026 18:04
 * File: Lec01DefaultBehaviourDemo
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

/*
 * By default, the current thread is doing all the work
 * */
public class Lec01DefaultBehaviourDemo {
    private static final Logger log = LoggerFactory.getLogger(Lec01DefaultBehaviourDemo.class);

    static void main() {
        var flux = Flux.create(sink -> {
            for (int i = 0; i < 3; i++) {
                log.info("Emitting {}", i);
                sink.next(i);
            }
            sink.complete();
        }).doOnNext(i -> log.info("Processing {}", i));

        Runnable runnable = () -> flux.subscribe(Util.subscriber("Ramjee"));

        Thread.ofPlatform().start(runnable);
    }
}
