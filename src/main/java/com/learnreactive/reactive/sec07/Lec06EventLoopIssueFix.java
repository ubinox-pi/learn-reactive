package com.learnreactive.reactive.sec07;

import com.learnreactive.reactive.common.Util;
import com.learnreactive.reactive.sec07.client.ExternalServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * Copyright (c) 2026 Ramjee Prasad
 * Licensed under a custom Non-Commercial, Attribution, Share-Alike License.
 * See the LICENSE file in the project root for full license information.
 *
 * Project: reactive
 * Package: com.learnreactive.reactive.sec07
 * Created by: Ashish Kushwaha on 10-01-2026 19:28
 * File: Lec06EventLoopIssueFix
 *
 * This source code is intended for educational and non-commercial purposes only.
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *   - Attribution must be given to the original author.
 *   - The code must be shared under the same license.
 *   - Commercial use is strictly prohibited.
 *
 */
public class Lec06EventLoopIssueFix {
    private static final Logger log = LoggerFactory.getLogger(Lec06EventLoopIssueFix.class);

    static void main() {
        var client = new ExternalServiceClient();
        log.info("Starting");

        for (int i = 0; i < 5; i++) {
            client.getProductName(i)
                    .map(Lec06EventLoopIssueFix::process)
                    .subscribe(Util.subscriber("Ashish"));
        }

        Util.sleep(6);
    }

    private static String process(String input) {
        Util.sleep(1);
        return input + "-processed";
    }
}
