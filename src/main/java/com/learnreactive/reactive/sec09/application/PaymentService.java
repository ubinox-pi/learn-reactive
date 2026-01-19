package com.learnreactive.reactive.sec09.application;

/*
 * Copyright (c) 2026 Ramjee Prasad
 * Licensed under a custom Non-Commercial, Attribution, Share-Alike License.
 * See the LICENSE file in the project root for full license information.
 *
 * Project: reactive
 * Package: com.learnreactive.reactive.sec09.application
 * Created by: Ashish Kushwaha on 17-01-2026 19:07
 * File: PaymentService
 *
 * This source code is intended for educational and non-commercial purposes only.
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *   - Attribution must be given to the original author.
 *   - The code must be shared under the same license.
 *   - Commercial use is strictly prohibited.
 *
 */

import reactor.core.publisher.Mono;

import java.util.Map;

/*
 * Just for demo.
 * Imagine payment-service, as an application, has an endpoint
 * This is a client which represents to call the endpoint (IO request).
 * */
public class PaymentService {
    private static final Map<Integer, Integer> userBalanceTable = Map.of(
            1, 100,
            2, 200,
            3, 300,
            4, 400
    );

    public static Mono<Integer> getUserBalance(int userId) {
        return Mono.fromSupplier(() -> userBalanceTable.get(userId));
    }
}
