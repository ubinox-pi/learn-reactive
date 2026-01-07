package com.learnreactive.reactive.assignments.assignment02;

import com.learnreactive.reactive.common.Util;
import reactor.core.publisher.Flux;

/*
 * Copyright (c) 2025 Ramjee Prasad
 * Licensed under a custom Non-Commercial, Attribution, Share-Alike License.
 * See the LICENSE file in the project root for full license information.
 *
 * Project: reactive
 * Package: com.learnreactive.reactive.assignments.assignment02
 * Created by: Ashish Kushwaha on 30-10-2025 09:46
 * File: GenerateTenFakeNamesUsingFluxRange
 *
 * This source code is intended for educational and non-commercial purposes only.
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *   - Attribution must be given to the original author.
 *   - The code must be shared under the same license.
 *   - Commercial use is strictly prohibited.
 *
 */
public class GenerateTenFakeNamesUsingFluxRange {
    static void main() {
        Flux.range(1, 10)
                .map(i -> "Name-" + Util.getFaker().name().fullName())
                .subscribe(Util.subscriber("Ashish"));
    }
}
