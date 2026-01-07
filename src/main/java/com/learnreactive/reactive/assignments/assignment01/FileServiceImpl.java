package com.learnreactive.reactive.assignments.assignment01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.nio.file.Files;
import java.nio.file.Path;

/*
 * Copyright (c) 2025 Ramjee Prasad
 * Licensed under a custom Non-Commercial, Attribution, Share-Alike License.
 * See the LICENSE file in the project root for full license information.
 *
 * Project: reactive
 * Package: com.learnreactive.reactive.assignments.assignment01
 * Created by: Ashish Kushwaha on 29-10-2025 18:11
 * File: FileServiceImpl
 *
 * This source code is intended for educational and non-commercial purposes only.
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *   - Attribution must be given to the original author.
 *   - The code must be shared under the same license.
 *   - Commercial use is strictly prohibited.
 *
 */
public class FileServiceImpl implements FileService {

    private static final Logger log = LoggerFactory.getLogger(FileServiceImpl.class);
    private static final Path FILE_PATH = Path.of("src", "main", "resources", "Sec02");

    @Override
    public Mono<String> readFile(String fileName) {
        return Mono.fromCallable(() -> Files.readString(FILE_PATH.resolve(fileName)));
    }

    @Override
    public Mono<Void> writeFile(String fileName, String content) {
        return Mono.fromRunnable(() -> write(fileName, content));
    }

    @Override
    public Mono<Boolean> deleteFile(String fileName) {
        return Mono.fromRunnable(() -> delete(fileName));
    }

    private void delete(String filename) {
        try {
            Files.delete(FILE_PATH.resolve(filename));
            log.info("File {} deleted successfully", filename);
        } catch (Exception e) {
            log.error("Error while deleting file {}", filename, e);
        }
    }

    private void write(String filename, String content) {
        try {
            Files.writeString(FILE_PATH.resolve(filename), content);
            log.info("File {} written successfully", filename);
        } catch (Exception e) {
            log.error("Error while writing file {}", filename, e);
        }
    }
}
