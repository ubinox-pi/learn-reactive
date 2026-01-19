package com.learnreactive.reactive.sec09;

/*
 * Copyright (c) 2026 Ramjee Prasad
 * Licensed under a custom Non-Commercial, Attribution, Share-Alike License.
 * See the LICENSE file in the project root for full license information.
 *
 * Project: reactive
 * Package: com.learnreactive.reactive.sec09
 * Created by: Ashish Kushwaha on 19-01-2026 18:16
 * File: Lec14ColectListOperator
 *
 * This source code is intended for educational and non-commercial purposes only.
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *   - Attribution must be given to the original author.
 *   - The code must be shared under the same license.
 *   - Commercial use is strictly prohibited.
 *
 */

import com.learnreactive.reactive.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/*
 * To collect the items received via Flux. Assuming we will have finite items
 * */
public class Lec14CollectListOperator {
    static void main() {
        Flux.range(1, 10)
                .concatWith(Mono.error(new RuntimeException("Exception occurred")))
                .collectList()
                .subscribe(Util.subscriber("Ashish"));
    }
}
