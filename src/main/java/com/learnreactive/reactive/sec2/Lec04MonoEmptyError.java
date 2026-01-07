package com.learnreactive.reactive.sec2;

import com.learnreactive.reactive.common.Util;
import reactor.core.publisher.Mono;

/*
 * Copyright (c) 2025 Ramjee Prasad
 * Licensed under a custom Non-Commercial, Attribution, Share-Alike License.
 * See the LICENSE file in the project root for full license information.
 *
 * Project: reactive
 * Package: com.learnreactive.reactive.sec2
 * Created by: Ashish Kushwaha on 22-10-2025 05:22
 * File: Lec04MonoEmptyError
 *
 * This source code is intended for educational and non-commercial purposes only.
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *   - Attribution must be given to the original author.
 *   - The code must be shared under the same license.
 *   - Commercial use is strictly prohibited.
 *
 */
public class Lec04MonoEmptyError {

    static void main() {
        getUsername(1)
                .subscribe(Util.subscriber("ashish"));
        getUsername(2)
                .subscribe(Util.subscriber("ashish"));
        getUsername(3)
                .subscribe(Util.subscriber("ashish"));
        getUsername(4)
                .subscribe(Util.subscriber("ashish"));
        getUsername(2)
                .subscribe(e -> System.out.println(e),
                        err -> System.out.println(err));
    }

    private static Mono<String> getUsername(int userId) {
        return switch (userId) {
            case 1 -> Mono.just("Ashish");
            case 2 -> Mono.just("Ramjee");
            case 3 -> Mono.empty();
            default -> throw new RuntimeException("User not found");
        };
    }
}
