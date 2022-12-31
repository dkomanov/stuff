package com.komanov.compression;

public abstract class LocalBuffer {
    private LocalBuffer() {
    }

    private static final ThreadLocal<byte[]> tl = new ThreadLocal<>();

    public static byte[] getBuffer(int size) {
        byte[] bytes = tl.get();
        if (bytes == null || bytes.length < size) {
            bytes = new byte[size];
            tl.set(bytes);
        }
        return bytes;
    }
}
