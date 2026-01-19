package com.learnreactive.reactive.sec09;

/*
 * Copyright (c) 2026 Ramjee Prasad
 * Licensed under a custom Non-Commercial, Attribution, Share-Alike License.
 * See the LICENSE file in the project root for full license information.
 *
 * Project: reactive
 * Package: com.learnreactive.reactive.sec09
 * Created by: Ashish Kushwaha on 19-01-2026 17:57
 * File: Lec13ConcatMap
 *
 * This source code is intended for educational and non-commercial purposes only.
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *   - Attribution must be given to the original author.
 *   - The code must be shared under the same license.
 *   - Commercial use is strictly prohibited.
 *
 */

import com.learnreactive.reactive.assignments.assignment09.client.ExternalServiceClient;
import com.learnreactive.reactive.common.Util;
import reactor.core.publisher.Flux;

/*
 * ensure that demo.jar is up and running
 * */
public class Lec13ConcatMap {

    static void main() {
        var client = new ExternalServiceClient();

        Flux.range(1, 10)
                .concatMap(client::getProductDetails)
                .subscribe(Util.subscriber("Ashish"));

        Util.sleep(20);
    }
}
