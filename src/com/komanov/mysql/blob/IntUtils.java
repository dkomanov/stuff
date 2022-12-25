package com.komanov.mysql.blob;

import java.io.ByteArrayOutputStream;

public abstract class IntUtils {
    private IntUtils() {
    }

    public static void writeInt(int value, ByteArrayOutputStream baos) {
        if (value < 0) {
            throw new IllegalArgumentException("Negative value: " + value);
        }
        baos.write((value >> 0) & 0xff);
        baos.write((value >> 8) & 0xff);
        baos.write((value >> 16) & 0xff);
        baos.write((value >> 24) & 0xff);
    }

    public static void writeInt(int value, byte[] arr) {
        if (value < 0) {
            throw new IllegalArgumentException("Negative value: " + value);
        }
        if (arr.length < 4) {
            throw new IllegalArgumentException("Array is smaller than 4");
        }

        arr[0] = (byte) ((value >> 0) & 0xff);
        arr[1] = (byte) ((value >> 8) & 0xff);
        arr[2] = (byte) ((value >> 16) & 0xff);
        arr[3] = (byte) ((value >> 24) & 0xff);
    }

    public static int readInt(byte[] arr) {
        if (arr.length < 4) {
            throw new IllegalArgumentException("Expected 4 bytes for the uncompressed length");
        }

        int len = (arr[0] & 0xff) + ((arr[1] & 0xff) << 8) + ((arr[2] & 0xff) << 16) + ((arr[3] & 0xff) << 24);
        if (len < 0) {
            throw new IllegalStateException("Invalid encoded length: " + len);
        }

        return len;
    }
}
