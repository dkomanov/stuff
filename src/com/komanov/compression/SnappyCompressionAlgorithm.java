package com.komanov.compression;

import org.xerial.snappy.Snappy;

public class SnappyCompressionAlgorithm implements CompressionAlgorithm {
    private SnappyCompressionAlgorithm() {
    }

    public static final SnappyCompressionAlgorithm INSTANCE = new SnappyCompressionAlgorithm();

    @Override
    public byte[] encode(byte[] data) throws Throwable {
        return Snappy.compress(data);
    }

    @Override
    public byte[] decode(byte[] encoded) throws Throwable {
        return Snappy.uncompress(encoded);
    }
}
