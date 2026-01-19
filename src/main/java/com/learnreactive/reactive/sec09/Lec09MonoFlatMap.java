package com.learnreactive.reactive.sec09;

/*
 * Copyright (c) 2026 Ramjee Prasad
 * Licensed under a custom Non-Commercial, Attribution, Share-Alike License.
 * See the LICENSE file in the project root for full license information.
 *
 * Project: reactive
 * Package: com.learnreactive.reactive.sec09
 * Created by: Ashish Kushwaha on 17-01-2026 19:11
 * File: MonoFlatMap
 *
 * This source code is intended for educational and non-commercial purposes only.
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *   - Attribution must be given to the original author.
 *   - The code must be shared under the same license.
 *   - Commercial use is strictly prohibited.
 *
 */

import com.learnreactive.reactive.common.Util;
import com.learnreactive.reactive.sec09.application.PaymentService;
import com.learnreactive.reactive.sec09.application.UserService;

/*
 * Sequential non-blocking IO calls
 * faltMap is used to flatten the inner publisher / to subscribe to the inner publisher
 * */
public class Lec09MonoFlatMap {
    static void main() {
        /*
         * We have username.
         * Get the user account balance
         * */
//        UserService.getUserIdForUserName("Ashish")
//                .map(PaymentService::getUserBalance)
//                .subscribe(Util.subscriber("ashish")); // this will print Mono<...> because map wraps the inner publisher into outer publisher


        UserService.getUserIdForUserName("Ramjee")
                .flatMap(PaymentService::getUserBalance)
                .subscribe(Util.subscriber("ashish"));
    }
}
