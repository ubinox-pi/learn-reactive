package com.learnreactive.reactive.test;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import reactor.test.StepVerifierOptions;
import reactor.util.context.Context;

/*
 * Copyright (c) 2026 Ramjee Prasad
 * Licensed under a custom Non-Commercial, Attribution, Share-Alike License.
 * See the LICENSE file in the project root for full license information.
 *
 * Project: reactive
 * Package: com.learnreactive.reactive.test
 * Created by: Ashish Kushwaha on 31-01-2026 18:20
 * File: Lec08ContextTest
 *
 * This source code is intended for educational and non-commercial purposes only.
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *   - Attribution must be given to the original author.
 *   - The code must be shared under the same license.
 *   - Commercial use is strictly prohibited.
 *
 */
public class Lec08ContextTest {

    public static Mono<String> getWelcomeMessage() {
        return Mono.deferContextual(context -> {
            if (context.hasKey("user")) {
                return Mono.just("Welcome %s to the Reactive World!".formatted(context.get("user").toString()));
            }
            return Mono.error(new RuntimeException("unauthenticated"));
        });
    }

    @Test
    public void welcomeMessageTest() {
        StepVerifierOptions options = StepVerifierOptions.create().withInitialContext(Context.of("user", "Ramjee"));
        StepVerifier.create(getWelcomeMessage(), options)
                .expectNext("Welcome Ramjee to the Reactive World!")
                .expectComplete()
                .verify();
    }

    @Test
    public void unauthenticatedTest() {
        StepVerifier.create(getWelcomeMessage())
                .expectErrorMessage("unauthenticated")
                .verify();
    }
}
