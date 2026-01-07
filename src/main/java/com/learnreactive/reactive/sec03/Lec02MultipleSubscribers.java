package com.learnreactive.reactive.sec03;

import com.learnreactive.reactive.common.Util;
import reactor.core.publisher.Flux;

/*
 * Copyright (c) 2025 Ramjee Prasad
 * Licensed under a custom Non-Commercial, Attribution, Share-Alike License.
 * See the LICENSE file in the project root for full license information.
 *
 * Project: reactive
 * Package: com.learnreactive.reactive.sec03
 * Created by: Ashish Kushwaha on 29-10-2025 18:44
 * File: Lec02MultipleSubscribers
 *
 * This source code is intended for educational and non-commercial purposes only.
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *   - Attribution must be given to the original author.
 *   - The code must be shared under the same license.
 *   - Commercial use is strictly prohibited.
 *
 */
public class Lec02MultipleSubscribers {


    static void main() {
        Flux<Integer> flux = Flux.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        flux.subscribe(Util.subscriber("Ashish"));
        flux.subscribe(Util.subscriber("Ramjee"));
        flux
                .filter(i -> i % 2 == 0)
                .map(i -> i + 'a')
                .subscribe(Util.subscriber("Prasad"));
    }
}
