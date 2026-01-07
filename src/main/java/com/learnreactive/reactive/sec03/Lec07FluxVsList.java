package com.learnreactive.reactive.sec03;

import com.learnreactive.reactive.common.Util;
import com.learnreactive.reactive.sec03.helper.NameGenerator;
import com.learnreactive.reactive.sec1.publisher.SubscriptionImpl;
import com.learnreactive.reactive.sec1.subscriber.SubscriberImpl;

/*
 * Copyright (c) 2025 Ramjee Prasad
 * Licensed under a custom Non-Commercial, Attribution, Share-Alike License.
 * See the LICENSE file in the project root for full license information.
 *
 * Project: reactive
 * Package: com.learnreactive.reactive.sec03
 * Created by: Ashish Kushwaha on 30-10-2025 09:59
 * File: Lec07FluxVsList
 *
 * This source code is intended for educational and non-commercial purposes only.
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *   - Attribution must be given to the original author.
 *   - The code must be shared under the same license.
 *   - Commercial use is strictly prohibited.
 *
 */
public class Lec07FluxVsList {
    static void main() {
//        var list = NameGenerator.getNamesList(10);
//        System.out.println("List size: " + list.size());
//        System.out.println("List: " + list);

        var subscriber = new SubscriberImpl();
        subscriber.onSubscribe(new SubscriptionImpl(subscriber));
        NameGenerator.getNamesFlux(10)
                .subscribe(Util.subscriber("Ashish"));

        subscriber.getSubscription().request(3);
    }
}
