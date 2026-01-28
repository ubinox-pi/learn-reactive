<p align="center">
  <img src="https://img.shields.io/badge/Project%20Reactor-3.5+-green?style=for-the-badge&logo=spring&logoColor=white" alt="Project Reactor"/>
  <img src="https://img.shields.io/badge/Java-25-orange?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java 25"/>
  <img src="https://img.shields.io/badge/Spring%20Boot-3.5.6-brightgreen?style=for-the-badge&logo=springboot&logoColor=white" alt="Spring Boot"/>
  <img src="https://img.shields.io/badge/License-Non--Commercial-blue?style=for-the-badge" alt="License"/>
</p>

<h1 align="center">
  🚀 Learn Reactive Programming with Project Reactor
</h1>

<p align="center">
  <strong>A comprehensive, hands-on guide to mastering reactive programming in Java</strong>
</p>

<p align="center">
  <a href="#-quick-start">Quick Start</a> •
  <a href="#-sections-overview">Sections</a> •
  <a href="#-assignments">Assignments</a> •
  <a href="#-project-structure">Structure</a> •
  <a href="#-contributing">Contributing</a>
</p>

---

## 📖 About This Project

This repository is a **complete learning resource** for reactive programming using **Project Reactor**. It covers everything from the basics of the Reactive Streams specification to advanced concepts like backpressure handling, schedulers, and combining publishers.

---

## ⚡ Quick Start

### Prerequisites

- **Java 25+** (with virtual threads support)
- **Maven 3.8+**
- **External Service** running on `localhost:8080` (for HTTP client demos)

### Installation

```bash
# Clone the repository
git clone git@github.com:ubinox-pi/learn-reactive.git

# Navigate to project directory
cd reactive

# Build the project
./mvnw clean install

# Run any demo (example)
./mvnw exec:java -Dexec.mainClass="com.learnreactive.reactive.sec02.Lec02MonoJust"
```

---

## 🗂️ Project Structure

```
📦 reactive
├── 📂 src/main/java/com/learnreactive/reactive
│   ├── 📂 common           # Utility classes & reusable components
│   ├── 📂 sec01             # Section 01: Reactive Streams Basics
│   ├── 📂 sec02             # Section 02: Mono - Single Value Publisher
│   ├── 📂 sec03             # Section 03: Flux - Multiple Values Publisher
│   ├── 📂 sec04             # Section 04: Flux Create & Generate
│   ├── 📂 sec05             # Section 05: Operators Deep Dive
│   ├── 📂 sec06             # Section 06: Hot vs Cold Publishers
│   ├── 📂 sec07             # Section 07: Schedulers & Threading
│   ├── 📂 sec08             # Section 08: Backpressure Handling
│   ├── 📂 sec09             # Section 09: Combining Publishers
│   ├── 📂 sec10             # Section 10: Batching & Grouping
│   ├── 📂 sec11             # Section 11: Repeat & Retry Patterns
│   └── 📂 assignments       # Hands-on Assignments
└── 📂 src/main/resources    # Configuration & resource files
```

---

## 📚 Sections Overview

<details>
<summary><h3>📗 Section 01: Reactive Streams Fundamentals</h3></summary>

> **Learn the core concepts of the Reactive Streams specification**

This section implements the Reactive Streams interfaces from scratch to understand the fundamentals.

#### 📁 Files

| File                              | Description                                   |
|-----------------------------------|-----------------------------------------------|
| `publisher/PublisherImpl.java`    | Custom Publisher implementation               |
| `publisher/SubscriptionImpl.java` | Subscription with backpressure & cancellation |
| `subscriber/SubscriberImpl.java`  | Custom Subscriber with lifecycle hooks        |
| `Main.java`                       | Demo: Publisher-Subscriber interaction        |

#### 🔑 Key Concepts

- **Publisher**: Produces data items
- **Subscriber**: Consumes data items
- **Subscription**: Controls data flow between publisher and subscriber
- **Backpressure**: Subscriber requests only what it can handle

#### 💻 Example Code

```java
// Publisher will not produce data unless the subscriber requests it
Publisher<String> publisher = new PublisherImpl();
SubscriberImpl subscriber = new SubscriberImpl();
publisher.subscribe(subscriber);

// Request data in batches
subscriber.getSubscription().request(3);  // Request 3 items
Thread.sleep(2000);
subscriber.getSubscription().request(3);  // Request 3 more items
```

