package com.learnreactive.reactive.sec06;

import com.learnreactive.reactive.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

/*
 * Copyright (c) 2026 Ramjee Prasad
 * Licensed under a custom Non-Commercial, Attribution, Share-Alike License.
 * See the LICENSE file in the project root for full license information.
 *
 * Project: reactive
 * Package: com.learnreactive.reactive.sec06
 * Created by: Ashish Kushwaha on 07-01-2026 22:01
 * File: Lec02HotPublisher
 *
 * This source code is intended for educational and non-commercial purposes only.
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *   - Attribution must be given to the original author.
 *   - The code must be shared under the same license.
 *   - Commercial use is strictly prohibited.
 *
 */
public class Lec02HotPublisher {

    private static final Logger log = LoggerFactory.getLogger(Lec02HotPublisher.class);

    static void main() {
        var movieFlux = movieStream().publish().refCount(1);

        Util.sleep(2);

        movieFlux.subscribe(Util.subscriber("Ashish"));

        Util.sleep(3);

        movieFlux.subscribe(Util.subscriber("Ramjee"));

        Util.sleep(15);
    }

    private static Flux<String> movieStream() {
        return Flux.generate(() -> {
                    log.info("Received the request");
                    return 1;
                },
                (state, sink) -> {
                    var scene = "Movie scene" + state;
                    log.info("playing {}", scene);
                    sink.next(scene);
                    return ++state;
                }
        ).take(10).delayElements(Duration.ofSeconds(1)).cast(String.class);
    }
}
