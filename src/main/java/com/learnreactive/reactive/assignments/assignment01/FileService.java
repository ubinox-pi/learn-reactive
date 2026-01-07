package com.learnreactive.reactive.assignments.assignment01;

import reactor.core.publisher.Mono;

/*
 * Copyright (c) 2025 Ramjee Prasad
 * Licensed under a custom Non-Commercial, Attribution, Share-Alike License.
 * See the LICENSE file in the project root for full license information.
 *
 * Project: reactive
 * Package: com.learnreactive.reactive.assignments.assignment01
 * Created by: Ashish Kushwaha on 29-10-2025 18:08
 * File: FileService
 *
 * This source code is intended for educational and non-commercial purposes only.
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *   - Attribution must be given to the original author.
 *   - The code must be shared under the same license.
 *   - Commercial use is strictly prohibited.
 *
 */
public interface FileService {

    Mono<String> readFile(String fileName);

    Mono<Void> writeFile(String fileName, String content);

    Mono<Boolean> deleteFile(String fileName);
}
