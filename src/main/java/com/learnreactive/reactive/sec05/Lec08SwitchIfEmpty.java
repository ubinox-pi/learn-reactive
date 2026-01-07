package com.learnreactive.reactive.sec05;

/*
 * Copyright (c) 2025 Ramjee Prasad
 * Licensed under a custom Non-Commercial, Attribution, Share-Alike License.
 * See the LICENSE file in the project root for full license information.
 *
 * Project: reactive
 * Package: com.learnreactive.reactive.sec05
 * Created by: Ashish Kushwaha on 21-12-2025 23:49
 * File: Lec08SwitchIfEmpty
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

/*
 * Similar to error handling
 * handling empty streams
 * */
public class Lec08SwitchIfEmpty {

    private static final Logger log = LoggerFactory.getLogger(Lec08SwitchIfEmpty.class);

    static void main() {

        /*
         * example
         * postgres + redis
         *
         * first search in redis if it empty then query from database
         * */

        Flux.range(1, 10)
                .filter(i -> i > 11)
                .switchIfEmpty(fallback())
                .subscribe(Util.subscriber("Ashish"));
    }

    private static Flux<Integer> fallback() {
        return Flux.range(100, 3);
    }
}
