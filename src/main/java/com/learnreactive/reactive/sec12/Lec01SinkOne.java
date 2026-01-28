package com.learnreactive.reactive.sec12;

import com.learnreactive.reactive.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

/*
 * Copyright (c) 2026 Ramjee Prasad
 * Licensed under a custom Non-Commercial, Attribution, Share-Alike License.
 * See the LICENSE file in the project root for full license information.
 *
 * Project: reactive
 * Package: com.learnreactive.reactive.sec12
 * Created by: Ashish Kushwaha on 25-01-2026 19:32
 * File: Lec01SinkOne
 *
 * This source code is intended for educational and non-commercial purposes only.
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *   - Attribution must be given to the original author.
 *   - The code must be shared under the same license.
 *   - Commercial use is strictly prohibited.
 *
 */
public class Lec01SinkOne {

    private static final Logger log = LoggerFactory.getLogger(Lec01SinkOne.class);

    static void main() {
        demo5();
    }

    private static void demo1() {
        Sinks.One<String> sink = Sinks.one(); // creates one sink.

        Mono<String> mono = sink.asMono();

        mono.subscribe(Util.subscriber("Ashish"));

        sink.tryEmitValue("Hello World!"); // it will try to send if fails not output.
    }

    private static void demo2() {
        Sinks.One<String> sink = Sinks.one(); // creates one sink.

        Mono<String> mono = sink.asMono();

        mono.subscribe(Util.subscriber("Ashish"));

        sink.tryEmitEmpty();
    }

    private static void demo3() {
        Sinks.One<String> sink = Sinks.one(); // creates one sink.

        Mono<String> mono = sink.asMono();

        mono.subscribe(Util.subscriber("Ashish"));

        sink.tryEmitError(new RuntimeException("Error"));
    }

    private static void demo4() {
        Sinks.One<String> sink = Sinks.one(); // creates one sink.

        Mono<String> mono = sink.asMono();

        mono.subscribe(Util.subscriber("Ashish"));
        mono.subscribe(Util.subscriber("Ramjee"));
        mono.subscribe(Util.subscriber("Kumar"));

        sink.tryEmitValue("Hello World!");
    }

    private static void demo5() {
        Sinks.One<String> sink = Sinks.one(); // creates one sink.

        Mono<String> mono = sink.asMono();

        mono.subscribe(Util.subscriber("Ashish"));

        sink.emitValue("Hello World!", (signalType, emitResult) -> {
            log.info("1 -> Signal type: {}, Emit result: {}", signalType, emitResult);
            return false;
        });
        sink.emitValue("Hello computer!", (signalType, emitResult) -> {
            log.info("2 -> Signal type: {}, Emit result: {}", signalType, emitResult);
            return false; // flag is for retry.
        });

//        19:51:17.675 INFO  [           main] c.l.r.common.DefaultSubscriber : Ashish Received: Hello World!
//        19:51:17.679 INFO  [           main] c.l.r.common.DefaultSubscriber : Ashish Completed!
//        19:51:17.680 INFO  [           main] c.l.r.sec12.Lec01SinkOne       : 2 -> Signal type: onNext, Emit result: FAIL_TERMINATED
    }
}
