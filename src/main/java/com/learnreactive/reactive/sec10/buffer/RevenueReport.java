package com.learnreactive.reactive.sec10.buffer;

import java.time.LocalTime;
import java.util.Map;

/*
 * Copyright (c) 2026 Ramjee Prasad
 * Licensed under a custom Non-Commercial, Attribution, Share-Alike License.
 * See the LICENSE file in the project root for full license information.
 *
 * Project: reactive
 * Package: com.learnreactive.reactive.assignments.assignment
 * Created by: Ashish Kushwaha on 23-01-2026 22:16
 * File: RevenueReport
 *
 * This source code is intended for educational and non-commercial purposes only.
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *   - Attribution must be given to the original author.
 *   - The code must be shared under the same license.
 *   - Commercial use is strictly prohibited.
 *
 */
public record RevenueReport(
        LocalTime time,
        Map<String, Integer> revenue
) {
}
