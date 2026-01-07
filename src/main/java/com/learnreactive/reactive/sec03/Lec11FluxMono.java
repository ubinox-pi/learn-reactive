package com.learnreactive.reactive.sec03;

import com.learnreactive.reactive.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/*
 * Copyright (c) 2025 Ramjee Prasad
 * Licensed under a custom Non-Commercial, Attribution, Share-Alike License.
 * See the LICENSE file in the project root for full license information.
 *
 * Project: reactive
 * Package: com.learnreactive.reactive.sec03
 * Created by: Ashish Kushwaha on 30-10-2025 11:14
 * File: Lec11FluxMono
 *
 * This source code is intended for educational and non-commercial purposes only.
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *   - Attribution must be given to the original author.
 *   - The code must be shared under the same license.
 *   - Commercial use is strictly prohibited.
 *
 */
public class Lec11FluxMono {

    static void main() {

        Mono mono = getUsername(1);
        save(Flux.from(mono));

        var flux = Flux.range(1, 10);
        Mono.from(flux)
                .subscribe(Util.subscriber("Ashish"));

    }

    private static Mono<String> getUsername(int userId) {
        return switch (userId) {
            case 1 -> Mono.just("Ashish");
            case 2 -> Mono.just("Ramjee");
            case 3 -> Mono.empty();
            default -> throw new RuntimeException("User not found");
        };
    }

    private static void save(Flux<String> flux) {
        flux.subscribe(Util.subscriber("Ramjee"));
    }

    private static void save(Mono<String> mono) {
        mono.subscribe(Util.subscriber("Ramjee"));
    }

}
