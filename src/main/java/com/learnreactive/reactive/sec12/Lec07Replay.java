package com.learnreactive.reactive.sec12;

import com.learnreactive.reactive.common.Util;
import reactor.core.publisher.Sinks;

/*
 * Copyright (c) 2026 Ramjee Prasad
 * Licensed under a custom Non-Commercial, Attribution, Share-Alike License.
 * See the LICENSE file in the project root for full license information.
 *
 * Project: reactive
 * Package: com.learnreactive.reactive.sec12
 * Created by: Ashish Kushwaha on 28-01-2026 22:22
 * File: Lec07Replay
 *
 * This source code is intended for educational and non-commercial purposes only.
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *   - Attribution must be given to the original author.
 *   - The code must be shared under the same license.
 *   - Commercial use is strictly prohibited.
 *
 */
public class Lec07Replay {

    static void main() {
        demo2();
    }

    public static void demo1() {

        // handle through which we would push items
        var sink = Sinks.many().replay().all();

        // handle through which subscribers will receive items.
        var flux = sink.asFlux();

        flux.subscribe(Util.subscriber("Ashish"));
        flux.subscribe(Util.subscriber("Kumar"));

        sink.tryEmitNext("Hello");
        sink.tryEmitNext("Hii");
        sink.tryEmitNext("Hola");
        sink.tryEmitNext("Hey");

        Util.sleep(2);

        flux.subscribe(Util.subscriber("Ramjee"));
        sink.tryEmitNext("new message");
    }

    public static void demo2() {

        // handle through which we would push items
        var sink = Sinks.many().replay().limit(2);

        // handle through which subscribers will receive items.
        var flux = sink.asFlux();

        flux.subscribe(Util.subscriber("Ashish"));
        flux.subscribe(Util.subscriber("Kumar"));

        sink.tryEmitNext("Hello");
        sink.tryEmitNext("Hii");
        sink.tryEmitNext("Hola");
        sink.tryEmitNext("Hey");

        Util.sleep(2);

        flux.subscribe(Util.subscriber("Ramjee"));
        sink.tryEmitNext("new message");
    }
}
