package com.learnreactive.reactive.sec04;

import com.learnreactive.reactive.common.Util;
import reactor.core.publisher.Flux;

/*
 * Copyright (c) 2025 Ramjee Prasad
 * Licensed under a custom Non-Commercial, Attribution, Share-Alike License.
 * See the LICENSE file in the project root for full license information.
 *
 * Project: reactive
 * Package: com.learnreactive.reactive.sec04
 * Created by: Ashish Kushwaha on 01-11-2025 10:35
 * File: Lec01FluxCreate
 *
 * This source code is intended for educational and non-commercial purposes only.
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *   - Attribution must be given to the original author.
 *   - The code must be shared under the same license.
 *   - Commercial use is strictly prohibited.
 *
 */
public class Lec01FluxCreate {

    static void main() {
        Flux.create(fluxSink -> {
                    while (true) {
                        String country = Util.getFaker().country().name();
                        fluxSink.next(country);
                        if (country.equalsIgnoreCase("INDIA")) {
                            fluxSink.complete();
                            break;
                        }
                    }
                    fluxSink.complete();
                })
                .subscribe(Util.subscriber("ashish"));
    }
}
