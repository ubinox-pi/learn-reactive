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
 * Created by: Ashish Kushwaha on 30-10-2025 09:44
 * File: Lrc05FluxRange
 *
 * This source code is intended for educational and non-commercial purposes only.
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *   - Attribution must be given to the original author.
 *   - The code must be shared under the same license.
 *   - Commercial use is strictly prohibited.
 *
 */
public class Lec05FluxRange {

    static void main() {
        Flux.range(1, 10)
                .subscribe(Util.subscriber("Ashish"));
    }
}
