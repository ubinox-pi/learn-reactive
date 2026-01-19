package com.learnreactive.reactive.sec09;

/*
 * Copyright (c) 2026 Ramjee Prasad
 * Licensed under a custom Non-Commercial, Attribution, Share-Alike License.
 * See the LICENSE file in the project root for full license information.
 *
 * Project: reactive
 * Package: com.learnreactive.reactive.sec09
 * Created by: Ashish Kushwaha on 17-01-2026 20:04
 * File: Lec11FluxFlatMap
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
import com.learnreactive.reactive.sec09.application.OrderService;
import com.learnreactive.reactive.sec09.application.User;
import com.learnreactive.reactive.sec09.application.UserService;

/*
 * Sequential non-blocking IO calls!
 * flatMap is used to flatten the inner publisher / to subscriber to the innner publisher
 * */
public class Lec11FluxFlatMap {
    static void main() {

        /*
         * Get all the order from Order-service
         * */
        UserService.getAllUser()
                .map(User::id)
                .flatMap(OrderService::getUserOrder)
                .subscribe(Util.subscriber("ashish"));

        Util.sleep(3);
    }
}