</details>

---

<details>
<summary><h3>📗 Section 02: Mono - The Single Value Publisher</h3></summary>

> **Master Mono for handling single or no values reactively**

#### 📁 Files

| File                                   | Description                          |
|----------------------------------------|--------------------------------------|
| `Lec01LazyStream.java`                 | Understanding lazy evaluation        |
| `Lec02MonoJust.java`                   | Creating Mono with `just()`          |
| `Lec03MonoSubscribe.java`              | Different subscribe methods          |
| `Lec04MonoEmptyError.java`             | Handling empty and error signals     |
| `Lec05MonoFromSupplier.java`           | Lazy Mono creation with Supplier     |
| `Lec05MonoFromCallable.java`           | Mono from Callable (with exceptions) |
| `Lec07MonoFromRunnable.java`           | Mono for side effects                |
| `Lec08MonoFromFuture.java`             | Converting CompletableFuture to Mono |
| `Lec09PublisherCreateVsExecution.java` | Publisher creation vs execution      |
| `Lec10MonoDefer.java`                  | Deferred Mono creation               |
| `Lec11NonBlockingIO.java`              | Non-blocking I/O with Mono           |
| `Lec12Assignment.java`                 | File service assignment              |

#### 🔑 Key Concepts

- `Mono.just()` - Wrap an existing value
- `Mono.fromSupplier()` - Lazy value computation
- `Mono.fromCallable()` - Handle checked exceptions
- `Mono.fromRunnable()` - Execute side effects
- `Mono.fromFuture()` - Bridge with CompletableFuture
- `Mono.defer()` - Defer publisher creation until subscription

#### 💻 Example Code

```java
// Mono.just - immediate value
Mono<String> mono = Mono.just("Hello World");

// Mono.fromSupplier - lazy evaluation
Mono.fromSupplier(() -> expensiveComputation())
    .subscribe(result -> log.info("Result: {}", result));

// Mono.fromCallable - handles checked exceptions
Mono.fromCallable(() -> Files.readString(path))
    .subscribe(content -> log.info("Content: {}", content));

// Mono.defer - defers publisher creation
Mono.defer(() -> createPublisher())
    .subscribe(Util.subscriber("sub1"));
```

</details>

---

<details>
<summary><h3>📗 Section 03: Flux - Multiple Values Publisher</h3></summary>

> **Handle streams of multiple values with Flux**

#### 📁 Files

| File                                     | Description                        |
|------------------------------------------|------------------------------------|
| `Lec01FluxJust.java`                     | Creating Flux with multiple values |
| `Lec02MultipleSubscribers.java`          | Multiple subscribers to same Flux  |
| `Lec03FluxFromIterableOrArray.java`      | Flux from collections and arrays   |
| `Lec04FluxFromStream.java`               | Flux from Java Streams             |
| `Lec05FluxRange.java`                    | Generate range of integers         |
| `Lec06Log.java`                          | Debugging with log operator        |
| `Lec07FluxVsList.java`                   | Flux vs traditional List           |
| `Lec08NonBlockingStreamingMessages.java` | Non-blocking streaming             |
| `Lec09FluxInterval.java`                 | Time-based emission                |
| `Lec10FluxEmptyError.java`               | Empty and error Flux               |
| `Lec11FluxMono.java`                     | Converting between Flux and Mono   |

#### 🔑 Key Concepts

- `Flux.just()` - Create from known values
- `Flux.fromIterable()` - Create from collections
- `Flux.range()` - Generate integer sequences
- `Flux.interval()` - Time-based emissions
- Multiple subscribers receive independent streams

#### 💻 Example Code

```java
// Create Flux from values
Flux.just("Ashish", "Ramjee", "Prasad")
    .subscribe(Util.subscriber("sub1"));

// Create Flux from collection
List<Character> list = List.of('a', 'b', 'c', 'd', 'e', 'f');
Flux.fromIterable(list)
    .subscribe(Util.subscriber("sub1"));

// Generate range
Flux.range(1, 10)
    .subscribe(Util.subscriber("sub1"));

// Time-based emissions
Flux.interval(Duration.ofMillis(500))
    .map(i -> Util.getFaker().name().firstName())
    .subscribe(Util.subscriber("sub1"));
```

