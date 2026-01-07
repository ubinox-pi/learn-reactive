package com.learnreactive.reactive.sec1;

/*
 * Copyright (c) 2025 Ramjee Prasad
 * Licensed under a custom Non-Commercial, Attribution, Share-Alike License.
 * See the LICENSE file in the project root for full license information.
 *
 * Project: reactive
 * Package: com.learnreactive.reactive.sec1
 * Created by: Ashish Kushwaha on 19-10-2025 18:21
 * File: Main
 *
 * This source code is intended for educational and non-commercial purposes only.
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *   - Attribution must be given to the original author.
 *   - The code must be shared under the same license.
 *   - Commercial use is strictly prohibited.
 *
 */

import com.learnreactive.reactive.sec1.publisher.PublisherImpl;
import com.learnreactive.reactive.sec1.subscriber.SubscriberImpl;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

/*
 * Publisher will not produce data unless the subscriber requests for it.
 * Publisher will only produce data when the subscriber requests for it or less
 * Subscriber can cancel the subscription. Producer should stop at that moment as the subscriber is no longer interested in consuming data.
 * Producer can send the error signal to indicate something is wrong.
 * */
public class Main {
    static void main(String[] args) throws InterruptedException {
        test4();
    }

    static void test4() throws InterruptedException {
        Publisher<String> publisher = new PublisherImpl();
        SubscriberImpl subscriber = new SubscriberImpl();
        publisher.subscribe(subscriber);
        subscriber.getSubscription().request(3);
        Thread.sleep(2000);
        subscriber.getSubscription().request(11);
        Thread.sleep(2000);
        subscriber.getSubscription().request(3);
        Thread.sleep(2000);
    }

    static void test3() throws InterruptedException {
        Publisher<String> publisher = new PublisherImpl();
        SubscriberImpl subscriber = new SubscriberImpl();
        publisher.subscribe(subscriber);
        subscriber.getSubscription().request(3);
        Thread.sleep(2000);
        subscriber.getSubscription().cancel();
        Thread.sleep(2000);
        subscriber.getSubscription().request(3);
        Thread.sleep(2000);
        subscriber.getSubscription().request(3);
        Thread.sleep(2000);
    }

    static void test2() throws InterruptedException {
        Publisher<String> publisher = new PublisherImpl();
        SubscriberImpl subscriber = new SubscriberImpl();
        publisher.subscribe(subscriber);
        subscriber.getSubscription().request(3);
        Thread.sleep(2000);
        subscriber.getSubscription().request(3);
        Thread.sleep(2000);
        subscriber.getSubscription().request(3);
        Thread.sleep(2000);
        subscriber.getSubscription().request(3);
        Thread.sleep(2000);
    }

    static void test1() {
        Publisher<String> publisher = new PublisherImpl();
        Subscriber<String> subscriber = new SubscriberImpl();
        publisher.subscribe(subscriber);
    }
}
