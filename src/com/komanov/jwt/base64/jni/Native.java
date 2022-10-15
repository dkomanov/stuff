package com.komanov.jwt.base64.jni;

import com.komanov.offheap.alloc.Allocator;
import sun.misc.Unsafe;

public abstract class Native {
    static {
        System.loadLibrary("base64_lib");
    }

    private Native() {
    }

    public static native byte[] encodeConfigUrlSafe(byte[] payload);

    private static native int encodeConfigSlice1(byte[] encoded, int size, long address, int outputSize);

    private static native int encodeConfigSlice2(long inputAddress, int inputSize, long outputAddress, int outputSize);

    public static native byte[] decodeConfigUrlSafe1(byte[] encoded);

    // get_byte_array_elements instead of convert_byte_array
    public static native byte[] decodeConfigUrlSafe2(byte[] encoded);

    // pass size instead of JNI get array length
    public static native byte[] decodeConfigUrlSafe3(byte[] encoded, int size);

    // get_primitive_array_critical instead of get_byte_array_elements
    public static native byte[] decodeConfigUrlSafe4(byte[] encoded, int size);

    public static native byte[] decodeConfigSliceUrlSafe1(byte[] encoded, int size);

    private static native int decodeConfigSliceUrlSafe2(byte[] encoded, int size, long address, int outputSize);
    private static native int decodeConfigSliceUrlSafe3(long inputAddress, int inputSize, long outputAddress, int outputSize);

    public static byte[] decodeConfigSliceUrlSafe2NoCache(byte[] encoded) {
        int inputSize = encoded.length;
        int outputSize = sizeForDecode(inputSize);
        long address = Allocator.alloc(outputSize);
        try {
            int written = decodeConfigSliceUrlSafe2(encoded, inputSize, address, outputSize);
            assert written == outputSize;
            return toByteArray(address, outputSize);
        } finally {
            Allocator.release(address);
        }
    }

    public static byte[] decodeConfigSliceUrlSafe2Cache(byte[] encoded) {
        int inputSize = encoded.length;
        int outputSize = sizeForDecode(inputSize);
        long address = DirectBuffer.getOutputBuffer(outputSize);
        int written = decodeConfigSliceUrlSafe2(encoded, inputSize, address, outputSize);
        assert written == outputSize;
        return toByteArray(address, outputSize);
    }

    public static byte[] decodeConfigSliceUrlSafe3CacheInputOutput(byte[] encoded) {
        int inputSize = encoded.length;
        long inputAddress = DirectBuffer.getInputBuffer(inputSize);
        copyFromByteArray(inputAddress, encoded, inputSize);

        int outputSize = sizeForDecode(inputSize);
        long address = DirectBuffer.getOutputBuffer(outputSize);
        int written = decodeConfigSliceUrlSafe3(inputAddress, inputSize, address, outputSize);
        assert written == outputSize;
        return toByteArray(address, outputSize);
    }

    public static byte[] encodeConfigSlice1NoCache(byte[] payload) {
        int inputSize = payload.length;
        int outputSize = sizeForEncode(inputSize);
        long address = Allocator.alloc(outputSize);
        try {
            int written = encodeConfigSlice1(payload, inputSize, address, outputSize);
            assert written >= outputSize - 4;
            return toByteArray(address, written);
        } finally {
            Allocator.release(address);
        }
    }

    public static byte[] encodeConfigSlice1Cache(byte[] encoded) {
        int inputSize = encoded.length;
        int outputSize = sizeForEncode(inputSize);
        long address = DirectBuffer.getOutputBuffer(outputSize);
        int written = encodeConfigSlice1(encoded, inputSize, address, outputSize);
        assert written >= outputSize - 4;
        return toByteArray(address, written);
    }

    public static byte[] encodeConfigSlice2CacheInputOutput(byte[] encoded) {
        int inputSize = encoded.length;
        long inputAddress = DirectBuffer.getInputBuffer(inputSize);
        copyFromByteArray(inputAddress, encoded, inputSize);
        int outputSize = sizeForEncode(inputSize);
        long outputAddress = DirectBuffer.getOutputBuffer(outputSize);
        int written = encodeConfigSlice2(inputAddress, inputSize, outputAddress, outputSize);
        assert written >= outputSize - 4;
        return toByteArray(outputAddress, written);
    }

    private static byte[] toByteArray(long address, int length) {
        byte[] result = new byte[length];
        Allocator.unsafe().copyMemory(null, address, result, Unsafe.ARRAY_BYTE_BASE_OFFSET, length);
        return result;
    }

    private static void copyFromByteArray(long address, byte[] input, int inputSize) {
        Allocator.unsafe().copyMemory(input, Unsafe.ARRAY_BYTE_BASE_OFFSET, null, address, inputSize);
    }

    private static int sizeForEncode(int inputSize) {
        return inputSize * 4 / 3 + 4;
    }

    private static int sizeForDecode(int inputSize) {
        // No need to add 4, because it's without padding!
        return inputSize * 3 / 4;
    }
}