</details>

---

<details>
<summary><h3>📗 Section 04: Flux Create & Generate</h3></summary>

> **Programmatically create Flux with full control**

#### 📁 Files

| File                                   | Description                      |
|----------------------------------------|----------------------------------|
| `Lec01FluxCreate.java`                 | Basic Flux.create() usage        |
| `Lec02FluxCreateRefactor.java`         | Refactored with Consumer pattern |
| `Lec03FluxSinkThreadSafety.java`       | Thread-safe FluxSink             |
| `Lec04FluxCreateDownstreamDemand.java` | Handling downstream demand       |
| `Lec05TakeOperator.java`               | take, takeWhile, takeUntil       |
| `Lec06FluxGenerate.java`               | Synchronous generation           |
| `Lec07GenerateWithState.java`          | Stateful generation              |
| `Lec07GenerateWithState2.java`         | Advanced stateful patterns       |

#### 🔑 Key Concepts

- `Flux.create()` - Async, multi-threaded, no backpressure
- `Flux.generate()` - Sync, single-threaded, backpressure aware
- `FluxSink` - Thread-safe for multiple producers
- `SynchronousSink` - One item per generate call

#### 💻 Example Code

```java
// Flux.create - emit until condition met
Flux.create(fluxSink -> {
    while (true) {
        String country = Util.getFaker().country().name();
        fluxSink.next(country);
        if (country.equalsIgnoreCase("INDIA")) {
            fluxSink.complete();
            break;
        }
    }
}).subscribe(Util.subscriber("sub1"));

// Flux.generate - synchronous, one item at a time
Flux.generate(sink -> {
    sink.next(Util.getFaker().country().name());
}).take(5).subscribe(Util.subscriber("sub1"));

// Flux.generate with state
Flux.generate(
    () -> 0,  // Initial state
    (counter, sink) -> {
        sink.next(Util.getFaker().country().name());
        if (++counter == 10) sink.complete();
        return counter;
    }
).subscribe(Util.subscriber("sub1"));
```

</details>

---

<details>
<summary><h3>📗 Section 05: Operators Deep Dive</h3></summary>

> **Master the most important reactive operators**

#### 📁 Files

| File                       | Description                                 |
|----------------------------|---------------------------------------------|
| `Lec01Handle.java`         | Filter + Map combined                       |
| `Lec03DoCallBacks.java`    | Lifecycle hooks (doOnNext, doOnError, etc.) |
| `Lec04Delay.java`          | Delay emissions                             |
| `Lec05Subscribe.java`      | Subscribe variants                          |
| `Lec06ErrorHandling.java`  | Error recovery strategies                   |
| `Lec07DefaultIfEmpty.java` | Default value for empty streams             |
| `Lec08SwitchIfEmpty.java`  | Switch to fallback publisher                |
| `Lec09Timeout.java`        | Timeout handling                            |
| `Lec10Transform.java`      | Reusable operator chains                    |

#### 🔑 Key Concepts

- `handle()` - Combined filter + map with control
- `doOnXxx()` - Side effects without modifying stream
- `onErrorReturn()` - Return default on error
- `onErrorResume()` - Switch to fallback publisher
- `defaultIfEmpty()` - Default for empty stream
- `switchIfEmpty()` - Fallback publisher for empty stream
- `timeout()` - Error on timeout
- `transform()` - Reusable operator pipelines

#### 💻 Example Code

```java
// Handle - combined filter + map
Flux.range(1, 10)
    .handle((item, sink) -> {
        switch (item) {
            case 1 -> sink.next(-2);      // Transform
            case 4 -> {}                   // Filter out
            case 7 -> sink.error(new RuntimeException("7 not allowed"));
            default -> sink.next(item);    // Pass through
        }
    })
    .subscribe(Util.subscriber("sub1"));

// Error handling
Flux.range(1, 10)
    .map(i -> i == 5 ? 5 / 0 : i)
    .onErrorContinue((error, obj) -> log.info("Error: {} for {}", error.getMessage(), obj))
    .subscribe(Util.subscriber("sub1"));

// Timeout with fallback
getProductName()
    .timeout(Duration.ofSeconds(1), fallbackMono())
    .subscribe(Util.subscriber("sub1"));
```

