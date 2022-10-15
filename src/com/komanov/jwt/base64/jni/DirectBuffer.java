package com.komanov.jwt.base64.jni;

import com.komanov.offheap.alloc.Allocator;

public final class DirectBuffer {
    private static final ThreadLocal<DirectBuffer> inputBuffer = new ThreadLocal<>();
    private static final ThreadLocal<DirectBuffer> outputBuffer = new ThreadLocal<>();

    private final long address;
    private final int bufferSize;
    private volatile boolean released;

    private DirectBuffer(long address, int bufferSize) {
        this.address = address;
        this.bufferSize = bufferSize;
    }

    @Override
    protected void finalize() {
        release();
    }

    public static long getInputBuffer(int size) {
        return getDirectBuffer(inputBuffer, size);
    }

    public static long getOutputBuffer(int size) {
        return getDirectBuffer(outputBuffer, size);
    }

    private static long getDirectBuffer(ThreadLocal<DirectBuffer> tl, int size) {
        DirectBuffer db = tl.get();
        if (db == null || db.bufferSize < size) {
            if (db != null) {
                db.release();
            }
            db = new DirectBuffer(Allocator.alloc(size), size);
            tl.set(db);
        }
        return db.address;
    }

    private void release() {
        if (!released) {
            Allocator.release(address);
            released = true;
        }
    }
}
