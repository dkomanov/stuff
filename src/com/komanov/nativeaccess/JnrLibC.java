package com.komanov.nativeaccess;

import jnr.ffi.CallingConvention;
import jnr.ffi.LibraryLoader;
import jnr.ffi.Pointer;
import jnr.ffi.Runtime;

public interface JnrLibC {
    JnrLibC INSTANCE = LibraryLoader.create(JnrLibC.class)
            .convention(CallingConvention.DEFAULT)
            .load("c");

    Runtime RUNTIME = Runtime.getRuntime(INSTANCE);

    int getloadavg(Pointer r, int num);
}
