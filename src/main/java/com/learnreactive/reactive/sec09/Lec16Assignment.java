package com.learnreactive.reactive.sec09;

import com.learnreactive.reactive.common.Util;
import com.learnreactive.reactive.sec09.application.*;
import reactor.core.publisher.Mono;

import java.util.List;

/*
 * Copyright (c) 2026 Ramjee Prasad
 * Licensed under a custom Non-Commercial, Attribution, Share-Alike License.
 * See the LICENSE file in the project root for full license information.
 *
 * Project: reactive
 * Package: com.learnreactive.reactive.sec09
 * Created by: Ashish Kushwaha on 19-01-2026 18:47
 * File: Lec16Assignment
 *
 * This source code is intended for educational and non-commercial purposes only.
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *   - Attribution must be given to the original author.
 *   - The code must be shared under the same license.
 *   - Commercial use is strictly prohibited.
 *
 */
public class Lec16Assignment {

    static void main() {
        UserService.getAllUser()
                .flatMap(Lec16Assignment::getUserInformation)
                .subscribe(Util.subscriber("Ashish"));


        Util.sleep(10);
    }

    private static Mono<UserInformation> getUserInformation(User user) {
        return Mono.zip(
                        PaymentService.getUserBalance(user.id()),
                        OrderService.getUserOrder(user.id()).collectList()
                )
                .map(t -> new UserInformation(user.id(), user.name(), t.getT1(), t.getT2()));
    }

    record UserInformation(Integer userId,
                           String username,
                           Integer balance,
                           List<Order> orders) {
    }
}