</details>

---

<details>
<summary><h3>📗 Section 06: Hot vs Cold Publishers</h3></summary>

> **Understand the difference between hot and cold publishers**

#### 📁 Files

| File                                             | Description                                   |
|--------------------------------------------------|-----------------------------------------------|
| `Lec01ColdPublisher.java`                        | Cold publisher behavior                       |
| `Lec02HotPublisher.java`                         | Hot publisher with publish().refCount()       |
| `Lec03HotPublisherAutoConnect.java`              | AutoConnect for hot publishers                |
| `Lec04HotPublisherCache.java`                    | Caching with replay()                         |
| `Lec05FluxCreateRefactorIssueFixLec02Sec04.java` | Fix for Flux.create with multiple subscribers |

#### 🔑 Key Concepts

- **Cold Publisher**: Each subscriber gets all data from the beginning
- **Hot Publisher**: Subscribers receive data from subscription point onward
- `publish().refCount(n)` - Start when n subscribers, stop when all leave
- `publish().autoConnect(n)` - Start when n subscribers, never stop
- `replay(n).autoConnect(0)` - Cache last n items for late subscribers

#### 💻 Example Code

```java
// Cold Publisher - each subscriber gets fresh data
var coldFlux = Flux.create(sink -> {
    for (int i = 0; i < 10; i++) {
        sink.next(i);
    }
    sink.complete();
});
coldFlux.subscribe(Util.subscriber("sub1")); // Gets 0-9
coldFlux.subscribe(Util.subscriber("sub2")); // Also gets 0-9

// Hot Publisher - shared stream
var hotFlux = movieStream().publish().refCount(1);
hotFlux.subscribe(Util.subscriber("Ashish"));  // Starts watching
Util.sleep(3);
hotFlux.subscribe(Util.subscriber("Ramjee"));  // Joins mid-movie

// Hot Publisher with cache
var cachedFlux = stockPriceStream().replay(10).autoConnect(0);
// Late subscribers get last 10 items plus new ones
```

</details>

---

<details>
<summary><h3>📗 Section 07: Schedulers & Threading</h3></summary>

> **Control thread execution in reactive pipelines**

#### 📁 Files

| File                             | Description                |
|----------------------------------|----------------------------|
| `Lec01DefaultBehaviourDemo.java` | Default threading behavior |
| `Lec02SubscribeOn.java`          | Change upstream thread     |
| `Lec03MultipleSubscribeOn.java`  | Multiple subscribeOn calls |
| `Lec04VirtualThreads.java`       | Java 21+ virtual threads   |
| `Lec04PublishOn.java`            | Change downstream thread   |
| `Lec06EventLoopIssueFix.java`    | Event loop considerations  |
| `Lec07PublishOnSubscribeOn.java` | Combined usage             |
| `Lec08Parallel.java`             | Parallel processing        |

#### 🔑 Key Concepts

- `subscribeOn()` - Affects upstream (producer) thread
- `publishOn()` - Affects downstream (consumer) thread
- `Schedulers.boundedElastic()` - For blocking I/O
- `Schedulers.parallel()` - For CPU-intensive tasks
- `parallel().runOn()` - True parallel execution
- Virtual threads support in Java 21+

#### 💻 Example Code

```java
// subscribeOn - changes producer thread
Flux.create(sink -> {
    log.info("Producing on: {}", Thread.currentThread().getName());
    sink.next(1);
    sink.complete();
})
.subscribeOn(Schedulers.boundedElastic())
.subscribe(i -> log.info("Received: {}", i));

// publishOn - changes consumer thread
Flux.range(1, 3)
    .doOnNext(i -> log.info("Produced: {}", i))
    .publishOn(Schedulers.parallel())
    .subscribe(i -> log.info("Consumed: {}", i));

// Parallel processing
Flux.range(1, 10)
    .parallel(4)
    .runOn(Schedulers.parallel())
    .map(this::process)
    .sequential()
    .subscribe(Util.subscriber("sub1"));
```

</details>

---

<details>
<summary><h3>📗 Section 08: Backpressure Handling</h3></summary>

> **Manage flow control between fast producers and slow consumers**

#### 📁 Files

