package com.komanov.jwt.base64.nativeaccess.panama;

import com.komanov.jwt.base64.nativeaccess.NativeAccessor;

public class PanamaJdk19NativeAccessor implements NativeAccessor {
    @Override
    public byte[] base64_encode(byte[] payload) {
        return new byte[0];
    }

    @Override
    public byte[] base64_decode(byte[] payload) {
        return new byte[0];
    }

    @Override
    public byte[] simd_encode(byte[] payload) {
        return new byte[0];
    }

    @Override
    public byte[] simd_decode(byte[] payload) {
        return new byte[0];
    }
}
