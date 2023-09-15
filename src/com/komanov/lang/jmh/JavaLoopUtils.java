package com.komanov.lang.jmh;

import org.openjdk.jmh.infra.Blackhole;

public class JavaLoopUtils {
    public static long javaForwardLoop(int iterations, Blackhole b) {
        long r = 0;
        for (int i = 0; i < iterations; ++i) {
            r += i;
            b.consume(r);
        }
        return r;
    }

    public static long javaBackwardLoop(int iterations, Blackhole b) {
        long r = 0;
        for (int i = iterations - 1; i >= 0; --i) {
            r += i;
            b.consume(r);
        }
        return r;
    }
}
