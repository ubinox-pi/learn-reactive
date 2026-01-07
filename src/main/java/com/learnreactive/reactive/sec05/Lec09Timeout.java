package com.learnreactive.reactive.sec05;

import com.learnreactive.reactive.common.Util;
import reactor.core.publisher.Mono;

import java.time.Duration;

/*
 * Copyright (c) 2025 Ramjee Prasad
 * Licensed under a custom Non-Commercial, Attribution, Share-Alike License.
 * See the LICENSE file in the project root for full license information.
 *
 * Project: reactive
 * Package: com.learnreactive.reactive.sec05
 * Created by: Ashish Kushwaha on 22-12-2025 01:08
 * File: Lec09Timeout
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
 * timeout - will produce error if the source Mono/Flux doesn't emit any item within the given duration
 *         - we can also specify a fallback Mono/Flux to be used in case of timeout
 *         - we can also handle as part of onError methods
 *         - there is also an overloaded method to accept the publisher
 *         - we can have multiple timeouts in a single stream. the closest one will be applied
 * */
public class Lec09Timeout {
    static void main() {

//        getProductName()
//                .timeout(Duration.ofSeconds(1), fallback())
//                .onErrorReturn("Product not found")
//                .subscribe(Util.subscriber("Ashish"));

        var mono = getProductName()
                .timeout(Duration.ofSeconds(1), fallback());

        mono.timeout(Duration.ofMillis(5000), fallback())
                .subscribe(Util.subscriber("Ashish"));


        Util.sleep(5);

    }

    private static Mono<String> getProductName() {
        return Mono.fromSupplier(() -> "Service" + Util.getFaker().commerce().productName())
                .delayElement(Duration.ofSeconds(3));
    }

    private static Mono<String> fallback() {
        return Mono.fromSupplier(() -> "Fallback" + Util.getFaker().commerce().productName())
                .delayElement(Duration.ofSeconds(3))
                .doFirst(() -> System.out.println("Fallback called"));
    }
}
