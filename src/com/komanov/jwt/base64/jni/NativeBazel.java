package com.komanov.jwt.base64.jni;

import com.komanov.offheap.alloc.Allocator;

import static com.komanov.jwt.base64.jni.NativeHelper.*;

public abstract class NativeBazel {
    static {
        System.loadLibrary("base64_lib");
    }

    public static final Native INSTANCE = new NativeBazelImpl();

    private NativeBazel() {
    }

    private static native byte[] encodeConfigUrlSafe(byte[] payload);

    private static native int encodeConfigSlice1(byte[] encoded, int size, long address, int outputSize);

    private static native int encodeConfigSlice2(long inputAddress, int inputSize, long outputAddress, int outputSize);

    private static native byte[] decodeConfigUrlSafe1(byte[] encoded);

    private static native byte[] decodeConfigUrlSafe2(byte[] encoded);

    private static native byte[] decodeConfigUrlSafe3(byte[] encoded, int size);

    private static native byte[] decodeConfigUrlSafe4(byte[] encoded, int size);

    private static native byte[] decodeConfigSliceUrlSafe1(byte[] encoded, int size);

    private static native int decodeConfigSliceUrlSafe2(byte[] encoded, int size, long address, int outputSize);

    private static native int decodeConfigSliceUrlSafe3(long inputAddress, int inputSize, long outputAddress, int outputSize);

    private static native int encodeSimdNative(long inputAddress, int inputSize, long outputAddress, int outputSize);

    private static native int decodeSimdNative(long inputAddress, int inputSize, long outputAddress, int outputSize);

    private static native int decodeSimdInPlaceNative(long address, int size);

    private static class NativeBazelImpl implements Native {
        @Override
        public byte[] encodeConfigUrlSafe(byte[] payload) {
            return NativeBazel.encodeConfigUrlSafe(payload);
        }

        @Override
        public byte[] decodeConfigUrlSafe1(byte[] encoded) {
            return NativeBazel.decodeConfigUrlSafe1(encoded);
        }

        @Override
        public byte[] decodeConfigUrlSafe2(byte[] encoded) {
            return NativeBazel.decodeConfigUrlSafe2(encoded);
        }

        @Override
        public byte[] decodeConfigUrlSafe3(byte[] encoded, int size) {
            return NativeBazel.decodeConfigUrlSafe3(encoded, size);
        }

        @Override
        public byte[] decodeConfigUrlSafe4(byte[] encoded, int size) {
            return NativeBazel.decodeConfigUrlSafe4(encoded, size);
        }

        @Override
        public byte[] decodeConfigSliceUrlSafe1(byte[] encoded, int size) {
            return NativeBazel.decodeConfigSliceUrlSafe1(encoded, size);
        }

        @Override
        public byte[] decodeConfigSliceUrlSafe2NoCache(byte[] encoded) {
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

        @Override
        public byte[] decodeConfigSliceUrlSafe2Cache(byte[] encoded) {
            int inputSize = encoded.length;
            int outputSize = sizeForDecode(inputSize);
            long address = DirectBuffer.getOutputBuffer(outputSize);
            int written = decodeConfigSliceUrlSafe2(encoded, inputSize, address, outputSize);
            assert written == outputSize;
            return toByteArray(address, outputSize);
        }

        @Override
        public byte[] decodeConfigSliceUrlSafe3CacheInputOutput(byte[] encoded) {
            int inputSize = encoded.length;
            long inputAddress = DirectBuffer.getInputBuffer(inputSize);
            copyFromByteArray(inputAddress, encoded, inputSize);

            int outputSize = sizeForDecode(inputSize);
            long address = DirectBuffer.getOutputBuffer(outputSize);
            int written = decodeConfigSliceUrlSafe3(inputAddress, inputSize, address, outputSize);
            assert written == outputSize;
            return toByteArray(address, outputSize);
        }

        @Override
        public byte[] encodeConfigSlice1NoCache(byte[] payload) {
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

        @Override
        public byte[] encodeConfigSlice1Cache(byte[] encoded) {
            int inputSize = encoded.length;
            int outputSize = sizeForEncode(inputSize);
            long address = DirectBuffer.getOutputBuffer(outputSize);
            int written = encodeConfigSlice1(encoded, inputSize, address, outputSize);
            assert written >= outputSize - 4;
            return toByteArray(address, written);
        }

        @Override
        public byte[] encodeConfigSlice2CacheInputOutput(byte[] encoded) {
            int inputSize = encoded.length;
            long inputAddress = DirectBuffer.getInputBuffer(inputSize);
            copyFromByteArray(inputAddress, encoded, inputSize);
            int outputSize = sizeForEncode(inputSize);
            long outputAddress = DirectBuffer.getOutputBuffer(outputSize);
            int written = encodeConfigSlice2(inputAddress, inputSize, outputAddress, outputSize);
            assert written >= outputSize - 4;
            return toByteArray(outputAddress, written);
        }

        @Override
        public byte[] encodeSimd(byte[] payload) {
            int inputSize = payload.length;
            long inputAddress = DirectBuffer.getInputBuffer(inputSize);
            copyFromByteArray(inputAddress, payload, inputSize);
            int outputSize = sizeForEncode(inputSize);
            long outputAddress = DirectBuffer.getOutputBuffer(outputSize);
            int written = encodeSimdNative(inputAddress, inputSize, outputAddress, outputSize);
            assert written >= outputSize - 4;
            return toByteArray(outputAddress, written);
        }

        @Override
        public byte[] decodeSimd(byte[] encoded) {
            int inputSize = encoded.length;
            long inputAddress = DirectBuffer.getInputBuffer(inputSize);
            copyFromByteArray(inputAddress, encoded, inputSize);
            int outputSize = sizeForDecode(inputSize);
            long address = DirectBuffer.getOutputBuffer(outputSize);
            int written = decodeSimdNative(inputAddress, inputSize, address, outputSize);
            assert written == outputSize;
            return toByteArray(address, outputSize);
        }

        @Override
        public byte[] decodeSimdInPlace(byte[] encoded) {
            int inputSize = encoded.length;
            long inputAddress = DirectBuffer.getInputBuffer(inputSize);
            copyFromByteArray(inputAddress, encoded, inputSize);
            int written = decodeSimdInPlaceNative(inputAddress, inputSize);
            return toByteArray(inputAddress, written);
        }
    }
}
