package com.learnreactive.reactive.sec09.application;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

/*
 * Copyright (c) 2026 Ramjee Prasad
 * Licensed under a custom Non-Commercial, Attribution, Share-Alike License.
 * See the LICENSE file in the project root for full license information.
 *
 * Project: reactive
 * Package: com.learnreactive.reactive.sec09.application
 * Created by: Ashish Kushwaha on 13-01-2026 21:24
 * File: UserService
 *
 * This source code is intended for educational and non-commercial purposes only.
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *   - Attribution must be given to the original author.
 *   - The code must be shared under the same license.
 *   - Commercial use is strictly prohibited.
 *
 */

/*
 * Just for demo
 * Imagine user-service, as an application, has 2 endpoints
 * This is a client which represents to call those two endpoints (IO requests).
 * */
public class UserService {

    private static final Map<String, Integer> userTable = Map.of(
            "Ashish", 1,
            "Ramjee", 2,
            "John", 3
    );

    public static Flux<User> getAllUser() {
        return Flux.fromIterable(userTable.entrySet())
                .map(entry -> new User(entry.getValue(), entry.getKey()));
    }

    public static Mono<Integer> getUserIdForUserName(String username) {
        return Mono.fromSupplier(() -> userTable.get(username));
    }
}
