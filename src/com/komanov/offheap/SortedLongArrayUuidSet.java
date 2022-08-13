package com.komanov.offheap;

import java.util.Iterator;
import java.util.UUID;

public class SortedLongArrayUuidSet extends java.util.AbstractSet<UUID> {
    private final long[] array;
    private final int size;

    public SortedLongArrayUuidSet(long[] array) {
        if (array.length % 2 != 0) {
            throw new IllegalArgumentException("array should be of even length");
        }

        this.array = array;
        this.size = array.length / 2;
    }

    @Override
    public boolean contains(Object o) {
        return contains((UUID) o);
    }

    public boolean contains(UUID value) {
        int found = binarySearch0(value);
        return found >= 0 && found < size;
    }

    private int binarySearch0(UUID key) {
        int low = 0;
        int high = size - 1;

        while (low <= high) {
            int mid = (low + high) >>> 1;

            int current = 2 * mid;
            long mostBits = array[current];

            if (mostBits < key.getMostSignificantBits())
                low = mid + 1;
            else if (mostBits > key.getMostSignificantBits())
                high = mid - 1;
            else {
                long leastBits = array[current + 1];
                if (leastBits < key.getLeastSignificantBits())
                    low = mid + 1;
                else if (leastBits > key.getLeastSignificantBits())
                    high = mid - 1;
                else
                    return mid; // key found
            }
        }
        return -(low + 1);  // key not found.
    }

    @Override
    public Iterator<UUID> iterator() {
        throw new IllegalStateException("not implemented");
    }

    @Override
    public int size() {
        return this.size;
    }
}
