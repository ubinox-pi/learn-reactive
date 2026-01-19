package com.learnreactive.reactive.sec09;

/*
 * Copyright (c) 2026 Ramjee Prasad
 * Licensed under a custom Non-Commercial, Attribution, Share-Alike License.
 * See the LICENSE file in the project root for full license information.
 *
 * Project: reactive
 * Package: com.learnreactive.reactive.sec09
 * Created by: Ashish Kushwaha on 19-01-2026 18:28
 * File: Lec15Then
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
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

/*
 * "then" could be helpful when we are not interested in the result of publisher  /
 * we need to have sequential execution of asynchronous tasks.
 * */
public class Lec15Then {
    private static final Logger log = LoggerFactory.getLogger(Lec15Then.class);

    static void main() {
        saveRecords(List.of("A", "B", "C"))
                .then(sendNotification(List.of("A", "B", "C")))
                .subscribe(Util.subscriber("sub1"));

        Util.sleep(3);
    }

    private static Flux<String> saveRecords(List<String> records) {
        return Flux.fromIterable(records)
                .map(r -> "saved" + r)
                .delayElements(Duration.ofMillis(500));
    }

    private static Mono<Void> sendNotification(List<String> records) {
        return Mono.fromRunnable(() -> log.info("Notification sent for records {}", records));
    }
}
