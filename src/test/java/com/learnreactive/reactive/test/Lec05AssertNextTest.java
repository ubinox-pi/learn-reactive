package com.learnreactive.reactive.test;

/*
 * Copyright (c) 2026 Ramjee Prasad
 * Licensed under a custom Non-Commercial, Attribution, Share-Alike License.
 * See the LICENSE file in the project root for full license information.
 *
 * Project: reactive
 * Package: com.learnreactive.reactive.test
 * Created by: Ashish Kushwaha on 31-01-2026 17:24
 * File: Lec05AssertNextTest
 *
 * This source code is intended for educational and non-commercial purposes only.
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *   - Attribution must be given to the original author.
 *   - The code must be shared under the same license.
 *   - Commercial use is strictly prohibited.
 *
 */

import com.learnreactive.reactive.common.Util;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.Objects;

/*
 * "assertNext" is the method in StepVerifier
 * assertNext = ConsumeNextWith
 * We can also collect all the items and test
 * */
public class Lec05AssertNextTest {
    private Flux<Book> getBooks() {
        return Flux.range(1, 3)
                .map(i -> new Book(i, Util.getFaker().book().author(), Util.getFaker().book().title()));
    }

    @Test
    public void assertNextTest1() {
        StepVerifier.create(getBooks())
                .assertNext(book -> Assertions.assertEquals(1, book.id))
                .thenConsumeWhile(Objects::nonNull)
                .expectComplete()
                .verify();

    }

    @Test
    public void assertNextTest2() {
        StepVerifier.create(getBooks().collectList())
                .assertNext(list -> Assertions.assertEquals(3, list.size()))
                .expectComplete()
                .verify();

    }

    record Book(
            Integer id,
            String author,
            String title
    ) {
    }
}
