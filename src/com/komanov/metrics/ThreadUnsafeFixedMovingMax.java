package com.komanov.metrics;

import com.codahale.metrics.Clock;
import scala.concurrent.duration.FiniteDuration;

import java.util.concurrent.TimeUnit;

import static com.komanov.metrics.FixedMovingMaxUtils.*;

public class ThreadUnsafeFixedMovingMax implements FixedMovingMax {
    private final Clock clock;
    private final long resolutionNanos;
    private final long[] measurements;
    private volatile long lastUpdateTick = 0L;

    public ThreadUnsafeFixedMovingMax(Clock clock, FiniteDuration window, FiniteDuration resolution) {
        this(clock, window.length(), window.unit(), resolution.length(), resolution.unit());
    }

    public ThreadUnsafeFixedMovingMax(Clock clock,
                                      long window,
                                      TimeUnit windowUnit,
                                      long resolution,
                                      TimeUnit resolutionUnit) {
        this.clock = clock;
        this.resolutionNanos = resolutionUnit.toNanos(resolution);

        int buckets = validateAndGetBuckets(windowUnit.toNanos(window), resolutionNanos);
        measurements = new long[buckets];
    }

    @Override
    public void update(long value) {
        long now = clock.getTick();
        long tick = lastUpdateTick;
        int length = measurements.length;
        int cell = getCellIndexByTick(now, resolutionNanos, length);
        int toDiscardCount = getDiscardCount(now, tick, resolutionNanos);
        discardBuckets(cell, toDiscardCount, length, i -> measurements[i] = 0L);

        long currentValue = measurements[cell];
        if (currentValue < value) {
            measurements[cell] = value;
        }
        lastUpdateTick = now;
    }

    @Override
    public long getMaxInWindow() {
        long now = clock.getTick();
        long tick = lastUpdateTick;
        int length = measurements.length;
        int cell = getCellIndexByTick(now, resolutionNanos, length);
        int toDiscardCount = getDiscardCount(now, tick, resolutionNanos);
        return findMaxInBuckets(cell, toDiscardCount, length, i -> measurements[i]);
    }

    @Override
    public long[] getMeasurementsForTests() {
        return java.util.Arrays.copyOf(measurements, measurements.length);
    }
}
