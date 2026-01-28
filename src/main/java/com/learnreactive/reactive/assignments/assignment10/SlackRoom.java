package com.learnreactive.reactive.assignments.assignment10;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

/*
 * Copyright (c) 2026 Ramjee Prasad
 * Licensed under a custom Non-Commercial, Attribution, Share-Alike License.
 * See the LICENSE file in the project root for full license information.
 *
 * Project: reactive
 * Package: com.learnreactive.reactive.assignments.assignment10
 * Created by: Ashish Kushwaha on 28-01-2026 22:36
 * File: SlackRoom
 *
 * This source code is intended for educational and non-commercial purposes only.
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *   - Attribution must be given to the original author.
 *   - The code must be shared under the same license.
 *   - Commercial use is strictly prohibited.
 *
 */
public class SlackRoom {
    private static final Logger log = LoggerFactory.getLogger(SlackRoom.class);
    private final String name;
    private final Sinks.Many<SlackMessage> sink;
    private final Flux<SlackMessage> flux;

    public SlackRoom(String name) {
        this.name = name;
        this.sink = Sinks.many().replay().all();
        this.flux = sink.asFlux();
    }

    public void addMember(SlackMember slackMember) {
        log.info("{} joined the room {}", slackMember.getName(), this.name);
        this.subscribeToRoomMessages(slackMember);
        slackMember.setMessageConsumer(msg -> this.postMessage(slackMember.getName(), msg));
    }

    private void subscribeToRoomMessages(SlackMember slackMember) {
        this.flux
                .filter(sm -> !sm.sender().equals(slackMember.getName()))
                .map(sm -> sm.formatForDelivery(slackMember.getName()))
                .subscribe(slackMember::receives);
    }

    private void postMessage(String sender, String message) {
        this.sink.tryEmitNext(new SlackMessage(sender, message));
    }
}
