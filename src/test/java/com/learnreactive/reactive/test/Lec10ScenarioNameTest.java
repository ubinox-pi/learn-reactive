package com.learnreactive.reactive.test;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.StepVerifierOptions;

/*
 * Copyright (c) 2026 Ramjee Prasad
 * Licensed under a custom Non-Commercial, Attribution, Share-Alike License.
 * See the LICENSE file in the project root for full license information.
 *
 * Project: reactive
 * Package: com.learnreactive.reactive.test
 * Created by: Ashish Kushwaha on 31-01-2026 17:44
 * File: Lec10ScenarioNameTest
 *
 * This source code is intended for educational and non-commercial purposes only.
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *   - Attribution must be given to the original author.
 *   - The code must be shared under the same license.
 *   - Commercial use is strictly prohibited.
 *
 */

// these tests are intended to fail
public class Lec10ScenarioNameTest {

    private Flux<Integer> getItems() {
        return Flux.range(1, 3);
    }

    @Test
    public void scenarioNameTest1() {
        StepVerifierOptions options = StepVerifierOptions
                .create()
                .scenarioName(" 1 to 3 item test");


//        java.lang.AssertionError: [ 1 to 3 item test] expectation "expectComplete" failed (expected: onComplete(); actual: onNext(3))

        StepVerifier.create(getItems(), options)
                .expectNext(1, 2)
                .expectComplete()
                .verify();
    }

    @Test
    public void scenarioNameTest2() {
        StepVerifierOptions options = StepVerifierOptions
                .create()
                .scenarioName(" 1 to 3 item test");

        StepVerifier.create(getItems(), options)
                .expectNext(11)
                .as("first item should be 11")
                .expectNext(2, 3)
                .as("then 2 and 3")
                .expectComplete()
                .verify();
    }
}
