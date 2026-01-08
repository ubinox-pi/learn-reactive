package com.learnreactive.reactive.sec02;

import com.learnreactive.reactive.assignments.assignment01.FileService;
import com.learnreactive.reactive.assignments.assignment01.FileServiceImpl;
import com.learnreactive.reactive.common.Util;

/*
 * Copyright (c) 2025 Ramjee Prasad
 * Licensed under a custom Non-Commercial, Attribution, Share-Alike License.
 * See the LICENSE file in the project root for full license information.
 *
 * Project: reactive
 * Package: com.learnreactive.reactive.sec2
 * Created by: Ashish Kushwaha on 29-10-2025 18:23
 * File: Lec12Assignment
 *
 * This source code is intended for educational and non-commercial purposes only.
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *   - Attribution must be given to the original author.
 *   - The code must be shared under the same license.
 *   - Commercial use is strictly prohibited.
 *
 */
public class Lec12Assignment {

    static void main() {
        FileService fileService = new FileServiceImpl();

        fileService.writeFile("file.txt", "Hello World")
                .subscribe(Util.subscriber("Ashish"));

        fileService.readFile("file.txt")
                .subscribe(Util.subscriber("Ashish"));

        fileService.deleteFile("file.txt")
                .subscribe(Util.subscriber("Ashish"));
    }
}
