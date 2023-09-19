package com.komanov.metrics;

import com.codahale.metrics.Clock;
import scala.concurrent.duration.FiniteDuration;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;

import static com.komanov.metrics.FixedMovingMaxUtils.*;

public class AtomicArrayFixedMovingMax implements FixedMovingMax {
    private final Clock clock;
    private final long resolutionNanos;
    private final AtomicLongArray measurements;
    private final AtomicLong lastUpdateTick = new AtomicLong(0);

    public AtomicArrayFixedMovingMax(Clock clock, FiniteDuration window, FiniteDuration resolution) {
        this(clock, window.length(), window.unit(), resolution.length(), resolution.unit());
    }

    public AtomicArrayFixedMovingMax(Clock clock,
                                     long window,
                                     TimeUnit windowUnit,
                                     long resolution,
                                     TimeUnit resolutionUnit) {
        this.clock = clock;
        this.resolutionNanos = resolutionUnit.toNanos(resolution);

        int buckets = validateAndGetBuckets(windowUnit.toNanos(window), resolutionNanos);
        measurements = new AtomicLongArray(buckets);
    }

    @Override
    public void update(long value) {
        long now = clock.getTick();
        long tick = lastUpdateTick.get();
        int length = measurements.length();
        int cell = getCellIndexByTick(now, resolutionNanos, length);
        int toDiscardCount = getDiscardCount(now, tick, resolutionNanos);
        if (toDiscardCount >= length) {
            for (int i = 0; i < length; ++i) {
                measurements.set(i, 0L);
            }
        } else if (toDiscardCount == 0) {
            // nothing to discard
        } else if ((cell + 1) - toDiscardCount >= 0) {
            int start = (cell + 1) - toDiscardCount;
            int len = Math.min(length, cell + 1);
            for (int i = start; i < len; ++i) {
                measurements.set(i, 0L);
            }
        } else {
            for (int i = 0; i <= cell; ++i) {
                measurements.set(i, 0L);
            }
            for (int i = length - (toDiscardCount - cell - 1); i < length; ++i) {
                measurements.set(i, 0L);
            }
        }
        measurements.set(cell, value);
        lastUpdateTick.set(now);
    }

    @Override
    public long getMaxInWindow() {
        long now = clock.getTick();
        long tick = lastUpdateTick.get();
        int length = measurements.length();
        int cell = getCellIndexByTick(now, resolutionNanos, length);
        int toDiscardCount = getDiscardCount(now, tick, resolutionNanos);

        long max = 0L;
        if (toDiscardCount >= length) {
            // nothing
        } else if (toDiscardCount == 0) {
            for (int i = 0; i < length; i++) {
                max = Math.max(max, measurements.get(i));
            }
        } else if (cell + 1 <= toDiscardCount) {
            int end = length - (toDiscardCount - cell - 1);
            for (int i = cell + 1; i < end; ++i) {
                max = Math.max(max, measurements.get(i));
            }
        } else {
            for (int i = 0; i <= cell - toDiscardCount; ++i) {
                max = Math.max(max, measurements.get(i));
            }
            for (int i = cell + 1; i < length; ++i) {
                max = Math.max(max, measurements.get(i));
            }
        }

        return max;
    }

    @Override
    public long[] getMeasurementsForTests() {
        return makeCopy(measurements.length(), measurements::getPlain);
    }
}
