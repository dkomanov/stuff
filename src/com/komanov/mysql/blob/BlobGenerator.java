package com.komanov.mysql.blob;

import java.util.*;

public class BlobGenerator {
    public static List<Integer> Lengths = allLengths();

    public static byte[] randomBlob(int length) {
        return BlobCompressionRatio.LOW_COMPRESSION_1_3.generateBlob(length);
    }

    private static List<Integer> allLengths() {
        SortedSet<Integer> list = new TreeSet<>();
        for (int kb = 1; kb <= 99; ++kb) {
            list.add(kb * 1024);
        }

        for (int mb = 0; mb <= 4; ++mb) {
            for (int kb = 0; kb <= 9; ++kb) {
                int len = mb * 1024 * 1024 + kb * 100 * 1024;
                if (len != 0) {
                    list.add(len);
                }
            }
        }
        list.add(5 * 1024 * 1024);
        return Collections.unmodifiableList(new ArrayList<>(list));
    }
}
