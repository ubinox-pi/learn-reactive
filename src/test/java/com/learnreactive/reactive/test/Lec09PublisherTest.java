package com.learnreactive.reactive.test;

import com.learnreactive.reactive.common.Util;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.publisher.TestPublisher;

import java.util.function.UnaryOperator;

/*
 * Copyright (c) 2026 Ramjee Prasad
 * Licensed under a custom Non-Commercial, Attribution, Share-Alike License.
 * See the LICENSE file in the project root for full license information.
 *
 * Project: reactive
 * Package: com.learnreactive.reactive.test
 * Created by: Ashish Kushwaha on 31-01-2026 18:30
 * File: Lec09PublisherTest
 *
 * This source code is intended for educational and non-commercial purposes only.
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *   - Attribution must be given to the original author.
 *   - The code must be shared under the same license.
 *   - Commercial use is strictly prohibited.
 *
 */
public class Lec09PublisherTest {

    private UnaryOperator<Flux<String>> processor() {
        return flux -> flux
                .filter(s -> s.length() > 1)
                .map(String::toUpperCase)
                .map(s -> s + ":" + s.length());
    }

    //    @Test
    public void publisherTestDemo() {
        var publisher = TestPublisher.<String>create();
        var flux = publisher.flux();

        flux.subscribe(Util.subscriber("Ramjee"));

//        publisher.next("Ramjee", "Ashish");
//        publisher.complete();

        publisher.emit("Ramjee", "A", "Ashish", "B", "C", "John"); // this is same as above but this automatically sends complete signal.
    }

    @Test
    public void publisherTest() {
        var publisher = TestPublisher.<String>create();
        var flux = publisher.flux();


        StepVerifier.create(flux.transform(processor()))
                .then(() -> publisher.emit("Ramjee", "A", "Ashish", "B", "C", "John"))
                .expectNext("RAMJEE:6", "ASHISH:6", "JOHN:4")
                .expectComplete()
                .verify();

    }

    @Test
    public void publisherTest2() {
        var publisher = TestPublisher.<String>create();
        var flux = publisher.flux();


        StepVerifier.create(flux.transform(processor()))
                .then(() -> publisher.emit("a", "b", "c"))
                .expectComplete()
                .verify();

    }
}
