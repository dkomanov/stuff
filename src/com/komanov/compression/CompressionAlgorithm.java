package com.komanov.compression;

public interface CompressionAlgorithm {
    byte[] encode(byte[] data) throws Throwable;

    byte[] decode(byte[] encoded) throws Throwable;
}
