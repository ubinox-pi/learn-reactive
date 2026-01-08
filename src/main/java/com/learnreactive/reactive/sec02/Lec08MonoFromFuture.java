package com.learnreactive.reactive.sec02;

import com.learnreactive.reactive.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

/*
 * Copyright (c) 2025 Ramjee Prasad
 * Licensed under a custom Non-Commercial, Attribution, Share-Alike License.
 * See the LICENSE file in the project root for full license information.
 *
 * Project: reactive
 * Package: com.learnreactive.reactive.sec2
 * Created by: Ashish Kushwaha on 22-10-2025 11:23
 * File: Lec08MonoFromFuture
 *
 * This source code is intended for educational and non-commercial purposes only.
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *   - Attribution must be given to the original author.
 *   - The code must be shared under the same license.
 *   - Commercial use is strictly prohibited.
 *
 */
public class Lec08MonoFromFuture {

    private static final Logger log = LoggerFactory.getLogger(Lec08MonoFromFuture.class);

    static void main() throws InterruptedException {
        Mono.fromFuture(() -> {
            try {
                return getName();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        //.subscribe(Util.subscriber("Ramjee"));
        Util.sleep(2);
    }

    private static CompletableFuture<String> getName() throws InterruptedException {
        return CompletableFuture.supplyAsync(() -> {
            log.info("Generating name");
            return Util.getFaker().name().fullName();
        });
    }
}
