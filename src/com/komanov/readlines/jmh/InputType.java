package com.komanov.readlines.jmh;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public enum InputType {
    ASCII {
        @Override
        public int[] getCodePoints(int length) {
            return shuffled(IntStream.iterate(1, i -> i % 88 + 40).limit(length));
        }
    },
    SINGLE_CHAR_ONLY {
        @Override
        public int[] getCodePoints(int length) {
            return shuffled(IntStream.iterate(1, i -> (i % (Character.MAX_VALUE - 40)) + 40).limit(length));
        }
    },
    MIXED {
        @Override
        public int[] getCodePoints(int length) {
            return shuffled(IntStream.range(1, 0x8040).limit(length));
        }
    },
    /**/;

    private static final Random rnd = new Random(1234999);

    private static int[] shuffled(IntStream stream) {
        List<Integer> numbers = stream.boxed().collect(Collectors.toList());
        Collections.shuffle(numbers, rnd);
        return numbers.stream().mapToInt(i -> i).toArray();
    }

    public abstract int[] getCodePoints(int length);
}
