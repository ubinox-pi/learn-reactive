package com.learnreactive.reactive.sec12;

import com.learnreactive.reactive.assignments.assignment10.SlackMember;
import com.learnreactive.reactive.assignments.assignment10.SlackRoom;
import com.learnreactive.reactive.common.Util;

/*
 * Copyright (c) 2026 Ramjee Prasad
 * Licensed under a custom Non-Commercial, Attribution, Share-Alike License.
 * See the LICENSE file in the project root for full license information.
 *
 * Project: reactive
 * Package: com.learnreactive.reactive.sec12
 * Created by: Ashish Kushwaha on 28-01-2026 22:46
 * File: Lec08SlackAssignment
 *
 * This source code is intended for educational and non-commercial purposes only.
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *   - Attribution must be given to the original author.
 *   - The code must be shared under the same license.
 *   - Commercial use is strictly prohibited.
 *
 */
public class Lec08SlackAssignment {
    static void main() {
        var room = new SlackRoom("room1");
        var ramjee = new SlackMember("Ramjee");
        var ashish = new SlackMember("Ashish");
        var kushwaha = new SlackMember("Kushwaha");

        // add two members to the room
        room.addMember(ramjee);
        room.addMember(ashish);

        ramjee.says("Hello everyone!");
        Util.sleep(4);

        ashish.says("Hay!");
        ramjee.says("I simply wanted to say hi");
        Util.sleep(4);

        room.addMember(kushwaha);
        kushwaha.says("Hi everyone!");
    }
}
