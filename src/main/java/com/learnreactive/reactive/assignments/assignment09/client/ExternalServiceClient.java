package com.learnreactive.reactive.assignments.assignment09.client;

import com.learnreactive.reactive.assignments.assignment09.Product;
import com.learnreactive.reactive.common.AbstractHttpClient;
import reactor.core.publisher.Mono;

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

    public Mono<Product> getProductDetails(int productId) {
        return Mono.zip(
                        getProductName(productId),
                        getProductPrice(productId),
                        getProductReview(productId)
                )
                .map(t -> new Product(t.getT1(), t.getT2(), t.getT3()));
    }

    private Mono<String> getProductName(int productId) {
        return getInfo("/demo05/product/" + productId);
    }

    private Mono<String> getProductPrice(int productId) {
        return getInfo("/demo05/price/" + productId);
    }

    private Mono<String> getProductReview(int productId) {
        return getInfo("/demo05/review/" + productId);
    }

    private Mono<String> getInfo(String path) {
        return this.httpClient.get()
                .uri(path)
                .responseContent()
                .asString()
                .next();
    }


}
