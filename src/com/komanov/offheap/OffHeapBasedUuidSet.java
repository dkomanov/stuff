package com.komanov.offheap;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.UUID;

public class OffHeapBasedUuidSet extends AbstractSet<UUID> {

    private final long address;
    private final int size;

    OffHeapBasedUuidSet(long address, int size) {
        this.address = address;
        this.size = size;
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

            long current = address + 16L * mid;
            long mostBits = Allocator.getLong(current);

            if (mostBits < key.getMostSignificantBits())
                low = mid + 1;
            else if (mostBits > key.getMostSignificantBits())
                high = mid - 1;
            else {
                long leastBits = Allocator.getLong(current + 8);
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

    public void destroy() {
        Allocator.release(address);
    }
}
