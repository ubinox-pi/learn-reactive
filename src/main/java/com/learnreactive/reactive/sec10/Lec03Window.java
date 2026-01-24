package com.learnreactive.reactive.sec10;

import com.learnreactive.reactive.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

/*
 * Copyright (c) 2026 Ramjee Prasad
 * Licensed under a custom Non-Commercial, Attribution, Share-Alike License.
 * See the LICENSE file in the project root for full license information.
 *
 * Project: reactive
 * Package: com.learnreactive.reactive.sec10
 * Created by: Ashish Kushwaha on 23-01-2026 22:42
 * File: Lec03Window
 *
 * This source code is intended for educational and non-commercial purposes only.
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *   - Attribution must be given to the original author.
 *   - The code must be shared under the same license.
 *   - Commercial use is strictly prohibited.
 *
 */
public class Lec03Window {

    static void main() {
        eventStream()
                .window(5)
                .flatMap(Lec03Window::processEvents)
                .subscribe();

        Util.sleep(60);
    }

    private static Flux<String> eventStream() {
        return Flux.interval(Duration.ofMillis(500))
                .map(i -> "Event-" + (i + 1));
    }

    private static Mono<Void> processEvents(Flux<String> flux) {
        return flux
                .doOnNext(e -> System.out.print("*"))
                .doOnComplete(System.out::println)
                .then();
    }
}
