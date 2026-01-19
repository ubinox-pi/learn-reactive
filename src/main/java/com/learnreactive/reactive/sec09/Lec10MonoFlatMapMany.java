package com.learnreactive.reactive.sec09;

import com.learnreactive.reactive.common.Util;
import com.learnreactive.reactive.sec09.application.OrderService;
import com.learnreactive.reactive.sec09.application.UserService;

/*
 * Copyright (c) 2026 Ramjee Prasad
 * Licensed under a custom Non-Commercial, Attribution, Share-Alike License.
 * See the LICENSE file in the project root for full license information.
 *
 * Project: reactive
 * Package: com.learnreactive.reactive.sec09
 * Created by: Ashish Kushwaha on 17-01-2026 19:25
 * File: Lec10MonoFlatMapMany
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
 * Sequential non-blocking IO calls!
 * flatMap is used to flatten the inner publisher / to subscriber to the inner publisher
 * Mono is supported to be 1 item - what if the flatmap returns multiple items!?
 * */
public class Lec10MonoFlatMapMany {
    static void main() {
        /*
         * We have username.
         * get all user oders
         * */
//        UserService.getUserIdForUserName("Ashish")
//                .flatMap(OrderService::getUserOrder)
//                .subscribe(Util.subscriber("ashish")); // this will give error as flatMap expects Mono but getUserOrder returns Flux

        UserService.getUserIdForUserName("Ashish")
                .flatMapMany(OrderService::getUserOrder)
                .subscribe(Util.subscriber("ashish"));

        Util.sleep(3);
    }
}
