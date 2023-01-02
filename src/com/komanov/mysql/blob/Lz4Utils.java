package com.komanov.mysql.blob;

import com.komanov.compression.CompressionAlgorithms;

public class Lz4Utils {
    public static byte[] compress(byte[] arr) {
        try {
            return CompressionAlgorithms.lz4_high9.encode(arr);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] decompress(byte[] arr) {
        try {
            return CompressionAlgorithms.lz4_high9.decode(arr);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