| File                               | Description                               |
|------------------------------------|-------------------------------------------|
| `Lec01BackPressureHandling.java`   | Default backpressure behavior             |
| `Lec02LimitRate.java`              | Rate limiting                             |
| `Lec03MultipleSubscriber.java`     | Multiple subscribers with different rates |
| `Lec04FluxCreate.java`             | Backpressure with Flux.create             |
| `Lec05BackpressureStrategies.java` | Different overflow strategies             |

#### 🔑 Key Concepts

- Reactor automatically handles backpressure
- `limitRate(n)` - Request n items at a time
- **Overflow Strategies**:
    - `BUFFER` - Buffer all items (default)
    - `DROP` - Drop items that can't be processed
    - `LATEST` - Keep only the latest item
    - `ERROR` - Signal error on overflow

#### 💻 Example Code

```java
// Limit rate - request in batches
producer
    .limitRate(5)  // Request 5 items at a time
    .subscribe(Util.subscriber("sub1"));

// Backpressure strategies
Flux.create(sink -> {
    for (int i = 0; i < 500; i++) {
        sink.next(i);
        Util.sleep(Duration.ofMillis(50));
    }
    sink.complete();
}, FluxSink.OverflowStrategy.DROP)  // Drop overflow
.subscribe(Util.subscriber("sub1"));

// Alternative strategies
.onBackpressureBuffer()      // Buffer (default)
.onBackpressureBuffer(10)    // Buffer with limit
.onBackpressureDrop()        // Drop excess
.onBackpressureLatest()      // Keep latest only
.onBackpressureError()       // Error on overflow
```

</details>

---

<details>
<summary><h3>📗 Section 09: Combining Publishers</h3></summary>

> **Combine multiple reactive streams effectively**

#### 📁 Files

| File                            | Description                    |
|---------------------------------|--------------------------------|
| `Lec01StartWith.java`           | Prepend items to stream        |
| `Lec02StartWithUseCase.java`    | Use case: cache fallback       |
| `Lec03ConcatWith.java`          | Sequential concatenation       |
| `Lec04ConcatError.java`         | Error handling in concat       |
| `Lec05Merge.java`               | Interleaved merging            |
| `Lec06MerceUseCase.java`        | Use case: flight search        |
| `Lec07Zip.java`                 | Combine items pairwise         |
| `Lec09MonoFlatMap.java`         | Mono sequential calls          |
| `Lec10MonoFlatMapMany.java`     | Mono to Flux transformation    |
| `Lec11FluxFlatMap.java`         | Flux parallel calls            |
| `Lec13ConcatMap.java`           | Sequential flatMap             |
| `Lec14CollectListOperator.java` | Collect to List                |
| `Lec15Then.java`                | Ignore results, chain tasks    |
| `Lec16Assignment.java`          | Combining operators assignment |

#### 🔑 Key Concepts

- `startWith()` - Prepend items/publishers
- `concatWith()` - Append sequentially (waits for completion)
- `mergeWith()` - Interleave (parallel subscription)
- `zip()` - Combine items pairwise (waits for all)
- `flatMap()` - Parallel inner publishers
- `concatMap()` - Sequential inner publishers
- `then()` - Execute after completion (ignores items)

#### 💻 Example Code

```java
// startWith - prepend items
producer1()
    .startWith(-1, 0)  // Prepend values
    .startWith(producer2())  // Prepend another publisher
    .subscribe(Util.subscriber("sub1"));

// concat - sequential (ordered)
Flux.concat(producer1(), producer2())
    .subscribe(Util.subscriber("sub1"));

// merge - parallel (interleaved)
Flux.merge(producer1(), producer2(), producer3())
    .subscribe(Util.subscriber("sub1"));

// zip - combine pairwise
Flux.zip(getBody(), getEngine(), getTires())
    .map(tuple -> new Car(tuple.getT1(), tuple.getT2(), tuple.getT3()))
    .subscribe(Util.subscriber("sub1"));

// flatMap - parallel inner publishers
UserService.getAllUser()
    .flatMap(user -> OrderService.getUserOrder(user.id()))
    .subscribe(Util.subscriber("sub1"));

// concatMap - sequential inner publishers (ordered)
userFlux.concatMap(user -> getOrdersFor(user))
    .subscribe(Util.subscriber("sub1"));
```

</details>

