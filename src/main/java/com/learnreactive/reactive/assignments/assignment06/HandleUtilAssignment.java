package com.learnreactive.reactive.assignments.assignment06;

import com.learnreactive.reactive.common.Util;
import reactor.core.publisher.Flux;

/*
 * Copyright (c) 2025 Ramjee Prasad
 * Licensed under a custom Non-Commercial, Attribution, Share-Alike License.
 * See the LICENSE file in the project root for full license information.
 *
 * Project: reactive
 * Package: com.learnreactive.reactive.assignments.assignment06
 * Created by: Ashish Kushwaha on 16-11-2025 18:32
 * File: HandleUtilAssignment
 *
 * This source code is intended for educational and non-commercial purposes only.
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *   - Attribution must be given to the original author.
 *   - The code must be shared under the same license.
 *   - Commercial use is strictly prohibited.
 *
 */
public class HandleUtilAssignment {
    static void main() {
        Flux.generate(synchronousSink -> synchronousSink.next(Util.getFaker().country().name()))
                .handle((item, synchronousSink) -> {
                    synchronousSink.next(item);
                    if (item.equals("India")) {
                        synchronousSink.complete();
                    }
                })
                .cast(String.class)
                .subscribe(Util.subscriber("Ashish"));
    }
}
