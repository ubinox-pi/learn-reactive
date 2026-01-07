package com.learnreactive.reactive.sec03;

import com.learnreactive.reactive.common.Util;
import reactor.core.publisher.Flux;

import java.util.List;

/*
 * Copyright (c) 2025 Ramjee Prasad
 * Licensed under a custom Non-Commercial, Attribution, Share-Alike License.
 * See the LICENSE file in the project root for full license information.
 *
 * Project: reactive
 * Package: com.learnreactive.reactive.sec03
 * Created by: Ashish Kushwaha on 30-10-2025 09:36
 * File: Lec03FluxFromIterableOrArray
 *
 * This source code is intended for educational and non-commercial purposes only.
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *   - Attribution must be given to the original author.
 *   - The code must be shared under the same license.
 *   - Commercial use is strictly prohibited.
 *
 */
public class Lec03FluxFromIterableOrArray {

    static void main() {
        var list = List.of('a', 'b', 'c', 'd', 'e', 'f');
        Flux.fromIterable(list)
                .subscribe(Util.subscriber("Ashish"));

        Integer[] arr = {1, 2, 3, 4, 5, 6};
        Flux.fromArray(arr)
                .subscribe(Util.subscriber("Ashish"));
    }
}
