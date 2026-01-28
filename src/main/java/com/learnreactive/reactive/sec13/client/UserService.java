package com.learnreactive.reactive.sec13.client;

import reactor.util.context.Context;

import java.util.Map;
import java.util.function.Function;

/*
 * Copyright (c) 2026 Ramjee Prasad
 * Licensed under a custom Non-Commercial, Attribution, Share-Alike License.
 * See the LICENSE file in the project root for full license information.
 *
 * Project: reactive
 * Package: com.learnreactive.reactive.sec13.client
 * Created by: Ashish Kushwaha on 28-01-2026 23:39
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
public class UserService {

    private static final Map<String, String> USER_CATEGORY = Map.of(
            "Ashish", "standard",
            "Ramjee", "premium"
    );

    public static Function<Context, Context> userCategoryContext() {
        return ctx -> ctx.<String>getOrEmpty("user")
                .filter(USER_CATEGORY::containsKey)
                .map(USER_CATEGORY::get)
                .map(category -> ctx.put("category", category))
                .orElse(Context.empty());
    }
}
