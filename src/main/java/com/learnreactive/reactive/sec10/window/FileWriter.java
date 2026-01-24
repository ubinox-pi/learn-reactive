package com.learnreactive.reactive.sec10.window;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/*
 * Copyright (c) 2026 Ramjee Prasad
 * Licensed under a custom Non-Commercial, Attribution, Share-Alike License.
 * See the LICENSE file in the project root for full license information.
 *
 * Project: reactive
 * Package: com.learnreactive.reactive.sec10.window
 * Created by: Ashish Kushwaha on 23-01-2026 22:53
 * File: FileWriter
 *
 * This source code is intended for educational and non-commercial purposes only.
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *   - Attribution must be given to the original author.
 *   - The code must be shared under the same license.
 *   - Commercial use is strictly prohibited.
 *
 */
public class FileWriter {
    private final Path path;
    private BufferedWriter writer;

    private FileWriter(Path path) {
        this.path = path;
    }

    public static Mono<Void> create(Flux<String> flux, Path path) {
        var writer = new FileWriter(path);
        return flux.doOnNext(writer::write)
                .doFirst(writer::createFile)
                .doFinally(_ -> writer.closeFile())
                .then();

    }

    private void createFile() {
        try {
            Files.createDirectories(path.getParent());
            this.writer = Files.newBufferedWriter(
                    path,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void closeFile() {
        try {
            this.writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void write(String line) {
        try {
            this.writer.write(line);
            this.writer.newLine();
            this.writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
