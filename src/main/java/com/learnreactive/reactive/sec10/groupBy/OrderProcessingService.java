package com.learnreactive.reactive.sec10.groupBy;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

/*
 * Copyright (c) 2026 Ramjee Prasad
 * Licensed under a custom Non-Commercial, Attribution, Share-Alike License.
 * See the LICENSE file in the project root for full license information.
 *
 * Project: reactive
 * Package: com.learnreactive.reactive.sec10.groupBy
 * Created by: Ashish Kushwaha on 24-01-2026 19:00
 * File: OrderProcessingService
 *
 * This source code is intended for educational and non-commercial purposes only.
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *   - Attribution must be given to the original author.
 *   - The code must be shared under the same license.
 *   - Commercial use is strictly prohibited.
 *
 */
public class OrderProcessingService {

    private static final Map<String, UnaryOperator<Flux<PurchaseOrder>>> PROCESSOR_MAP = Map.of(
            "Kids", kidsProcessing(),
            "Automotive", automotiveProcessing()
    );

    private static UnaryOperator<Flux<PurchaseOrder>> automotiveProcessing() {
        return flux -> flux
                .map(po -> new PurchaseOrder(po.item(), po.category(), po.price() + 100));
    }

    private static UnaryOperator<Flux<PurchaseOrder>> kidsProcessing() {
        return flux -> flux
                .flatMap(po -> getFreeKidsOrder(po).flux().startWith(po));
    }

    private static Mono<PurchaseOrder> getFreeKidsOrder(PurchaseOrder order) {
        return Mono.fromSupplier(() -> new PurchaseOrder(
                order.item() + "_FREE",
                order.category(),
                0
        ));
    }

    public static Predicate<PurchaseOrder> canProcess() {
        return po -> PROCESSOR_MAP.containsKey(po.category());
    }

    public static UnaryOperator<Flux<PurchaseOrder>> getProcessor(String category) {
        return PROCESSOR_MAP.get(category);
    }

}
