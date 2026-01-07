package com.learnreactive.reactive.sec2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

/*
 * Copyright (c) 2025 Ramjee Prasad
 * Licensed under a custom Non-Commercial, Attribution, Share-Alike License.
 * See the LICENSE file in the project root for full license information.
 *
 * Project: reactive
 * Package: com.learnreactive.reactive.sec2
 * Created by: Ashish Kushwaha on 21-10-2025 05:33
 * File: Lec03MonoSubscribe
 *
 * This source code is intended for educational and non-commercial purposes only.
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *   - Attribution must be given to the original author.
 *   - The code must be shared under the same license.
 *   - Commercial use is strictly prohibited.
 *
 */
public class Lec03MonoSubscribe {

    private static final Logger log = LoggerFactory.getLogger(Lec03MonoSubscribe.class);

    static void main() {
        var mono = Mono.just(1)
                .map(i -> i + 2);

        mono.subscribe(i -> log.info("Received {}", i),
                err -> new RuntimeException("error", err),
                () -> log.info("Completed!"),
                subscription -> subscription.request(1));
    }
}
