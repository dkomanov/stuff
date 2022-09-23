package com.komanov.jni.rs.bin;

public abstract class Native {
    private Native() {
    }

    static {
        System.loadLibrary("simple_jni_lib");
    }

    public static native String greet(String name);
}
