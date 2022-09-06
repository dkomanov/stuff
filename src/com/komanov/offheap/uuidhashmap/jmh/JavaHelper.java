package com.komanov.offheap.uuidhashmap.jmh;

import com.komanov.offheap.uuidhashmap.UuidToIntHashMap;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class JavaHelper {
    public static int run(Map<UUID, Integer> collection, List<UUID> toCheck) {
        int r = 0;
        for (UUID uuid : toCheck) {
            if (collection.containsKey(uuid)) {
                r += 1;
            }
        }
        return r;
    }

    public static int run(scala.collection.immutable.Map<UUID, Integer> collection, List<UUID> toCheck) {
        int r = 0;
        for (UUID uuid : toCheck) {
            if (collection.contains(uuid)) {
                r += 1;
            }
        }
        return r;
    }

    public static int run(UuidToIntHashMap map, List<UUID> toCheck) {
        int r = 0;
        for (UUID uuid : toCheck) {
            if (map.get(uuid).isPresent()) {
                r += 1;
            }
        }
        return r;
    }
}
