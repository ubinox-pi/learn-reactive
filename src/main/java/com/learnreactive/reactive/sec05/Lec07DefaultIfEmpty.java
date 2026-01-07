package com.learnreactive.reactive.sec05;

/*
 * Copyright (c) 2025 Ramjee Prasad
 * Licensed under a custom Non-Commercial, Attribution, Share-Alike License.
 * See the LICENSE file in the project root for full license information.
 *
 * Project: reactive
 * Package: com.learnreactive.reactive.sec05
 * Created by: Ashish Kushwaha on 21-12-2025 23:44
 * File: Lec07DefaultIfEmpty
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
import reactor.core.publisher.Mono;

/*
 * Similar to error handling
 * Handling empty streams
 * Flux.<Integer>empty()
 *       .defaultIfEmpty(0)
 *       .subscribe(Util.subscriber("Ashish"));
 * */
public class Lec07DefaultIfEmpty {

    static void main() {
        Mono.empty()
                .defaultIfEmpty("This is a fall back value")
                .subscribe(Util.subscriber("Ashish"));
        Mono.just("ashish")
                .defaultIfEmpty("This is a fall back value")
                .subscribe(Util.subscriber("Ashish"));

        Flux.range(1, 10)
                .filter(i -> i > 0)
                .defaultIfEmpty(-1)
                .subscribe(Util.subscriber("Ashish"));
        Flux.range(1, 10)
                .filter(i -> i > 11)
                .defaultIfEmpty(-1)
                .subscribe(Util.subscriber("Ashish"));
    }
}
