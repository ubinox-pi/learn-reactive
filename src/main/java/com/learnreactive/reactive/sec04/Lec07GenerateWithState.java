package com.learnreactive.reactive.sec04;

import com.learnreactive.reactive.common.Util;
import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicInteger;

/*
 * Copyright (c) 2025 Ramjee Prasad
 * Licensed under a custom Non-Commercial, Attribution, Share-Alike License.
 * See the LICENSE file in the project root for full license information.
 *
 * Project: reactive
 * Package: com.learnreactive.reactive.sec04
 * Created by: Ashish Kushwaha on 12-11-2025 22:32
 * File: Lec07GenerateWithState
 *
 * This source code is intended for educational and non-commercial purposes only.
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *   - Attribution must be given to the original author.
 *   - The code must be shared under the same license.
 *   - Commercial use is strictly prohibited.
 *
 */
public class Lec07GenerateWithState {

    static AtomicInteger integer = new AtomicInteger(0);

    static void main() {
        Flux.generate(synchronousSink -> {
                    String country = Util.getFaker().country().name();
                    synchronousSink.next(country);
                    integer.incrementAndGet();
                    if (integer.get() == 20 || country.equalsIgnoreCase("INDIA")) synchronousSink.complete();
                })
                .subscribe(Util.subscriber("Ashish"));
    }
}
