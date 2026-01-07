package com.learnreactive.reactive.assignments.assignment03;

import com.learnreactive.reactive.common.Util;
import com.learnreactive.reactive.sec03.client.ExternalServiceClient;

/*
 * Copyright (c) 2025 Ramjee Prasad
 * Licensed under a custom Non-Commercial, Attribution, Share-Alike License.
 * See the LICENSE file in the project root for full license information.
 *
 * Project: reactive
 * Package: com.learnreactive.reactive.assignments.assignment03
 * Created by: Ashish Kushwaha on 01-11-2025 10:13
 * File: Assignment
 *
 * This source code is intended for educational and non-commercial purposes only.
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *   - Attribution must be given to the original author.
 *   - The code must be shared under the same license.
 *   - Commercial use is strictly prohibited.
 *
 */
public class Assignment {

    static void main() {
        var client = new ExternalServiceClient();
        var observer = new StockPriceObserver();
        client.getPriceChanges()
                .subscribe(observer);

        Util.sleep(20);
    }
}
