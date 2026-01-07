package com.learnreactive.reactive.sec2;

import com.learnreactive.reactive.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

/*
 * creating a publisher is a lightweight operation.
 * Executing the publisher is a heavy operation.
 * So, we should create publishers as early as possible but execute them as late as possible.
 */

/*
 * Copyright (c) 2025 Ramjee Prasad
 * Licensed under a custom Non-Commercial, Attribution, Share-Alike License.
 * See the LICENSE file in the project root for full license information.
 *
 * Project: reactive
 * Package: com.learnreactive.reactive.sec2
 * Created by: Ashish Kushwaha on 22-10-2025 14:38
 * File: Lec09PublisherCreateVsExecuting
 *
 * This source code is intended for educational and non-commercial purposes only.
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *   - Attribution must be given to the original author.
 *   - The code must be shared under the same license.
 *   - Commercial use is strictly prohibited.
 *
 */
public class Lec09PublisherCreateVsExecution {

    private static final Logger log = LoggerFactory.getLogger(Lec09PublisherCreateVsExecution.class);

    static void main() throws InterruptedException {
        getName()
                .subscribe(Util.subscriber("Ashish"));
    }

    private static Mono<String> getName() {
        log.info("Entering getName");
        return Mono.fromSupplier(() -> {
            log.info("Generating name");
            Util.sleep(3);
            return Util.getFaker().name().fullName();
        });
    }
}
