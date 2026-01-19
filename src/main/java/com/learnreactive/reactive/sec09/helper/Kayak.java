package com.learnreactive.reactive.sec09.helper;

import reactor.core.publisher.Flux;

import java.time.Duration;

/*
 * Copyright (c) 2026 Ramjee Prasad
 * Licensed under a custom Non-Commercial, Attribution, Share-Alike License.
 * See the LICENSE file in the project root for full license information.
 *
 * Project: reactive
 * Package: com.learnreactive.reactive.sec09.helper
 * Created by: Ashish Kushwaha on 13-01-2026 19:59
 * File: Kayak
 *
 * This source code is intended for educational and non-commercial purposes only.
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *   - Attribution must be given to the original author.
 *   - The code must be shared under the same license.
 *   - Commercial use is strictly prohibited.
 *
 */
public class Kayak {
    public static Flux<Flight> getFlights() {
        return Flux.merge(
                AmericanAirlines.getAirline(),
                Qatar.getAirline(),
                Emirates.getAirline()
        ).take(Duration.ofMillis(2000));
    }
}
