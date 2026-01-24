package com.learnreactive.reactive.sec10;

import com.learnreactive.reactive.common.Util;
import com.learnreactive.reactive.sec10.buffer.BookOrder;
import com.learnreactive.reactive.sec10.buffer.RevenueReport;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/*
 * Copyright (c) 2026 Ramjee Prasad
 * Licensed under a custom Non-Commercial, Attribution, Share-Alike License.
 * See the LICENSE file in the project root for full license information.
 *
 * Project: reactive
 * Package: com.learnreactive.reactive.sec10
 * Created by: Ashish Kushwaha on 23-01-2026 22:18
 * File: Lec02BufferAssignment
 *
 * This source code is intended for educational and non-commercial purposes only.
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *   - Attribution must be given to the original author.
 *   - The code must be shared under the same license.
 *   - Commercial use is strictly prohibited.
 *
 */
public class Lec02BufferAssignment {

    static Set<String> allowedCategories = Set.of(
            "Science fiction",
            "Fantasy",
            "Suspense/Thriller"
    );

    static void main() {
        orderStream()
                .filter(o -> allowedCategories.contains(o.genre()))
                .buffer(Duration.ofSeconds(5))
                .map(Lec02BufferAssignment::generateReport)
                .subscribe(Util.subscriber("Ashish"));

        Util.sleep(60);
    }

    private static Flux<BookOrder> orderStream() {
        return Flux.interval(Duration.ofMillis(200))
                .map(_ -> BookOrder.create());
    }

    private static RevenueReport generateReport(List<BookOrder> orders) {
        var revenue = orders.stream()
                .collect(Collectors.groupingBy(
                        BookOrder::genre,
                        Collectors.summingInt(BookOrder::price)
                ));

        return new RevenueReport(LocalTime.now(), revenue);
    }
}
