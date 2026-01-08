package com.learnreactive.reactive.sec02;

import com.learnreactive.reactive.common.Util;
import com.learnreactive.reactive.sec02.client.ExternalServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * Copyright (c) 2025 Ramjee Prasad
 * Licensed under a custom Non-Commercial, Attribution, Share-Alike License.
 * See the LICENSE file in the project root for full license information.
 *
 * Project: reactive
 * Package: com.learnreactive.reactive.sec2
 * Created by: Ashish Kushwaha on 22-10-2025 15:46
 * File: Lec11NonBlockingIO
 *
 * This source code is intended for educational and non-commercial purposes only.
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *   - Attribution must be given to the original author.
 *   - The code must be shared under the same license.
 *   - Commercial use is strictly prohibited.
 *
 */
public class Lec11NonBlockingIO {

    private static final Logger log = LoggerFactory.getLogger(Lec11NonBlockingIO.class);

    static void main() {
        ExternalServiceClient client = new ExternalServiceClient();

        log.info("Calling getProductName");

        for (int i = 0; i < 100; i++) {
//            client.getProductName(i)
//                    .subscribe(Util.subscriber("sub1"));

            //it will block the current thread until the response is received
            client.getProductName(i)
                    .block();

            log.info("Completed {}", i);
            Util.sleep(1);

        }


        Util.sleep(2);
    }
}
