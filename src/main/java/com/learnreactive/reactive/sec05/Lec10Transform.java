package com.learnreactive.reactive.sec05;

import com.learnreactive.reactive.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.function.UnaryOperator;

/*
 * Copyright (c) 2026 Ramjee Prasad
 * Licensed under a custom Non-Commercial, Attribution, Share-Alike License.
 * See the LICENSE file in the project root for full license information.
 *
 * Project: reactive
 * Package: com.learnreactive.reactive.sec05
 * Created by: Ashish Kushwaha on 07-01-2026 19:16
 * File: Lec10Transforn
 *
 * This source code is intended for educational and non-commercial purposes only.
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *   - Attribution must be given to the original author.
 *   - The code must be shared under the same license.
 *   - Commercial use is strictly prohibited.
 *
 */
public class Lec10Transform {

    private static final Logger log = LoggerFactory.getLogger(Lec10Transform.class);

    static void main() {
        var isDebuggerEnabled = true;

        getCustomers()
                .transform(isDebuggerEnabled ? addDebugger() : UnaryOperator.identity())
                .subscribe(Util.subscriber("Ashish"));

        getPurchaseOrders()
                .transform(addDebugger())
                .subscribe(Util.subscriber("Ashish"));
    }

    private static <T> UnaryOperator<Flux<T>> addDebugger() {
        return tFlux -> tFlux
                .doOnNext(i -> log.info("Received: {}", i))
                .doOnComplete(() -> log.info("Completed!"))
                .doOnError(err -> log.error("Error occurred: ", err));
    }

    private static Flux<Customer> getCustomers() {
        return Flux.range(1, 3)
                .map(i -> new Customer(i, Util.getFaker().name().fullName()));
    }

    private static Flux<PurchaseOrder> getPurchaseOrders() {
        return Flux.range(1, 5)
                .map(i -> new PurchaseOrder(Util.getFaker().name().fullName(), i, i * 10));
    }

    public record Customer(int id, String name) {
    }

    public record PurchaseOrder(String productName, int price, int quantity) {
    }
}
