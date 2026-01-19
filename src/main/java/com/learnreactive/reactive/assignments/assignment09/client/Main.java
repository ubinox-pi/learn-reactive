package com.learnreactive.reactive.assignments.assignment09.client;

import com.learnreactive.reactive.common.Util;

/*
 * Copyright (c) 2026 Ramjee Prasad
 * Licensed under a custom Non-Commercial, Attribution, Share-Alike License.
 * See the LICENSE file in the project root for full license information.
 *
 * Project: reactive
 * Package: com.learnreactive.reactive.assignments.assignment09.client
 * Created by: Ashish Kushwaha on 13-01-2026 20:31
 * File: Main
 *
 * This source code is intended for educational and non-commercial purposes only.
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *   - Attribution must be given to the original author.
 *   - The code must be shared under the same license.
 *   - Commercial use is strictly prohibited.
 *
 */
public class Main {
    static void main() {
        var client = new ExternalServiceClient();

        for (int i = 0; i < 10; i++) {
            client.getProductDetails(i)
                    .subscribe(Util.subscriber("ProductDetails-" + i));
        }

        Util.sleep(3);
    }
}
