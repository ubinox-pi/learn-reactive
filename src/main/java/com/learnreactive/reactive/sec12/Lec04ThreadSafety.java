package com.learnreactive.reactive.sec12;

import com.learnreactive.reactive.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Sinks;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

/*
 * Copyright (c) 2026 Ramjee Prasad
 * Licensed under a custom Non-Commercial, Attribution, Share-Alike License.
 * See the LICENSE file in the project root for full license information.
 *
 * Project: reactive
 * Package: com.learnreactive.reactive.sec12
 * Created by: Ashish Kushwaha on 25-01-2026 20:11
 * File: Lec04ThreadSafety
 *
 * This source code is intended for educational and non-commercial purposes only.
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *   - Attribution must be given to the original author.
 *   - The code must be shared under the same license.
 *   - Commercial use is strictly prohibited.
 *
 */
public class Lec04ThreadSafety {
    private static final Logger log = LoggerFactory.getLogger(Lec04ThreadSafety.class);

    static void main() {
        demo2();
    }

    public static void demo1() {

        var sink = Sinks.many().unicast().onBackpressureBuffer();

        var flux = sink.asFlux();

        var list = new ArrayList<>();
        flux.subscribe(list::add);

        for (int i = 0; i < 1000; i++) {
            var j = i;
            CompletableFuture.runAsync(() -> {
                sink.tryEmitNext(j);
            });
        }
        Util.sleep(3);
        log.info("List size: {}", list.size());
    }

    public static void demo2() {
        var sink = Sinks.many().unicast().onBackpressureBuffer();

        var flux = sink.asFlux();

        var list = new ArrayList<>();
        flux.subscribe(list::add);

        for (int i = 0; i < 1000; i++) {
            var j = i;
            CompletableFuture.runAsync(() -> {
                sink.emitNext(j, (signal, emitResult) -> {
                    return Sinks.EmitResult.FAIL_NON_SERIALIZED.equals(emitResult);
                });
            });
        }
        Util.sleep(3);
        log.info("List size: {}", list.size());
    }
}
