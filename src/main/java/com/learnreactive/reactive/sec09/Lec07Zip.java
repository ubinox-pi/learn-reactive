package com.learnreactive.reactive.sec09;

/*
 * Copyright (c) 2026 Ramjee Prasad
 * Licensed under a custom Non-Commercial, Attribution, Share-Alike License.
 * See the LICENSE file in the project root for full license information.
 *
 * Project: reactive
 * Package: com.learnreactive.reactive.sec09
 * Created by: Ashish Kushwaha on 13-01-2026 20:06
 * File: Lec07Zip
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
import reactor.core.publisher.Flux;

import java.time.Duration;

/*
 * Zip
 * - we will subscribe to all required producer to same time
 * - all or nothing
 * - all producer have to emit an item
 * */
public class Lec07Zip {

    static void main() {
        Flux.zip(getBody(), getEngine(), getTires())
                .doOnNext(System.out::println)
                .subscribe(Util.subscriber("Ashish"));

        Util.sleep(2);
    }

    private static Flux<String> getBody() {
        return Flux.range(1, 5)
                .map(i -> "Body-" + 1)
                .delayElements(Duration.ofMillis(100));
    }

    private static Flux<String> getEngine() {
        return Flux.range(1, 3)
                .map(i -> "Engine-" + 1)
                .delayElements(Duration.ofMillis(200));
    }

    private static Flux<String> getTires() {
        return Flux.range(1, 10)
                .map(i -> "Tires-" + 1)
                .delayElements(Duration.ofMillis(75));
    }

}
