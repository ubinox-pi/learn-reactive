package com.learnreactive.reactive.sec10;

import com.learnreactive.reactive.common.Util;
import com.learnreactive.reactive.sec10.window.FileWriter;
import reactor.core.publisher.Flux;

import java.nio.file.Path;
import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

/*
 * Copyright (c) 2026 Ramjee Prasad
 * Licensed under a custom Non-Commercial, Attribution, Share-Alike License.
 * See the LICENSE file in the project root for full license information.
 *
 * Project: reactive
 * Package: com.learnreactive.reactive.sec10
 * Created by: Ashish Kushwaha on 24-01-2026 17:36
 * File: Lec04WindowAssignment
 *
 * This source code is intended for educational and non-commercial purposes only.
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *   - Attribution must be given to the original author.
 *   - The code must be shared under the same license.
 *   - Commercial use is strictly prohibited.
 *
 */
public class Lec04WindowAssignment {
    static void main() {
        var counter = new AtomicInteger(0);
        var fileNameFormat = "src\\main\\resources\\sec10\\lec04\\file%d.txt";

        eventStream()
                .window(5)
                .flatMap(flux -> FileWriter.create(flux, Path.of(fileNameFormat.formatted(counter.incrementAndGet()))))
                .subscribe();

        Util.sleep(60);
    }

    private static Flux<String> eventStream() {
        return Flux.interval(Duration.ofMillis(500))
                .map(i -> "Event-" + (i + 1));
    }
}
