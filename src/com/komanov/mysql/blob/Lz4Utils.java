package com.komanov.mysql.blob;

import net.jpountz.lz4.LZ4Compressor;
import net.jpountz.lz4.LZ4Factory;
import net.jpountz.lz4.LZ4FastDecompressor;

import java.util.Arrays;

// https://github.com/lz4/lz4-java
public class Lz4Utils {
    private static final LZ4Compressor LZ_4_COMPRESSOR = LZ4Factory.fastestInstance().highCompressor();
    public static final LZ4FastDecompressor LZ_4_FAST_DECOMPRESSOR = LZ4Factory.fastestInstance().fastDecompressor();

    public static byte[] compress(byte[] arr) {
        byte[] buffer = TlBuffer.getBuffer(LZ_4_COMPRESSOR.maxCompressedLength(arr.length) + 4);
        IntUtils.writeInt(arr.length, buffer);
        int count = LZ_4_COMPRESSOR.compress(arr, 0, arr.length, buffer, 4, buffer.length - 4);
        return Arrays.copyOf(buffer, count + 4);
    }

    public static byte[] decompress(byte[] arr) {
        int length = IntUtils.readInt(arr);
        byte[] result = new byte[length];
        LZ_4_FAST_DECOMPRESSOR.decompress(arr, 4, result, 0, length);
        return result;
    }
}
