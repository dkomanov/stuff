package com.komanov.offheap;

import java.util.Arrays;
import java.util.Iterator;
import java.util.UUID;

public class SortedUuidArray extends java.util.AbstractSet<UUID> {
    private final UUID[] array;

    public SortedUuidArray(UUID[] array) {
        this.array = array;
    }

    @Override
    public boolean contains(Object o) {
        return contains((UUID) o);
    }

    public boolean contains(UUID value) {
        int found = Arrays.binarySearch(array, value);
        return found >= 0 && found < array.length;
    }

    @Override
    public Iterator<UUID> iterator() {
        return Arrays.stream(array).iterator();
    }

    @Override
    public int size() {
        return array.length;
    }
}