---

<details>
<summary><h3>📗 Section 10: Batching & Grouping</h3></summary>

> **Process items in batches and groups**

#### 📁 Files

| File                          | Description                     |
|-------------------------------|---------------------------------|
| `Lec01Buffer.java`            | Collect items into List batches |
| `Lec02BufferAssignment.java`  | Buffer assignment               |
| `Lec03Window.java`            | Collect items into Flux windows |
| `Lec04WindowAssignment.java`  | Window assignment               |
| `Lec05GroupedFlux.java`       | Group by key                    |
| `Lec06GroupByAssignment.java` | GroupBy assignment              |

#### 🔑 Key Concepts

- `buffer()` - Collect into `List<T>`
- `bufferTimeout()` - Buffer by count OR time
- `window()` - Collect into `Flux<T>` (for processing)
- `groupBy()` - Split by key into `GroupedFlux`

#### 💻 Example Code

```java
// Buffer - collect into lists
eventStream()
    .buffer(3)  // Emit List of 3 items
    .subscribe(Util.subscriber("sub1"));

// Buffer with timeout
eventStream()
    .bufferTimeout(3, Duration.ofSeconds(1))  // 3 items OR 1 second
    .subscribe(Util.subscriber("sub1"));

// Window - collect into Flux for processing
eventStream()
    .window(5)
    .flatMap(this::processEventBatch)
    .subscribe();

// GroupBy - split by key
Flux.range(1, 30)
    .groupBy(i -> i % 2)  // Even/odd groups
    .flatMap(groupedFlux ->
        groupedFlux.doOnNext(i -> log.info("Group {}: {}", groupedFlux.key(), i)).then()
    )
    .subscribe();
```

</details>

---

<details>
<summary><h3>📗 Section 11: Repeat & Retry Patterns</h3></summary>

> **Handle retries and repeating operations**

#### 📁 Files

| File                                | Description            |
|-------------------------------------|------------------------|
| `Lec01Repeat.java`                  | Repeat on completion   |
| `Lec02Retry.java`                   | Retry on error         |
| `Lec03DemoJar.java`                 | External service demo  |
| `client/ExternalServiceClient.java` | HTTP client with retry |
| `client/ClientError.java`           | Client-side errors     |
| `client/ServerError.java`           | Server-side errors     |

#### 🔑 Key Concepts

- `repeat()` - Re-subscribe on completion
- `repeat(n)` - Repeat n times
- `repeatWhen()` - Conditional repeat with delay
- `retry()` - Re-subscribe on error
- `retry(n)` - Retry n times
- `retryWhen()` - Advanced retry with backoff

#### 💻 Example Code

```java
// Repeat - re-subscribe on completion
getCountry()
    .repeat(2)  // Original + 2 repeats
    .subscribe(Util.subscriber("sub1"));

// Repeat until condition
getCountry()
    .repeat()
    .takeUntil(c -> c.equalsIgnoreCase("canada"))
    .subscribe(Util.subscriber("sub1"));

// Repeat with delay
getCountry()
    .repeatWhen(flux -> flux.delayElements(Duration.ofSeconds(2)).take(2))
    .subscribe(Util.subscriber("sub1"));

// Retry - re-subscribe on error
getCountry()
    .retry(3)  // Retry up to 3 times
    .subscribe(Util.subscriber("sub1"));

// Retry with backoff and filtering
getCountry()
    .retryWhen(
        Retry.fixedDelay(3, Duration.ofSeconds(1))
            .filter(ex -> RuntimeException.class.equals(ex.getClass()))
            .doBeforeRetry(signal -> log.info("Retrying..."))
    )
    .subscribe(Util.subscriber("sub1"));
```

</details>

---

## 📝 Assignments

