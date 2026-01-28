package com.learnreactive.reactive.sec12;

/*
 * Copyright (c) 2026 Ramjee Prasad
 * Licensed under a custom Non-Commercial, Attribution, Share-Alike License.
 * See the LICENSE file in the project root for full license information.
 *
 * Project: reactive
 * Package: com.learnreactive.reactive.sec12
 * Created by: Ashish Kushwaha on 25-01-2026 19:59
 * File: Lec02SinkUnicast
 *
 * This source code is intended for educational and non-commercial purposes only.
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *   - Attribution must be given to the original author.
 *   - The code must be shared under the same license.
 *   - Commercial use is strictly prohibited.
 *
 */

import com.learnreactive.reactive.common.Util;
import reactor.core.publisher.Sinks;

/*
 * we can emit multiple messages, but there will be only one subscriber.
 * */
public class Lec02SinkUnicast {

    static void main() {
        demo2();
    }

    public static void demo1() {

        // handle through which we would push items
        // onBackPressureBuffer - unbounded queue
        var sink = Sinks.many().unicast().onBackpressureBuffer();

        // handle through which subscribers will receive items.
        var flux = sink.asFlux();

        sink.tryEmitNext("Hello");
        sink.tryEmitNext("Hii");
        sink.tryEmitNext("Hola");
        sink.tryEmitNext("Hey");

        flux.subscribe(Util.subscriber("Ramjee"));
    }

