package com.komanov.metrics;

import java.util.concurrent.atomic.AtomicLong;
import java.util.function.IntConsumer;
import java.util.function.IntToLongFunction;

public class FixedMovingMaxUtils {

    public static int getCellIndexByTick(long tick, long resolutionNanos, int buckets) {
        return (int) ((tick / resolutionNanos) % buckets);
    }

    public static int getDiscardCount(long now, long tick, long resolutionNanos) {
        long diff = Math.abs(now - tick); // abs protects from concurrent update, should be within resolution anyway
        return (int) (diff / resolutionNanos); // number of "previous" cells (resolution periods) to discard
    }

    public static int validateAndGetBuckets(long windowNanos, long resolutionNanos) {
        if (windowNanos <= 0L) {
            throw new IllegalArgumentException("Window can't be empty");
        }

        if (resolutionNanos <= 0L) {
            throw new IllegalArgumentException("Resolution can't be empty");
        }

        if (windowNanos % resolutionNanos != 0) {
            throw new IllegalArgumentException("window % resolution must be zero");
        }

        long buckets = windowNanos / resolutionNanos;
        if (buckets >= Integer.MAX_VALUE) {
            throw new IllegalArgumentException("Resolution is too high");
        }

        return (int) buckets;
    }

    public static void discardBuckets(int cell, int toDiscardCount, int length, IntConsumer discardByIndex) {
        if (toDiscardCount >= length) {
            for (int i = 0; i < length; ++i) {
                discardByIndex.accept(i);
            }
        } else if (toDiscardCount == 0) {
            // nothing to discard
        } else if ((cell + 1) - toDiscardCount >= 0) {
            int start = (cell + 1) - toDiscardCount;
            int len = Math.min(length, cell + 1);
            for (int i = start; i < len; ++i) {
                discardByIndex.accept(i);
            }
        } else {
            for (int i = 0; i <= cell; ++i) {
                discardByIndex.accept(i);
            }
            for (int i = length - (toDiscardCount - cell - 1); i < length; ++i) {
                discardByIndex.accept(i);
            }
        }
    }

    public static long findMaxInBuckets(int cell, int toDiscardCount, int length, IntToLongFunction getMaxByIndex) {
        long max = 0L;
        if (toDiscardCount >= length) {
            // nothing
        } else if (toDiscardCount == 0) {
            for (int i = 0; i < length; ++i) {
                max = Math.max(max, getMaxByIndex.applyAsLong(i));
            }
        } else if (cell + 1 <= toDiscardCount) {
            int end = length - (toDiscardCount - cell - 1);
            for (int i = cell + 1; i < end; ++i) {
                max = Math.max(max, getMaxByIndex.applyAsLong(i));
            }
        } else {
            for (int i = 0; i <= cell - toDiscardCount; ++i) {
                max = Math.max(max, getMaxByIndex.applyAsLong(i));
            }
            for (int i = cell + 1; i < length; ++i) {
                max = Math.max(max, getMaxByIndex.applyAsLong(i));
            }
        }

        return max;
    }

    public static long[] makeCopy(int length, IntToLongFunction getMaxByIndex) {
        long[] r = new long[length];
        for (int i = 0; i < length; i++) {
            r[i] = getMaxByIndex.applyAsLong(i);
        }
        return r;
    }
}
