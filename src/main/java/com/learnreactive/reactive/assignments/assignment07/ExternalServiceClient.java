package com.learnreactive.reactive.assignments.assignment07;

import com.learnreactive.reactive.common.AbstractHttpClient;
import reactor.core.publisher.Mono;

import java.time.Duration;

/*
 * Copyright (c) 2025 Ramjee Prasad
 * Licensed under a custom Non-Commercial, Attribution, Share-Alike License.
 * See the LICENSE file in the project root for full license information.
 *
 * Project: reactive
 * Package: com.learnreactive.reactive.sec2.client
 * Created by: Ashish Kushwaha on 22-10-2025 15:41
 * File: ExternalServiceClient
 *
 * This source code is intended for educational and non-commercial purposes only.
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *   - Attribution must be given to the original author.
 *   - The code must be shared under the same license.
 *   - Commercial use is strictly prohibited.
 *
 */
public class ExternalServiceClient extends AbstractHttpClient {
    public Mono<String> getProductName(int productId) {
        var defaultPath = "/demo03/product/" + productId;
        var timeoutFallback = "/demo03/timeout-fallback/product/" + productId;
        var emptyFallback = "/demo03/empty-fallback/product/" + productId;

        return getProductName(defaultPath)
                .timeout(Duration.ofSeconds(2), getProductName(timeoutFallback))
                .switchIfEmpty(getProductName(emptyFallback));
    }

    private Mono<String> getProductName(String path) {
        return this.httpClient.get()
                .uri(path)
                .responseContent()
                .asString()
                .next();
    }


}
