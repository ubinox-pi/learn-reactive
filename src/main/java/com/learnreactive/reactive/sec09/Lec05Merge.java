package com.learnreactive.reactive.sec09;

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
 * Package: com.learnreactive.reactive.sec09
 * Created by: Ashish Kushwaha on 13-01-2026 18:56
 * File: Lec05Merge
 *
 * This source code is intended for educational and non-commercial purposes only.
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *   - Attribution must be given to the original author.
 *   - The code must be shared under the same license.
 *   - Commercial use is strictly prohibited.
 *
 */
public class Lec05Merge {
    private static final Logger log = LoggerFactory.getLogger(Lec05Merge.class);

    private static Flux<Integer> producer1() {
        return Flux.just(1, 2, 3)
                .transform(Util.fluxLogger("ashish"))
                .delayElements(Duration.ofMillis(10));
    }

    private static Flux<Integer> producer2() {
        return Flux.just(51, 52, 53)
                .transform(Util.fluxLogger("ramjee"))
                .delayElements(Duration.ofMillis(10));
    }

    private static Flux<Integer> producer3() {
        return Flux.just(11, 12, 13)
                .transform(Util.fluxLogger("prasad"))
                .delayElements(Duration.ofMillis(10));
    }

    static void main() {

        demo2();

        Util.sleep(3);
    }

    public static void demo1() {
        Flux.merge(producer1(), producer2(), producer3())
                .subscribe(Util.subscriber("Ashish"));
    }

    public static void demo2() {
        producer2()
                .mergeWith(producer1())
                .mergeWith(producer3())
                .subscribe(Util.subscriber("Ashish"));
    }


}
