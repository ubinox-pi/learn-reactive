package com.learnreactive.reactive.sec10;

import com.learnreactive.reactive.common.Util;
import com.learnreactive.reactive.sec10.groupBy.OrderProcessingService;
import com.learnreactive.reactive.sec10.groupBy.PurchaseOrder;
import reactor.core.publisher.Flux;

import java.time.Duration;

/*
 * Copyright (c) 2026 Ramjee Prasad
 * Licensed under a custom Non-Commercial, Attribution, Share-Alike License.
 * See the LICENSE file in the project root for full license information.
 *
 * Project: reactive
 * Package: com.learnreactive.reactive.sec10
 * Created by: Ashish Kushwaha on 24-01-2026 19:12
 * File: Lec06GroupByAssignment
 *
 * This source code is intended for educational and non-commercial purposes only.
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *   - Attribution must be given to the original author.
 *   - The code must be shared under the same license.
 *   - Commercial use is strictly prohibited.
 *
 */
public class Lec06GroupByAssignment {
    static void main() {
        orderStream()
                .filter(OrderProcessingService.canProcess())
                .groupBy(PurchaseOrder::category)
                .flatMap(gf -> gf.transform(OrderProcessingService.getProcessor(gf.key())))
                .subscribe(Util.subscriber("Ashish"));

        Util.sleep(60);
    }

    private static Flux<PurchaseOrder> orderStream() {
        return Flux.interval(Duration.ofMillis(200))
                .map(_ -> PurchaseOrder.create());
    }
}
