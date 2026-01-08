package com.learnreactive.reactive.assignments.assignment08;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/*
 * Copyright (c) 2026 Ramjee Prasad
 * Licensed under a custom Non-Commercial, Attribution, Share-Alike License.
 * See the LICENSE file in the project root for full license information.
 *
 * Project: reactive
 * Package: com.learnreactive.reactive.assignments.assignment08
 * Created by: Ashish Kushwaha on 08-01-2026 18:55
 * File: RevenueService
 *
 * This source code is intended for educational and non-commercial purposes only.
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *   - Attribution must be given to the original author.
 *   - The code must be shared under the same license.
 *   - Commercial use is strictly prohibited.
 *
 */
public class RevenueService implements OrderProcessor{

    private final Map<String, Integer> revenueMap = new HashMap<>();

    @Override
    public void consume(Order order) {
        var currentRevenue = revenueMap.getOrDefault(order.category(), 0);
        var updatedRevenue = currentRevenue + order.price();
        revenueMap.put(order.category(), updatedRevenue);
    }

    @Override
    public Flux<String> stream() {
        return Flux.interval(Duration.ofSeconds(2))
                .map(i -> this.revenueMap.toString());
    }
}
