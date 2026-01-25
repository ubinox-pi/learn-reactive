package com.learnreactive.reactive.sec11;


import com.learnreactive.reactive.common.Util;
import com.learnreactive.reactive.sec11.client.ExternalServiceClient;
import com.learnreactive.reactive.sec11.client.ServerError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.util.retry.Retry;

import java.time.Duration;

/*
 * Copyright (c) 2026 Ramjee Prasad
 * Licensed under a custom Non-Commercial, Attribution, Share-Alike License.
 * See the LICENSE file in the project root for full license information.
 *
 * Project: reactive
 * Package: com.learnreactive.reactive.sec11
 * Created by: Ashish Kushwaha on 24-01-2026 21:21
 * File: Lec03DemoJar
 *
 * This source code is intended for educational and non-commercial purposes only.
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *   - Attribution must be given to the original author.
 *   - The code must be shared under the same license.
 *   - Commercial use is strictly prohibited.
 *
 */
public class Lec03DemoJar {
    private static final Logger log = LoggerFactory.getLogger(Lec03DemoJar.class);

    static void main() {

        retry();

        Util.sleep(60);
    }

    private static void repeat() {
        var client = new ExternalServiceClient();
        client.getCountry()
                .repeat()
                .takeUntil(c -> c.equalsIgnoreCase("canada"))
                .subscribe(Util.subscriber("Ashish"));
    }

    private static void retry() {
        var client = new ExternalServiceClient();
        client.getProductName(2)
                .retryWhen(retryOnServerError())
                .subscribe(Util.subscriber("Ashish"));
    }

    private static Retry retryOnServerError() {
        return Retry.fixedDelay(20, Duration.ofSeconds(1))
                .filter(ex -> ServerError.class.equals(ex.getClass()))
                .doBeforeRetry(retrySignal -> log.info("Retrying {} time", retrySignal.failure().getMessage()));
    }
}
