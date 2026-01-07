package com.learnreactive.reactive.sec04.helper;

import com.learnreactive.reactive.common.Util;
import reactor.core.publisher.FluxSink;

import java.util.function.Consumer;

/*
 * Copyright (c) 2025 Ramjee Prasad
 * Licensed under a custom Non-Commercial, Attribution, Share-Alike License.
 * See the LICENSE file in the project root for full license information.
 *
 * Project: reactive
 * Package: com.learnreactive.reactive.sec04.helper
 * Created by: Ashish Kushwaha on 01-11-2025 10:47
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
public class NameGenerator implements Consumer<FluxSink<String>> {

    private FluxSink<String> sink;

    @Override
    public void accept(FluxSink<String> stringFluxSink) {
        this.sink = stringFluxSink;
    }

    public void generateNames() {
        this.sink.next(Util.getFaker().name().fullName());
    }
}
