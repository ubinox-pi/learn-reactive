package com.learnreactive.reactive.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

/*
 * Copyright (c) 2026 Ramjee Prasad
 * Licensed under a custom Non-Commercial, Attribution, Share-Alike License.
 * See the LICENSE file in the project root for full license information.
 *
 * Project: reactive
 * Package: com.learnreactive.reactive.test
 * Created by: Ashish Kushwaha on 31-01-2026 16:50
 * File: Lec02EmptyErrorTest
 *
 * This source code is intended for educational and non-commercial purposes only.
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *   - Attribution must be given to the original author.
 *   - The code must be shared under the same license.
 *   - Commercial use is strictly prohibited.
 *
 */
public class Lec02EmptyErrorTest {

    private static Mono<String> getUsername(int userId) {
        return switch (userId) {
            case 1 -> Mono.just("Ashish");
            case 2 -> Mono.just("Ramjee");
            case 3 -> Mono.empty();
            default -> Mono.error(new RuntimeException("User not found"));
        };
    }

    @Test
    public void userTest() {
        StepVerifier.create(getUsername(1))
                .expectNext("Ashish")
                .expectComplete()
                .verify();
    }

    @Test
    public void emptyTest() {
        StepVerifier.create(getUsername(3))
                .expectComplete()
                .verify();
    }

    @Test
    public void errorTest1() {
        StepVerifier.create(getUsername(4))
                .expectError()
                .verify();
    }

    @Test
    public void errorTest2() {
        StepVerifier.create(getUsername(4))
                .expectError(RuntimeException.class) // we can specify the type of error
                .verify();
    }

    @Test
    public void errorTest3() {
        StepVerifier.create(getUsername(4))
                .expectErrorMessage("User not found")
                .verify();
    }

    @Test
    public void errorTest4() {
        StepVerifier.create(getUsername(4))
                .consumeErrorWith(ex -> {
                    Assertions.assertEquals(RuntimeException.class, ex.getClass());
                    Assertions.assertEquals("User not found", ex.getMessage());
                })
                .verify();
    }
}
