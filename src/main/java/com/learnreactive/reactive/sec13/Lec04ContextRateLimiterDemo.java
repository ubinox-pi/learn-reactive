package com.learnreactive.reactive.sec13;


import com.learnreactive.reactive.common.Util;
import com.learnreactive.reactive.sec13.client.ExternalServiceClient;
import reactor.util.context.Context;

/*
 * Copyright (c) 2026 Ramjee Prasad
 * Licensed under a custom Non-Commercial, Attribution, Share-Alike License.
 * See the LICENSE file in the project root for full license information.
 *
 * Project: reactive
 * Package: com.learnreactive.reactive.sec13.client
 * Created by: Ashish Kushwaha on 28-01-2026 23:30
 * File: Lec04ContextRateLimiterDemo
 *
 * This source code is intended for educational and non-commercial purposes only.
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *   - Attribution must be given to the original author.
 *   - The code must be shared under the same license.
 *   - Commercial use is strictly prohibited.
 *
 */
public class Lec04ContextRateLimiterDemo {
    static void main() {
        var client = new ExternalServiceClient();

        for (int i = 0; i < 10; i++) {
            client.getBook()
                    .contextWrite(Context.of("user", "Ashish"))
                    .subscribe(Util.subscriber("Ashish"));

            Util.sleep(1);
        }

        Util.sleep(5);
    }
}
