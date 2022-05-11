package com.komanov.javaenum;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum SimpleEnum {
    UNKNOWN(0),
    FIRST(1),
    SECOND(2),
    THIRD(3),
    FOURTH(4),
    ;

    public final int value;

    SimpleEnum(int value) {
        this.value = value;
    }

    private static SimpleEnum[] valuesCopy = values();
    private static Map<Integer, SimpleEnum> reverseLookup =
            Arrays.stream(SimpleEnum.values()).collect(Collectors.toMap(s -> s.value, Function.identity()));

    public static SimpleEnum fromInt(final int id) {
        return reverseLookup.getOrDefault(id, UNKNOWN);
    }

    public static SimpleEnum fromIntStream(final int id) {
        return Arrays.stream(values()).filter(s -> s.value == id).findFirst().orElse(UNKNOWN);
    }

    public static SimpleEnum fromIntLoop(final int id) {
        for (SimpleEnum s : values()) {
            if (s.value == id) return s;
        }
        return UNKNOWN;
    }

    public static SimpleEnum fromIntLoopOverCopy(final int id) {
        for (SimpleEnum s : valuesCopy) {
            if (s.value == id) return s;
        }
        return UNKNOWN;
    }

    public static SimpleEnum fromIndex(final int id) {
        if (id >= 0 && id < values().length) {
            return values()[id];
        }
        return UNKNOWN;
    }

    public static SimpleEnum fromIndexOverCopy(final int id) {
        if (id >= 0 && id < valuesCopy.length) {
            return valuesCopy[id];
        }
        return UNKNOWN;
    }

}