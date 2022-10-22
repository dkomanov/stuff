package com.komanov.jwt.base64.jni.jmh;

import com.komanov.jwt.base64.jni.Native;
import com.komanov.jwt.base64.jni.NativeBazel;
import com.komanov.jwt.base64.jni.NativeCargo;

public enum NativeLib {
    BAZEL {
        @Override
        public Native getNative() {
            return NativeBazel.INSTANCE;
        }
    },
    CARGO {
        @Override
        public Native getNative() {
            return NativeCargo.INSTANCE;
        }
    },
    ;

    public abstract Native getNative();
}
