package com.learnreactive.reactive.sec2;

import com.learnreactive.reactive.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.List;

/*
 * Copyright (c) 2025 Ramjee Prasad
 * Licensed under a custom Non-Commercial, Attribution, Share-Alike License.
 * See the LICENSE file in the project root for full license information.
 *
 * Project: reactive
 * Package: com.learnreactive.reactive.sec2
 * Created by: Ashish Kushwaha on 22-10-2025 10:59
 * File: Lec05MonoFromSupplier
 *
 * This source code is intended for educational and non-commercial purposes only.
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *   - Attribution must be given to the original author.
 *   - The code must be shared under the same license.
 *   - Commercial use is strictly prohibited.
 *
 */
public class Lec05MonoFromCallable {

    private static final Logger log = LoggerFactory.getLogger(Lec05MonoFromCallable.class);

    static void main() {
        var list = List.of(1, 2, 3, 4, 5, 6, 6, 7, 8, 9, 10);
        Mono.fromSupplier(() -> {
                    try {
                        return sum(list);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .subscribe(Util.subscriber("Ramjee"));

        Mono.fromCallable(() -> sum(list))
                .subscribe(Util.subscriber("Ashish"));
    }

    private static int sum(List<Integer> list) throws Exception {
        log.info("finding the sum of the list {}", list);
        return list.stream().mapToInt(i -> i).sum();
    }
}
