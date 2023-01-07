package com.komanov.mysql.blob;

import java.util.SortedSet;
import java.util.TreeSet;

public class BlobGenerator {
    public static int[] Lengths = allLengths();

    private static int[] allLengths() {
        SortedSet<Integer> list = new TreeSet<>();
        list.add(1024);
        for (int kb = 5; kb < 100; kb += 5) {
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

        return list.stream().mapToInt(v -> v).toArray();
    }
}
