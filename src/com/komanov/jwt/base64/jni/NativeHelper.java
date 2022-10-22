package com.komanov.jwt.base64.jni;

import com.komanov.offheap.alloc.Allocator;
import sun.misc.Unsafe;

abstract class NativeHelper {
    private NativeHelper() {
    }

    public static byte[] toByteArray(long address, int length) {
        byte[] result = new byte[length];
        Allocator.unsafe().copyMemory(null, address, result, Unsafe.ARRAY_BYTE_BASE_OFFSET, length);
        return result;
    }

    public static void copyFromByteArray(long address, byte[] input, int inputSize) {
        Allocator.unsafe().copyMemory(input, Unsafe.ARRAY_BYTE_BASE_OFFSET, null, address, inputSize);
    }

    public static int sizeForEncode(int inputSize) {
        return inputSize * 4 / 3 + 4;
    }

    public static int sizeForDecode(int inputSize) {
        // No need to add 4, because it's without padding!
        return inputSize * 3 / 4;
    }
}
