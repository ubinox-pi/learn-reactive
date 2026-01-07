package com.learnreactive.reactive.sec1.publisher;

import com.github.javafaker.Faker;
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
 * Package: com.learnreactive.reactive.sec1.publisher
 * Created by: Ashish Kushwaha on 19-10-2025 18:00
 * File: SubscriptionImpl
 *
 * This source code is intended for educational and non-commercial purposes only.
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *   - Attribution must be given to the original author.
 *   - The code must be shared under the same license.
 *   - Commercial use is strictly prohibited.
 *
 */
public class SubscriptionImpl implements Subscription {

    private static final Logger log = LoggerFactory.getLogger(SubscriptionImpl.class);
    private static final int MAX_ITEMS = 10;
    private final Subscriber<? super String> subscriber;
    private final Faker faker;
    private int count = 0;
    private Boolean isCancelled = false;

    public SubscriptionImpl(Subscriber<? super String> subscriber) {
        this.subscriber = subscriber;
        faker = Faker.instance();
    }

    @Override
    public void request(long l) {
        if (isCancelled) {
            return;
        }
        log.info("Subscriber has requested {} items", l);
        if (l > MAX_ITEMS) {
            this.subscriber.onError(new RuntimeException("Validation failure"));
            this.isCancelled = true;
            return;
        }
        for (int i = 0; i < l && count < MAX_ITEMS; i++) {
            count++;
            this.subscriber.onNext("Item " + faker.internet().emailAddress());
        }
        if (count == MAX_ITEMS) {
            log.info("No more items yo be produced. Sending onComplete signal to subscriber");
            this.subscriber.onComplete();
            this.isCancelled = true;
        }
    }

    @Override
    public void cancel() {
        log.info("subscriber has cancelled the subscription");
        this.isCancelled = true;
    }
}
