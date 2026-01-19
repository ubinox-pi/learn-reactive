package com.learnreactive.reactive.sec09.helper;

import com.learnreactive.reactive.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

/*
 * Copyright (c) 2026 Ramjee Prasad
 * Licensed under a custom Non-Commercial, Attribution, Share-Alike License.
 * See the LICENSE file in the project root for full license information.
 *
 * Project: reactive
 * Package: com.learnreactive.reactive.sec09.helper
 * Created by: Ashish Kushwaha on 13-01-2026 18:13
 * File: NameGenerator
 *
 * This source code is intended for educational and non-commercial purposes only.
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *   - Attribution must be given to the original author.
 *   - The code must be shared under the same license.
 *   - Commercial use is strictly prohibited.
 *
 */
public class NameGenerator {

    private static final Logger log = LoggerFactory.getLogger(NameGenerator.class);
    private static final List<String> redis = new ArrayList<>();

    public Flux<String> generateNames() {
        return Flux.generate(sink -> {
            log.info("Emitting name");
            Util.sleep(1);
            var name = Util.getFaker().name().fullName();
            redis.add(name);
            sink.next(name);
        }).startWith(redis).cast(String.class);
    }

}
