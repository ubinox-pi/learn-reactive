package com.learnreactive.reactive.sec04;

import com.learnreactive.reactive.common.Util;
import com.learnreactive.reactive.sec04.helper.NameGenerator;
import reactor.core.publisher.Flux;

/*
 * Copyright (c) 2025 Ramjee Prasad
 * Licensed under a custom Non-Commercial, Attribution, Share-Alike License.
 * See the LICENSE file in the project root for full license information.
 *
 * Project: reactive
 * Package: com.learnreactive.reactive.sec04
 * Created by: Ashish Kushwaha on 01-11-2025 10:49
 * File: Lec02FluxCreateRefactor
 *
 * This source code is intended for educational and non-commercial purposes only.
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *   - Attribution must be given to the original author.
 *   - The code must be shared under the same license.
 *   - Commercial use is strictly prohibited.
 *
 */
public class Lec02FluxCreateRefactor {

    static void main() {
        var generator = new NameGenerator();
        var flux = Flux.create(generator);
        flux.subscribe(Util.subscriber("ashish"));

        for (int i = 0; i < 10; i++) {
            generator.generateNames();
            Util.sleep(1);
        }
    }
}
