package com.komanov.mysql.blob;

public abstract class TlBuffer {
    private TlBuffer() {
    }

    private static final ThreadLocal<byte[]> localBuffer = new ThreadLocal<>();

    public static byte[] getBuffer(int len) {
        byte[] buffer = localBuffer.get();
        if (buffer == null || buffer.length < len) {
            buffer = new byte[len];
            localBuffer.set(buffer);
        }
        return buffer;
    }
}
