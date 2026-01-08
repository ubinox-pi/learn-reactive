package com.learnreactive.reactive.assignments.assignment08;

import com.learnreactive.reactive.common.Util;

/*
 * Copyright (c) 2026 Ramjee Prasad
 * Licensed under a custom Non-Commercial, Attribution, Share-Alike License.
 * See the LICENSE file in the project root for full license information.
 *
 * Project: reactive
 * Package: com.learnreactive.reactive.assignments.assignment08
 * Created by: Ashish Kushwaha on 08-01-2026 19:00
 * File: Main
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
        var client = new ExternalServiceClient();
        var inventoryService = new InventoryService();
        var revenueService = new RevenueService();

        client.orderFlux()
                .subscribe(inventoryService::consume);

        client.orderFlux()
                .subscribe(revenueService::consume);

        inventoryService.stream()
                .subscribe(Util.subscriber("Inventory"));

        revenueService.stream()
                .subscribe(Util.subscriber("Revenue"));

        Util.sleep(30);
    }
}
