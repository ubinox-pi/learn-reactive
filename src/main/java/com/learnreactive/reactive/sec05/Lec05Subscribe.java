package com.learnreactive.reactive.sec05;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

/*
 * Copyright (c) 2025 Ramjee Prasad
 * Licensed under a custom Non-Commercial, Attribution, Share-Alike License.
 * See the LICENSE file in the project root for full license information.
 *
 * Project: reactive
 * Package: com.learnreactive.reactive.sec05
 * Created by: Ashish Kushwaha on 16-11-2025 19:56
 * File: Lec05Subscribe
 *
 * This source code is intended for educational and non-commercial purposes only.
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *   - Attribution must be given to the original author.
 *   - The code must be shared under the same license.
 *   - Commercial use is strictly prohibited.
 *
 */
public class Lec05Subscribe {

    private static final Logger log = LoggerFactory.getLogger(Lec05Subscribe.class);

    static void main() {
        Flux.range(1, 10)
                .doOnNext(i -> log.info("doOnNext: {}", i))
                .doOnComplete(() -> log.info("doOnComplete"))
                .doOnError(e -> log.error("doOnError", e))
                .subscribe();
    }
}
