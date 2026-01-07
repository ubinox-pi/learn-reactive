package com.learnreactive.reactive.assignments.assignment03;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * Copyright (c) 2025 Ramjee Prasad
 * Licensed under a custom Non-Commercial, Attribution, Share-Alike License.
 * See the LICENSE file in the project root for full license information.
 *
 * Project: reactive
 * Package: com.learnreactive.reactive.assignments.assignment03
 * Created by: Ashish Kushwaha on 01-11-2025 10:05
 * File: StockPriceObserver
 *
 * This source code is intended for educational and non-commercial purposes only.
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *   - Attribution must be given to the original author.
 *   - The code must be shared under the same license.
 *   - Commercial use is strictly prohibited.
 *
 */
public class StockPriceObserver implements Subscriber<Integer> {

    private static final Logger log = LoggerFactory.getLogger(StockPriceObserver.class);
    private int balance = 10000;
    private int quantity = 0;
    private Subscription subscription;

    @Override
    public void onSubscribe(Subscription subscription) {
        subscription.request(Long.MAX_VALUE);
        this.subscription = subscription;
    }

    @Override
    public void onNext(Integer price) {
        if (price > 90 && balance >= price) {
            quantity++;
            balance = balance - price;
            log.info("Bought a stock at price: {} | Current Balance: {} | Total Stocks: {}", price, balance, quantity);
        } else if (price > 110 && quantity > 0) {
            log.info("Decided to sell stock(s) as price is greater than 110");
            balance = balance + (quantity * price);
            quantity = 0;
            log.info("Sold a stock at price: {} | Current Balance: {} | Total Stocks: {}", price, balance, quantity);
            subscription.cancel();
        }
    }

    @Override
    public void onError(Throwable throwable) {
        log.error("Error occurred: {}", throwable.getMessage());
    }

    @Override
    public void onComplete() {
        log.info("Stock price observer has been completed.");
    }
}
