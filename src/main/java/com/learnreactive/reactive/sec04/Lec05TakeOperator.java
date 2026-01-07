package com.learnreactive.reactive.sec04;

import com.learnreactive.reactive.common.Util;
import reactor.core.publisher.Flux;

/*
 * Copyright (c) 2025 Ramjee Prasad
 * Licensed under a custom Non-Commercial, Attribution, Share-Alike License.
 * See the LICENSE file in the project root for full license information.
 *
 * Project: reactive
 * Package: com.learnreactive.reactive.sec04
 * Created by: Ashish Kushwaha on 12-11-2025 17:43
 * File: Lec05TakeOperator
 *
 * This source code is intended for educational and non-commercial purposes only.
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *   - Attribution must be given to the original author.
 *   - The code must be shared under the same license.
 *   - Commercial use is strictly prohibited.
 *
 */
public class Lec05TakeOperator {
    static void main() {
        takeUntil();
    }

    private static void takeUntil() {
        Flux.range(1, 10)
                .log("take")
                .takeUntil(i -> i < 6) // Stops when the condition met
                .log("sub")
                .subscribe(Util.subscriber("Ashish"));
    }

    private static void takeWhile() {
        Flux.range(1, 10)
                .log("take")
                .takeWhile(i -> i < 6) // Stops when the condition not met
                .log("sub")
                .subscribe(Util.subscriber("Ashish"));
    }

    private static void take() {
        Flux.range(1, 10)
                .log("take")
                .take(5)
                .log("sub")
                .subscribe(Util.subscriber("Ashish"));
    }
}
