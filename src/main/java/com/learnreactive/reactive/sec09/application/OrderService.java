package com.learnreactive.reactive.sec09.application;

/*
 * Copyright (c) 2026 Ramjee Prasad
 * Licensed under a custom Non-Commercial, Attribution, Share-Alike License.
 * See the LICENSE file in the project root for full license information.
 *
 * Project: reactive
 * Package: com.learnreactive.reactive.sec09.application
 * Created by: Ashish Kushwaha on 14-01-2026 21:45
 * File: OrderService
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
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.Map;

/*
 * Just for demo
 * Imagine order-service, as an application, has and endpoints.
 * This is a client class which represents to call the endpoints (IO request).
 * */
public class OrderService {
    private static final Map<Integer, List<Order>> orderTable = Map.of(
            1, List.of(
                    new Order(1, Util.getFaker().commerce().productName(), Util.getFaker().random().nextInt(10, 100)),
                    new Order(1, Util.getFaker().commerce().productName(), Util.getFaker().random().nextInt(100, 1000))
            ),
            2, List.of(
                    new Order(2, Util.getFaker().commerce().productName(), Util.getFaker().random().nextInt(1000, 10000)),
                    new Order(2, Util.getFaker().commerce().productName(), Util.getFaker().random().nextInt(10000, 100000)),
                    new Order(2, Util.getFaker().commerce().productName(), Util.getFaker().random().nextInt(100000, 1000000))
            ),
            3, List.of(
                    new Order(3, Util.getFaker().commerce().productName(), Util.getFaker().random().nextInt(1000, 10000)),
                    new Order(3, Util.getFaker().commerce().productName(), Util.getFaker().random().nextInt(10000, 100000)),
                    new Order(3, Util.getFaker().commerce().productName(), Util.getFaker().random().nextInt(100000, 1000000))
            ),
            4, List.of(
                    new Order(4, Util.getFaker().commerce().productName(), Util.getFaker().random().nextInt(1000, 10000)),
                    new Order(4, Util.getFaker().commerce().productName(), Util.getFaker().random().nextInt(10000, 100000)),
                    new Order(4, Util.getFaker().commerce().productName(), Util.getFaker().random().nextInt(100000, 1000000))
            )
    );

    public static Flux<Order> getUserOrder(Integer userId) {
        return Flux.fromIterable(orderTable.get(userId))
                .delayElements(Duration.ofMillis(500));
    }
}
