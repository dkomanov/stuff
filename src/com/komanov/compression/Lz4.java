package com.komanov.compression;

import net.jpountz.lz4.LZ4Compressor;
import net.jpountz.lz4.LZ4Factory;
import net.jpountz.lz4.LZ4FastDecompressor;

import java.util.Arrays;

import static org.apache.commons.lang3.Conversion.byteArrayToInt;
import static org.apache.commons.lang3.Conversion.intToByteArray;

class Lz4 implements CompressionAlgorithm {
    private static final LZ4FastDecompressor decompressor = LZ4Factory.fastestInstance().fastDecompressor();

    public static Lz4 FAST = new Lz4(LZ4Factory.fastestInstance().fastCompressor());
    public static Lz4 HIGH_9 = new Lz4(LZ4Factory.fastestInstance().highCompressor());
    public static Lz4 HIGH_17 = new Lz4(LZ4Factory.fastestInstance().highCompressor(17));

    private final LZ4Compressor compressor;

    private Lz4(LZ4Compressor compressor) {
        this.compressor = compressor;
    }

    @Override
    public byte[] encode(byte[] data) {
        byte[] out = LocalBuffer.getBuffer(compressor.maxCompressedLength(data.length) + 4);
        intToByteArray(data.length, 0, out, 0, 4);
        int written = compressor.compress(data, 0, data.length, out, 4);
        return Arrays.copyOf(out, written + 4);
    }

    @Override
    public byte[] decode(byte[] encoded) throws Throwable {
        int size = byteArrayToInt(encoded, 0, 0, 0, 4);
        byte[] out = new byte[size];
        int read = decompressor.decompress(encoded, 4, out, 0, size);

        assert read == encoded.length - 4;

        return out;
    }
}
