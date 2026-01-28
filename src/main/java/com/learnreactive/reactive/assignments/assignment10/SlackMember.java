package com.learnreactive.reactive.assignments.assignment10;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Consumer;

/*
 * Copyright (c) 2026 Ramjee Prasad
 * Licensed under a custom Non-Commercial, Attribution, Share-Alike License.
 * See the LICENSE file in the project root for full license information.
 *
 * Project: reactive
 * Package: com.learnreactive.reactive.assignments.assignment10
 * Created by: Ashish Kushwaha on 28-01-2026 22:29
 * File: SlackMember
 *
 * This source code is intended for educational and non-commercial purposes only.
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *   - Attribution must be given to the original author.
 *   - The code must be shared under the same license.
 *   - Commercial use is strictly prohibited.
 *
 */
public class SlackMember {
    private static final Logger log = LoggerFactory.getLogger(SlackMember.class);

    private final String name;
    private Consumer<String> messageConsumer;

    public SlackMember(String name) {
        this.name = name;
    }

    static void main() {

    }

    public String getName() {
        return name;
    }

    public void setMessageConsumer(Consumer<String> messageConsumer) {
        this.messageConsumer = messageConsumer;
    }

    public void says(String message) {
        this.messageConsumer.accept(message);
    }

    public void receives(String message) {
        log.info(message);
    }
}
