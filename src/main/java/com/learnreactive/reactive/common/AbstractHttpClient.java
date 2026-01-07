package com.learnreactive.reactive.common;


import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.LoopResources;

/*
 * Copyright (c) 2025 Ramjee Prasad
 * Licensed under a custom Non-Commercial, Attribution, Share-Alike License.
 * See the LICENSE file in the project root for full license information.
 *
 * Project: reactive
 * Package: com.learnreactive.reactive.common
 * Created by: Ashish Kushwaha on 22-10-2025 15:35
 * File: AbstractHttpClient
 *
 * This source code is intended for educational and non-commercial purposes only.
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *   - Attribution must be given to the original author.
 *   - The code must be shared under the same license.
 *   - Commercial use is strictly prohibited.
 *
 */
public abstract class AbstractHttpClient {
    private static final String BASE_URL = "http://localhost:8080";
    protected final HttpClient httpClient;

    public AbstractHttpClient() {
        LoopResources loopResources = LoopResources.create("ashish", 1, true);
        this.httpClient = HttpClient.create().runOn(loopResources).baseUrl(BASE_URL);
    }
}