    public static void demo2() {
        var sink = Sinks.many().unicast().onBackpressureBuffer();

        var flux = sink.asFlux();

        sink.tryEmitNext("Hello");
        sink.tryEmitNext("Hii");
        sink.tryEmitNext("Hola");
        sink.tryEmitNext("Hey");

        flux.subscribe(Util.subscriber("Ashish"));
        flux.subscribe(Util.subscriber("Ramjee"));

//        C:\Users\ASUS\.jdks\graalvm-jdk-25\bin\java.exe "-Dvaadin.copilot.pluginDotFilePath=C:\Users\ASUS\Desktop\learn reactive\reactive\.idea\.copilot-plugin" "-javaagent:C:\Users\ASUS\AppData\Local\Programs\IntelliJ IDEA Ultimate\lib\idea_rt.jar=64346" -Dfile.encoding=UTF-8 -Dsun.stdout.encoding=UTF-8 -Dsun.stderr.encoding=UTF-8 -classpath "C:\Users\ASUS\Desktop\learn reactive\reactive\target\classes;C:\Users\ASUS\.m2\repository\io\projectreactor\reactor-core\3.7.1\reactor-core-3.7.1.jar;C:\Users\ASUS\.m2\repository\org\reactivestreams\reactive-streams\1.0.4\reactive-streams-1.0.4.jar;C:\Users\ASUS\.m2\repository\io\projectreactor\netty\reactor-netty-core\1.2.1\reactor-netty-core-1.2.1.jar;C:\Users\ASUS\.m2\repository\io\netty\netty-handler\4.1.127.Final\netty-handler-4.1.127.Final.jar;C:\Users\ASUS\.m2\repository\io\netty\netty-common\4.1.127.Final\netty-common-4.1.127.Final.jar;C:\Users\ASUS\.m2\repository\io\netty\netty-resolver\4.1.127.Final\netty-resolver-4.1.127.Final.jar;C:\Users\ASUS\.m2\repository\io\netty\netty-buffer\4.1.127.Final\netty-buffer-4.1.127.Final.jar;C:\Users\ASUS\.m2\repository\io\netty\netty-transport\4.1.127.Final\netty-transport-4.1.127.Final.jar;C:\Users\ASUS\.m2\repository\io\netty\netty-transport-native-unix-common\4.1.127.Final\netty-transport-native-unix-common-4.1.127.Final.jar;C:\Users\ASUS\.m2\repository\io\netty\netty-codec\4.1.127.Final\netty-codec-4.1.127.Final.jar;C:\Users\ASUS\.m2\repository\io\netty\netty-handler-proxy\4.1.127.Final\netty-handler-proxy-4.1.127.Final.jar;C:\Users\ASUS\.m2\repository\io\netty\netty-codec-socks\4.1.127.Final\netty-codec-socks-4.1.127.Final.jar;C:\Users\ASUS\.m2\repository\io\netty\netty-resolver-dns\4.1.127.Final\netty-resolver-dns-4.1.127.Final.jar;C:\Users\ASUS\.m2\repository\io\netty\netty-codec-dns\4.1.127.Final\netty-codec-dns-4.1.127.Final.jar;C:\Users\ASUS\.m2\repository\io\netty\netty-resolver-dns-native-macos\4.1.127.Final\netty-resolver-dns-native-macos-4.1.127.Final-osx-x86_64.jar;C:\Users\ASUS\.m2\repository\io\netty\netty-resolver-dns-classes-macos\4.1.127.Final\netty-resolver-dns-classes-macos-4.1.127.Final.jar;C:\Users\ASUS\.m2\repository\io\netty\netty-transport-native-epoll\4.1.127.Final\netty-transport-native-epoll-4.1.127.Final-linux-x86_64.jar;C:\Users\ASUS\.m2\repository\io\netty\netty-transport-classes-epoll\4.1.127.Final\netty-transport-classes-epoll-4.1.127.Final.jar;C:\Users\ASUS\.m2\repository\io\projectreactor\netty\reactor-netty-http\1.2.1\reactor-netty-http-1.2.1.jar;C:\Users\ASUS\.m2\repository\io\netty\netty-codec-http\4.1.127.Final\netty-codec-http-4.1.127.Final.jar;C:\Users\ASUS\.m2\repository\io\netty\netty-codec-http2\4.1.127.Final\netty-codec-http2-4.1.127.Final.jar;C:\Users\ASUS\.m2\repository\ch\qos\logback\logback-classic\1.5.12\logback-classic-1.5.12.jar;C:\Users\ASUS\.m2\repository\ch\qos\logback\logback-core\1.5.12\logback-core-1.5.12.jar;C:\Users\ASUS\.m2\repository\org\slf4j\slf4j-api\2.0.17\slf4j-api-2.0.17.jar;C:\Users\ASUS\.m2\repository\com\github\javafaker\javafaker\1.0.2\javafaker-1.0.2.jar;C:\Users\ASUS\.m2\repository\org\apache\commons\commons-lang3\3.17.0\commons-lang3-3.17.0.jar;C:\Users\ASUS\.m2\repository\org\yaml\snakeyaml\1.23\snakeyaml-1.23-android.jar;C:\Users\ASUS\.m2\repository\com\github\mifmif\generex\1.0.2\generex-1.0.2.jar;C:\Users\ASUS\.m2\repository\dk\brics\automaton\automaton\1.11-8\automaton-1.11-8.jar" com.learnreactive.reactive.sec12.Lec02SinkUnicast
//        20:10:27.726 INFO  [           main] c.l.r.common.DefaultSubscriber : Ashish Received: Hello
//        20:10:27.728 INFO  [           main] c.l.r.common.DefaultSubscriber : Ashish Received: Hii
//        20:10:27.728 INFO  [           main] c.l.r.common.DefaultSubscriber : Ashish Received: Hola
//        20:10:27.728 INFO  [           main] c.l.r.common.DefaultSubscriber : Ashish Received: Hey
//        20:10:27.731 ERROR [           main] c.l.r.common.DefaultSubscriber : Ramjee an error has occurred
//        java.lang.IllegalStateException: Sinks.many().unicast() sinks only allow a single Subscriber
//        at reactor.core.publisher.SinkManyUnicast.subscribe(SinkManyUnicast.java:426)
//        at reactor.core.publisher.Flux.subscribe(Flux.java:8891)
//        at com.learnreactive.reactive.sec12.Lec02SinkUnicast.demo2(Lec02SinkUnicast.java:62)
//        at com.learnreactive.reactive.sec12.Lec02SinkUnicast.main(Lec02SinkUnicast.java:31)

    }
}
