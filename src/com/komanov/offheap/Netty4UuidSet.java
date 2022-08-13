package com.komanov.offheap;

import io.netty.buffer.ByteBuf;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.UUID;

public class Netty4UuidSet extends AbstractSet<UUID> {
    private final ByteBuf buf;
    private final int size;

    public Netty4UuidSet(ByteBuf buf, int size) {
        this.buf = buf;
        this.size = size;
    }

    public boolean contains(UUID uuid) {
        int found = binarySearch0(uuid);
        return found >= 0 && found < size;
    }

    @Override
    public boolean contains(Object o) {
        return contains((UUID) o);
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
        buf.release();
    }

    private int binarySearch0(UUID key) {
        int low = 0;
        int high = size - 1;

        while (low <= high) {
            int mid = (low + high) >>> 1;

            int current = 16 * mid;
            long mostBits = buf.getLong(current);

            if (mostBits < key.getMostSignificantBits())
                low = mid + 1;
            else if (mostBits > key.getMostSignificantBits())
                high = mid - 1;
            else {
                long leastBits = buf.getLong(current + 8);
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
}
