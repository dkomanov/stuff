package com.komanov.offheap.uuidhashmap;

import com.komanov.offheap.alloc.Allocator$;
import sun.misc.Unsafe;

import java.util.*;
import java.util.stream.Collectors;

public abstract class UuidToIntHashMap {
    private static final int HashCodeOffset = 0;
    private static final int NextIndexOffset = 4;
    private static final int MostBitsOffset = 8;
    private static final int LeastBitsOffset = 16;
    private static final int ValueOffset = 24;
    private static final int EntrySize =
            4 // hash code
                    + 4 // index to the next entry in linked list or -1 if it's end
                    + 16 // UUID: the most bits + the least bits
                    + 4 // value
            ;
    private static final Unsafe unsafe = Allocator$.MODULE$.unsafe();

    private UuidToIntHashMap() {
    }

    public abstract OptionalInt get(UUID key);

    public abstract void destroy();

    public static UuidToIntHashMap heap(Map<UUID, Integer> map) {
        if (map.isEmpty()) {
            return EmptyMap.INSTANCE;
        }

        return buildOnHeap(map);
    }

    private static OnHeapMap buildOnHeap(Map<UUID, Integer> map) {
        int size = map.size();
        ArrayList<List<UuidHashCodeIndex>> values = new ArrayList<>(
                map
                        .keySet()
                        .stream()
                        .map(uuid -> new UuidHashCodeIndex(uuid, size))
                        .collect(Collectors.groupingBy(i -> i.index))
                        .values()
        );

        HeapEntry[] entries = new HeapEntry[size];
        values.forEach(list -> {
            UuidHashCodeIndex uhci = list.get(0);
            entries[uhci.index] = new HeapEntry(uhci, getValue(map, uhci.uuid));
        });
        int indexOfNull = -1;
//        int maxList = 0;
//        int numberOfCollisions = 0;
//        int numberOfLists = 0;
        for (List<UuidHashCodeIndex> list : values) {
            if (list.size() > 1) {
//                numberOfLists += 1;
//                numberOfCollisions += list.size();
//                maxList = Math.max(maxList, list.size());
                HeapEntry prev = entries[list.get(0).index];
                for (int j = 1; j < list.size(); ++j) {
                    UuidHashCodeIndex uhci = list.get(j);
                    indexOfNull = findNullIndex(entries, indexOfNull);
                    prev.next = indexOfNull;
                    prev = new HeapEntry(uhci, getValue(map, uhci.uuid));
                    entries[indexOfNull] = prev;
                }
            }
        }

//        System.out.println("COLLISION REPORT:");
//        System.out.println("size: " + map.size());
//        System.out.println("total number of collisions: " + numberOfCollisions);
//        System.out.println("total number of lists: " + numberOfLists);
//        System.out.println("max list length: " + maxList);

        return new OnHeapMap(entries);
    }

    public static UuidToIntHashMap offHeap(Map<UUID, Integer> map) {
        if (map.isEmpty()) {
            return EmptyMap.INSTANCE;
        }

        OnHeapMap hm = buildOnHeap(map);
        final int size = hm.entries.length;
        final long address = unsafe.allocateMemory((long) size * EntrySize);
        long offset = address;
        for (int i = 0; i < hm.entries.length; ++i) {
            HeapEntry e = hm.entries[i];
            unsafe.putInt(offset + HashCodeOffset, e.hash);
            unsafe.putInt(offset + NextIndexOffset, e.next);
            unsafe.putLong(offset + MostBitsOffset, e.mostSignificantBits);
            unsafe.putLong(offset + LeastBitsOffset, e.leastSignificantBits);
            unsafe.putInt(offset + ValueOffset, e.value);
            offset += EntrySize;
        }

        return new OffHeapMap(address, size);
    }

    public static UuidToIntHashMap offHeapWithPadding(Map<UUID, Integer> map) {
        if (map.isEmpty()) {
            return EmptyMap.INSTANCE;
        }

        OnHeapMap hm = buildOnHeap(map);
        final int size = hm.entries.length;
        final long address = unsafe.allocateMemory((long) size * 32);
        long offset = address;
        for (int i = 0; i < hm.entries.length; ++i) {
            HeapEntry e = hm.entries[i];
            unsafe.putInt(offset + HashCodeOffset, e.hash);
            unsafe.putInt(offset + NextIndexOffset, e.next);
            unsafe.putLong(offset + MostBitsOffset, e.mostSignificantBits);
            unsafe.putLong(offset + LeastBitsOffset, e.leastSignificantBits);
            unsafe.putInt(offset + ValueOffset, e.value);
            offset += 32;
        }

        return new OffHeapMapWithPadding(address, size);
    }

    private static int getIndex(int hash, int size) {
        return Math.abs(hash) % size;
    }

