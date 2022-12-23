package com.komanov.jwt.base64.nativeaccess;

public interface NativeAccessor extends AutoCloseable  {
    byte[] base64_encode(byte[] payload) throws Throwable;

    byte[] base64_decode(byte[] payload) throws Throwable;


    byte[] simd_encode(byte[] payload) throws Throwable;

    byte[] simd_decode(byte[] payload) throws Throwable;
}
