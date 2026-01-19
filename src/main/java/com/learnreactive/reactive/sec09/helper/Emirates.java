package com.learnreactive.reactive.sec09.helper;

import com.learnreactive.reactive.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

/*
 * Copyright (c) 2026 Ramjee Prasad
 * Licensed under a custom Non-Commercial, Attribution, Share-Alike License.
 * See the LICENSE file in the project root for full license information.
 *
 * Project: reactive
 * Package: com.learnreactive.reactive.sec09.helper
 * Created by: Ashish Kushwaha on 13-01-2026 19:51
 * File: Emirates
 *
 * This source code is intended for educational and non-commercial purposes only.
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *   - Attribution must be given to the original author.
 *   - The code must be shared under the same license.
 *   - Commercial use is strictly prohibited.
 *
 */
public class Emirates {

    private static final String AIRLINE = "Emirates";

    public static Flux<Flight> getAirline() {
        return Flux.range(1, Util.getFaker().random().nextInt(2, 10))
                .delayElements(Duration.ofMillis(Util.getFaker().random().nextInt(200, 1000)))
                .map(i -> new Flight(AIRLINE, Util.getFaker().number().numberBetween(300, 1000)))
                .transform(Util.fluxLogger(AIRLINE));
    }
}
