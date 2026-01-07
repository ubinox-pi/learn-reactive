package com.learnreactive.reactive.assignments.assignment05;

import com.learnreactive.reactive.common.Util;

import java.nio.file.Path;

/*
 * Copyright (c) 2025 Ramjee Prasad
 * Licensed under a custom Non-Commercial, Attribution, Share-Alike License.
 * See the LICENSE file in the project root for full license information.
 *
 * Project: reactive
 * Package: com.learnreactive.reactive.assignments.assignment05
 * Created by: Ashish Kushwaha on 12-11-2025 22:53
 * File: Msin
 *
 * This source code is intended for educational and non-commercial purposes only.
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *   - Attribution must be given to the original author.
 *   - The code must be shared under the same license.
 *   - Commercial use is strictly prohibited.
 *
 */
public class Main {
    static void main() {
        Path filePath = Path.of("C:\\Users\\ASUS\\Desktop\\learn reactive\\reactive\\src\\main\\java\\com\\learnreactive\\reactive\\assignments\\assignment05\\file.txt");
        FileReaderService fileReaderService = new FileReaderServiceImpl();
        fileReaderService.read(filePath)
                .subscribe(Util.subscriber("Ashish"));
    }
}
