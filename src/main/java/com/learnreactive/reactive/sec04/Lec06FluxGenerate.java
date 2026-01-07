package com.learnreactive.reactive.sec04;

import com.learnreactive.reactive.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

/*
 * Copyright (c) 2025 Ramjee Prasad
 * Licensed under a custom Non-Commercial, Attribution, Share-Alike License.
 * See the LICENSE file in the project root for full license information.
 *
 * Project: reactive
 * Package: com.learnreactive.reactive.sec04
 * Created by: Ashish Kushwaha on 12-11-2025 22:10
 * File: Lec06FluxGenerate
 *
 * This source code is intended for educational and non-commercial purposes only.
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *   - Attribution must be given to the original author.
 *   - The code must be shared under the same license.
 *   - Commercial use is strictly prohibited.
 *
 */
public class Lec06FluxGenerate {

    /*
     * invokes the given lambda expression again based on downstream demand.
     * we can only emit one value at a time
     * will stop when complete method is invoked.
     * will stop when error method is invoked.
     * will stop downstream cancels
     */

    private static final Logger log = LoggerFactory.getLogger(Lec06FluxGenerate.class);

    static void main() {
        Flux.generate(synchronousSink -> {
                    log.info("invoked");
                    synchronousSink.next(1);
                    //synchronousSink.next(2);  //Can't emit more than one item
                    //synchronousSink.complete();
                })
                .take(5)
                .subscribe(Util.subscriber("Ashish"));
    }
}
