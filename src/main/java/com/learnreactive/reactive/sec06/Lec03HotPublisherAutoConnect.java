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
 * Created by: Ashish Kushwaha on 08-01-2026 17:45
 * File: Lec03HotPublisherAutoConnect
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
 * almost same as publish().refCount(1)
 * does not stop when subscriber cancel, so it will start producing even for 0 subscribers once it started
 * make it real hot publisher - publish().autoConnect(0)
 * */
public class Lec03HotPublisherAutoConnect {

    private static final Logger log = LoggerFactory.getLogger(Lec03HotPublisherAutoConnect.class);

    static void main() {
        var movieFlux = movieStream().publish().autoConnect(0);

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
