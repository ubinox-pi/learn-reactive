package com.learnreactive.reactive.sec10.groupBy;

import com.github.javafaker.Commerce;
import com.learnreactive.reactive.common.Util;

/*
 * Copyright (c) 2026 Ramjee Prasad
 * Licensed under a custom Non-Commercial, Attribution, Share-Alike License.
 * See the LICENSE file in the project root for full license information.
 *
 * Project: reactive
 * Package: com.learnreactive.reactive.common
 * Created by: Ashish Kushwaha on 24-01-2026 18:56
 * File: PurchaseOrder
 *
 * This source code is intended for educational and non-commercial purposes only.
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *   - Attribution must be given to the original author.
 *   - The code must be shared under the same license.
 *   - Commercial use is strictly prohibited.
 *
 */
public record PurchaseOrder(
        String item,
        String category,
        Integer price
) {
    public static PurchaseOrder create() {
        Commerce commerce = Util.getFaker().commerce();
        return new PurchaseOrder(
                commerce.productName(),
                commerce.department(),
                Util.getFaker().number().numberBetween(10, 500)
        );
    }
}
