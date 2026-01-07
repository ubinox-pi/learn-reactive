package com.learnreactive.reactive.sec03.helper;

import com.learnreactive.reactive.common.Util;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.IntStream;

/*
 * Copyright (c) 2025 Ramjee Prasad
 * Licensed under a custom Non-Commercial, Attribution, Share-Alike License.
 * See the LICENSE file in the project root for full license information.
 *
 * Project: reactive
 * Package: com.learnreactive.reactive.sec03.helper
 * Created by: Ashish Kushwaha on 30-10-2025 10:00
 * File: NameGenerator
 *
 * This source code is intended for educational and non-commercial purposes only.
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *   - Attribution must be given to the original author.
 *   - The code must be shared under the same license.
 *   - Commercial use is strictly prohibited.
 *
 */
public class NameGenerator {

    public static List<String> getNamesList(int count) {
        return IntStream.range(0, count)
                .mapToObj(o -> generateNames())
                .toList();
    }

    private static String generateNames() {
        Util.sleep(1);
        return Util.getFaker().name().firstName();
    }

    public static Flux<String> getNamesFlux(int count) {
        return Flux.range(0, count)
                .map(o -> generateNames());
    }
}
