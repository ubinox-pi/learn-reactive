package com.learnreactive.reactive.assignments.assignment10;

/*
 * Copyright (c) 2026 Ramjee Prasad
 * Licensed under a custom Non-Commercial, Attribution, Share-Alike License.
 * See the LICENSE file in the project root for full license information.
 *
 * Project: reactive
 * Package: com.learnreactive.reactive.assignments.assignment10
 * Created by: Ashish Kushwaha on 28-01-2026 22:34
 * File: SlackMessage
 *
 * This source code is intended for educational and non-commercial purposes only.
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *   - Attribution must be given to the original author.
 *   - The code must be shared under the same license.
 *   - Commercial use is strictly prohibited.
 *
 */
public record SlackMessage(
        String sender,
        String message
) {
    private static final String MESSAGE_FORMAT = "[%s -> %s] : %s";

    public String formatForDelivery(String receiver) {
        return String.format(MESSAGE_FORMAT, sender, receiver, message);
    }
}
