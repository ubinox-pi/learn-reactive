package com.learnreactive.reactive.sec06;

/*
 * Copyright (c) 2026 Ramjee Prasad
 * Licensed under a custom Non-Commercial, Attribution, Share-Alike License.
 * See the LICENSE file in the project root for full license information.
 *
 * Project: reactive
 * Package: com.learnreactive.reactive.sec06
 * Created by: Ashish Kushwaha on 08-01-2026 17:52
 * File: Lec04HotPublisherCache
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

import java.time.Duration;

/*
 * publish().autoConnect(0) will provide new values to the subscriber
 * replay allows us to cache
 * */
public class Lec04HotPublisherCache {

    private static final Logger log = LoggerFactory.getLogger(Lec04HotPublisherCache.class);

    static void main() {
        var stockFlux = stockPriceStream().replay(10).autoConnect(0);

        Util.sleep(4);

        log.info("ashish is joining.");

        stockFlux
                .subscribe(Util.subscriber("Ashish"));

        Util.sleep(4);

        log.info("ramjee is joining.");

        stockFlux
                .subscribe(Util.subscriber("Ramjee"));

        Util.sleep(15);
    }

    private static Flux<Integer> stockPriceStream() {
        return Flux.generate(sink -> sink.next(Util.getFaker().random().nextInt(10, 100)))
                .doOnNext(price -> log.info("Emitting price is {}", price))
                .delayElements(Duration.ofSeconds(3))
                .cast(Integer.class);
    }
}