| #  | Assignment                                                                                 | Section | Description                                             |
|----|--------------------------------------------------------------------------------------------|---------|---------------------------------------------------------|
| 01 | [FileService](src/main/java/com/learnreactive/reactive/assignments/assignment01)           | Sec 02  | Reactive file operations (read/write/delete) using Mono |
| 02 | [GenerateFakeNames](src/main/java/com/learnreactive/reactive/assignments/assignment02)     | Sec 03  | Generate 10 fake names using Flux.range()               |
| 03 | [StockPriceObserver](src/main/java/com/learnreactive/reactive/assignments/assignment03)    | Sec 03  | Stock trading simulation with reactive streams          |
| 04 | [FluxGenerateUntil](src/main/java/com/learnreactive/reactive/assignments/assignment04)     | Sec 04  | Generate countries until "INDIA" is found               |
| 05 | [FileReaderService](src/main/java/com/learnreactive/reactive/assignments/assignment05)     | Sec 04  | Reactive file reader with Flux.generate()               |
| 06 | [HandleUtilAssignment](src/main/java/com/learnreactive/reactive/assignments/assignment06)  | Sec 05  | Using handle() operator for filtering                   |
| 07 | [ExternalServiceClient](src/main/java/com/learnreactive/reactive/assignments/assignment07) | Sec 05  | Timeout and transform operators                         |
| 08 | [OrderProcessor](src/main/java/com/learnreactive/reactive/assignments/assignment08)        | Sec 06  | Hot publisher for order processing                      |
| 09 | [ProductDetails](src/main/java/com/learnreactive/reactive/assignments/assignment09)        | Sec 09  | Combining publishers with zip()                         |

---

## 🛠️ Common Utilities

### DefaultSubscriber

A reusable subscriber implementation for learning and debugging:

```java
// Usage
Mono.just("Hello")
    .subscribe(Util.subscriber("MySubscriber"));
```

### AbstractHttpClient

Base HTTP client using Reactor Netty:

```java
public class ExternalServiceClient extends AbstractHttpClient {
    public Mono<String> getProductName(int productId) {
        return this.httpClient.get()
                .uri("/demo01/product/" + productId)
                .responseContent()
                .asString()
                .next();
    }
}
```

### Util Class

Helpful utilities for demos:

```java
Util.subscriber("name")     // Create a subscriber
Util.getFaker()             // Get Faker instance
Util.sleep(seconds)         // Thread sleep
Util.fluxLogger("name")     // Add logging to Flux
```

---

## 📦 Dependencies

| Dependency           | Version  | Purpose               |
|----------------------|----------|-----------------------|
| `reactor-core`       | 2024.0.1 | Core reactive library |
| `reactor-netty-http` | -        | HTTP client           |
| `reactor-test`       | -        | Testing utilities     |
| `logback-classic`    | 1.5.12   | Logging               |
| `javafaker`          | 1.0.2    | Fake data generation  |
| `junit-jupiter`      | 5.11.3   | Testing               |

---

## 🎯 Learning Path

```
┌─────────────────────────────────────────────────────────────────┐
│  1. Reactive Streams Basics (sec01)                             │
│     └── Understand Publisher, Subscriber, Subscription          │
├─────────────────────────────────────────────────────────────────┤
│  2. Mono (sec02) → 3. Flux (sec03)                              │
│     └── Single vs Multiple value publishers                     │
├─────────────────────────────────────────────────────────────────┤
│  4. Create & Generate (sec04)                                   │
│     └── Programmatic stream creation                            │
├─────────────────────────────────────────────────────────────────┤
│  5. Operators (sec05)                                           │
│     └── Transform, filter, error handling                       │
├─────────────────────────────────────────────────────────────────┤
│  6. Hot vs Cold (sec06)                                         │
│     └── Publisher behavior with multiple subscribers            │
├─────────────────────────────────────────────────────────────────┤
│  7. Schedulers (sec07) → 8. Backpressure (sec08)                │
│     └── Threading and flow control                              │
├─────────────────────────────────────────────────────────────────┤
│  9. Combining (sec09) → 10. Batching (sec10)                    │
│     └── Merge, zip, concat, buffer, window                      │
├─────────────────────────────────────────────────────────────────┤
│  11. Repeat & Retry (sec11)                                     │
│     └── Resilience patterns                                     │
└─────────────────────────────────────────────────────────────────┘
```

---

## 📄 License

This project is licensed under a **Non-Commercial, Attribution, Share-Alike License**.

- **Attribution** must be given to the original author
- Code must be shared under the same license
- **Commercial use is strictly prohibited**

---

## 👤 Author

**Ramjee Prasad**

---

<p align="center">
  <strong>⭐ Star this repository if you find it helpful!</strong>
</p>

<p align="center">
  Made with ❤️ for the reactive programming community
</p>
