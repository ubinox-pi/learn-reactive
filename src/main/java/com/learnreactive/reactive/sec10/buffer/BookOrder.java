package com.learnreactive.reactive.sec10.buffer;

import com.learnreactive.reactive.common.Util;

/*
 * Copyright (c) 2026 Ramjee Prasad
 * Licensed under a custom Non-Commercial, Attribution, Share-Alike License.
 * See the LICENSE file in the project root for full license information.
 *
 * Project: reactive
 * Package: com.learnreactive.reactive.assignments.assignment
 * Created by: Ashish Kushwaha on 23-01-2026 22:14
 * File: BookOrder
 *
 * This source code is intended for educational and non-commercial purposes only.
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *   - Attribution must be given to the original author.
 *   - The code must be shared under the same license.
 *   - Commercial use is strictly prohibited.
 *
 */
public record BookOrder(
        String genre,
        String title,
        Integer price
) {

    public static BookOrder create() {
        var book = Util.getFaker().book();
        return new BookOrder(
                book.genre(),
                book.title(),
                Util.getFaker().number().numberBetween(10, 100)
        );
    }
}
