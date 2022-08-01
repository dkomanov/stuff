package com.komanov.collection.jmh;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class JavaHelper {
    public static int run(Set<UUID> collection, List<UUID> toCheck) {
        int r = 0;
        for (UUID uuid : toCheck) {
            if (collection.contains(uuid)) {
                r += 1;
            }
        }
        return r;
    }

    public static int run(scala.collection.immutable.Set<UUID> collection, List<UUID> toCheck) {
        int r = 0;
        for (UUID uuid : toCheck) {
            if (collection.contains(uuid)) {
                r += 1;
            }
        }
        return r;
    }

    public static int run(scala.collection.mutable.Set<UUID> collection, List<UUID> toCheck) {
        int r = 0;
        for (UUID uuid : toCheck) {
            if (collection.contains(uuid)) {
                r += 1;
            }
        }
        return r;
    }

    public static int run(Map<UUID, scala.Int> collection, List<UUID> toCheck) {
        int r = 0;
        for (UUID uuid : toCheck) {
            if (collection.containsKey(uuid)) {
                r += 1;
            }
        }
        return r;
    }

    public static int run(scala.collection.immutable.Map<UUID, scala.Int> collection, List<UUID> toCheck) {
        int r = 0;
        for (UUID uuid : toCheck) {
            if (collection.contains(uuid)) {
                r += 1;
            }
        }
        return r;
    }

    public static int run(scala.collection.mutable.Map<UUID, scala.Int> collection, List<UUID> toCheck) {
        int r = 0;
        for (UUID uuid : toCheck) {
            if (collection.contains(uuid)) {
                r += 1;
            }
        }
        return r;
    }
}