    private static int getValue(Map<UUID, Integer> map, UUID key) {
        Integer value = map.get(key);
        if (value == null) {
            throw new IllegalArgumentException("value of " + key + " is null");
        }
        return value;
    }

    private static int findNullIndex(HeapEntry[] entries, int from) {
        for (int i = from + 1; i < entries.length; ++i) {
            if (entries[i] == null) {
                return i;
            }
        }
        throw new IllegalStateException("no null entry");
    }

    private static int offsetForIndex(int index) {
        return index * EntrySize;
    }

    private static int offsetForIndexWithPadding(int index) {
        return index * 32;
    }

    private static class UuidHashCodeIndex {
        public final UUID uuid;
        public final int hash;
        public final int index;

        public UuidHashCodeIndex(UUID uuid, int size) {
            this.uuid = uuid;
            this.hash = uuid.hashCode();
            this.index = getIndex(hash, size); // TODO optimize somehow?
        }
    }

    private static class EmptyMap extends UuidToIntHashMap {
        public static EmptyMap INSTANCE = new EmptyMap();

        @Override
        public OptionalInt get(UUID key) {
            return OptionalInt.empty();
        }

        @Override
        public void destroy() {
        }
    }

    private static class OnHeapMap extends UuidToIntHashMap {
        private final HeapEntry[] entries;

        public OnHeapMap(HeapEntry[] entries) {
            this.entries = entries;
        }

        @Override
        public OptionalInt get(UUID key) {
            if (key == null) {
                throw new IllegalArgumentException("key is null");
            }

            int hash = key.hashCode();
            int index = getIndex(hash, entries.length);
            do {
                HeapEntry e = entries[index];
                if (e.mostSignificantBits == key.getMostSignificantBits() && e.leastSignificantBits == key.getLeastSignificantBits()) {
                    return OptionalInt.of(e.value);
                }
                index = e.next;

            } while (index != -1);

            return OptionalInt.empty();
        }

        @Override
        public void destroy() {
        }
    }

    private static class OffHeapMap extends UuidToIntHashMap {
        private final long address;
        private final int size;

        private OffHeapMap(long address, int size) {
            this.address = address;
            this.size = size;
        }

        @Override
        public OptionalInt get(UUID key) {
            if (key == null) {
                throw new IllegalArgumentException("key is null");
            }

            int hash = key.hashCode();
            int index = getIndex(hash, size);
            do {
                long offset = address + offsetForIndex(index);
                int entryHash = unsafe.getInt(offset + HashCodeOffset);
                if (entryHash == hash) {
                    if (key.getMostSignificantBits() == unsafe.getLong(offset + MostBitsOffset)
                            && key.getLeastSignificantBits() == unsafe.getLong(offset + LeastBitsOffset)
                    ) {
                        return OptionalInt.of(unsafe.getInt(offset + ValueOffset));
                    }
                }
                index = unsafe.getInt(offset + NextIndexOffset);
            } while (index != -1);

            return OptionalInt.empty();
        }

        @Override
        public void destroy() {
            unsafe.freeMemory(address);
        }
    }

    private static class OffHeapMapWithPadding extends UuidToIntHashMap {
        private final long address;
        private final int size;

        private OffHeapMapWithPadding(long address, int size) {
            this.address = address;
            this.size = size;
        }

        @Override
        public OptionalInt get(UUID key) {
            if (key == null) {
                throw new IllegalArgumentException("key is null");
            }

            int hash = key.hashCode();
            int index = getIndex(hash, size);
            do {
                long offset = address + offsetForIndexWithPadding(index);
                int entryHash = unsafe.getInt(offset + HashCodeOffset);
                if (entryHash == hash) {
                    if (key.getMostSignificantBits() == unsafe.getLong(offset + MostBitsOffset)
                            && key.getLeastSignificantBits() == unsafe.getLong(offset + LeastBitsOffset)
                    ) {
                        return OptionalInt.of(unsafe.getInt(offset + ValueOffset));
                    }
                }
                index = unsafe.getInt(offset + NextIndexOffset);
            } while (index != -1);

            return OptionalInt.empty();
        }

        @Override
        public void destroy() {
            unsafe.freeMemory(address);
        }
    }

    private static class HeapEntry {
        public final int hash;
        public int next;
        public final long mostSignificantBits;
        public final long leastSignificantBits;
        public final int value;

        public HeapEntry(UuidHashCodeIndex uhci, int value) {
            this(uhci.hash, -1, uhci.uuid.getMostSignificantBits(), uhci.uuid.getLeastSignificantBits(), value);
        }

        public HeapEntry(int hash, int next, long mostSignificantBits, long leastSignificantBits, int value) {
            this.hash = hash;
            this.next = next;
            this.mostSignificantBits = mostSignificantBits;
            this.leastSignificantBits = leastSignificantBits;
            this.value = value;
        }
    }
}
