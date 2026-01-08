package com.learnreactive.reactive.sec04;

import com.learnreactive.reactive.common.Util;
import com.learnreactive.reactive.sec01.subscriber.SubscriberImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;


/*
 * Copyright (c) 2025 Ramjee Prasad
 * Licensed under a custom Non-Commercial, Attribution, Share-Alike License.
 * See the LICENSE file in the project root for full license information.
 *
 * Project: reactive
 * Package: com.learnreactive.reactive.sec04
 * Created by: Ashish Kushwaha on 01-11-2025 11:08
 * File: Lec04FluxCreateDownstreamdemand
 *
 * This source code is intended for educational and non-commercial purposes only.
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *   - Attribution must be given to the original author.
 *   - The code must be shared under the same license.
 *   - Commercial use is strictly prohibited.
 *
 */
public class Lec04FluxCreateDownstreamDemand {

    /*
     * it is designed to be used when we have a single subscriber.
     * FluxSink is thread safe. We can share it with multiple threads.
     * We can keep on emitting data into the sink w/o worrying about downstream demand.
     * FluxSink will deliver everything to subscriber safely.
     */

    private static final Logger log = LoggerFactory.getLogger(Lec04FluxCreateDownstreamDemand.class);

    static void main() {
        var subscriber = new SubscriberImpl();
        Flux.<String>create(fluxSink -> {
            for (int i = 1; i <= 10; i++) {
                var name = Util.getFaker().name().fullName();
                log.info("Generated Name: {}", name);
                fluxSink.next(name);
            }
            fluxSink.complete();
        }).subscribe(subscriber);

        Util.sleep(2);
        subscriber.getSubscription().request(3);
        Util.sleep(2);
        subscriber.getSubscription().request(5);
        Util.sleep(2);
        subscriber.getSubscription().cancel();
    }
}
