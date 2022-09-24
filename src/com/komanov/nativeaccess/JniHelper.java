package com.komanov.nativeaccess;

import com.komanov.offheap.alloc.Allocator;

import static com.komanov.nativeaccess.Helper.ensureResultCode;

public abstract class JniHelper {
    static {
        System.loadLibrary("getloadavg_lib");
    }

    private JniHelper() {
    }

    private static native int getloadavg(long address, int num);

    public static double getLoadAverage() {
        long address = Allocator.alloc(24);
        try {
            ensureResultCode(getloadavg(address, 3));
            return Allocator.getDouble(address);
        } finally {
            Allocator.release(address);
        }
    }
}
