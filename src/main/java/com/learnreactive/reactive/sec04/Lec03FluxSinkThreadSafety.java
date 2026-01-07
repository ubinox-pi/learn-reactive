package com.learnreactive.reactive.sec04;

import com.learnreactive.reactive.common.Util;
import com.learnreactive.reactive.sec04.helper.NameGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.ArrayList;

/*
 * Copyright (c) 2025 Ramjee Prasad
 * Licensed under a custom Non-Commercial, Attribution, Share-Alike License.
 * See the LICENSE file in the project root for full license information.
 *
 * Project: reactive
 * Package: com.learnreactive.reactive.sec04
 * Created by: Ashish Kushwaha on 01-11-2025 10:52
 * File: Lec03FluxSinkThreadSafety
 *
 * This source code is intended for educational and non-commercial purposes only.
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *   - Attribution must be given to the original author.
 *   - The code must be shared under the same license.
 *   - Commercial use is strictly prohibited.
 *
 */
public class Lec03FluxSinkThreadSafety {

    private static final Logger log = LoggerFactory.getLogger(Lec03FluxSinkThreadSafety.class);

    static void main() {
        demo2();
    }

    private static void demo2() {
        var list = new ArrayList<String>();
        var generator = new NameGenerator();
        var flux = Flux.create(generator);
        flux.subscribe(list::add);

        Runnable r = () -> {
            for (int i = 0; i < 1000; i++) {
                generator.generateNames();
            }
        };

        for (int i = 0; i < 10; i++) {
            Thread.ofPlatform().start(r);
        }

        Util.sleep(3);
        log.info("List size : {}", list.size());
    }

    private static void demo1() {
        var list = new ArrayList<Integer>();
        Runnable r = () -> {
            for (int i = 0; i < 1000; i++) {
                list.add(i);
            }
        };

        for (int i = 0; i < 10; i++) {
            Thread.ofPlatform().start(r);
        }

        Util.sleep(3);
        log.info("List size: {}", list.size());

    }
}
