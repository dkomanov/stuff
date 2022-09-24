package com.komanov.nativeaccess;

import com.komanov.offheap.alloc.Allocator;
import jnr.ffi.Pointer;

import static com.komanov.nativeaccess.Helper.ensureResultCode;

public abstract class JnrHelper {
    private JnrHelper() {
    }

    public static double getLoadAverage() {
        long address = Allocator.alloc(24);
        try {
            Pointer p = Pointer.wrap(JnrLibC.RUNTIME, address, 24);
            ensureResultCode(JnrLibC.INSTANCE.getloadavg(p, 3));
            return p.getDouble(0);
        } finally {
            Allocator.release(address);
        }
    }
}
