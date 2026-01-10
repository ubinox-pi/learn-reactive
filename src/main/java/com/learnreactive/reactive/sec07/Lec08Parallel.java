package com.learnreactive.reactive.sec07;

import com.learnreactive.reactive.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/*
 * Copyright (c) 2026 Ramjee Prasad
 * Licensed under a custom Non-Commercial, Attribution, Share-Alike License.
 * See the LICENSE file in the project root for full license information.
 *
 * Project: reactive
 * Package: com.learnreactive.reactive.sec07
 * Created by: Ashish Kushwaha on 10-01-2026 19:53
 * File: Lec08Parallel
 *
 * This source code is intended for educational and non-commercial purposes only.
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *   - Attribution must be given to the original author.
 *   - The code must be shared under the same license.
 *   - Commercial use is strictly prohibited.
 *
 */
public class Lec08Parallel {
    private static final Logger log = LoggerFactory.getLogger(Lec08Parallel.class);

    static void main() {
        Flux.range(1, 10)
                .parallel(10)
                .runOn(Schedulers.parallel())
                .map(Lec08Parallel::process)
                .sequential() // make those print in sequence
                .map(i -> i + 'a')
                .subscribe(Util.subscriber("Ashish"));

        Util.sleep(10);
    }

    private static int process(int i) {
        log.info("time consuming task {}", i);
        Util.sleep(2);
        return i * 2;
    }
}
