package com.komanov.jwt.base64.nativeaccess;

import jdk.incubator.foreign.CLinker;
import jdk.incubator.foreign.FunctionDescriptor;
import jdk.incubator.foreign.ResourceScope;
import jdk.incubator.foreign.SegmentAllocator;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;

import static jdk.incubator.foreign.CLinker.*;

public class PanamaJdk17NativeAccessor { //implements NativeAccessor {
    //public static final NativeAccessor INSTANCE = new PanamaJdk17NativeAccessor();

    private static final ResourceScope SCOPE = ResourceScope.newSharedScope();
    private static final SegmentAllocator ALLOCATOR = SegmentAllocator.ofScope(SCOPE);

    private static final MethodHandle BASE64_ENCODE = getMethodHandle("base64_encode");
    private static final MethodHandle BASE64_DECODE = getMethodHandle("base64_decode");
    private static final MethodHandle SIMD_ENCODE = getMethodHandle("simd_encode");
    private static final MethodHandle SIMD_DECODE = getMethodHandle("simd_decode");

    //@Override
    public byte[] base64_encode(byte[] payload) throws Throwable {
        try (var scope = ResourceScope.newConfinedScope()) {
            var allocator = SegmentAllocator.ofScope(scope);
            var inputSegment = allocator.allocateArray(C_CHAR, payload);
            int outputSize = payload.length * 2;
            var outputSegment = allocator.allocateArray(C_CHAR, outputSize);
            var written = (int) BASE64_ENCODE.invokeExact(
                    inputSegment.address(),
                    payload.length,
                    outputSegment.address(),
                    outputSize
            );
            return outputSegment.asSlice(0, written).toByteArray();
        }
    }

    //@Override
    public byte[] base64_decode(byte[] payload) throws Throwable {
        return new byte[0];
    }

    //@Override
    public byte[] simd_encode(byte[] payload) throws Throwable {
        return new byte[0];
    }

    //@Override
    public byte[] simd_decode(byte[] payload) throws Throwable {
        return new byte[0];
    }

    //@Override
    public void close() throws Exception {
        SCOPE.close();
    }

    private static MethodHandle getMethodHandle(String name) {
        return getInstance().downcallHandle(
                CLinker.systemLookup().lookup(name).get(),
                ALLOCATOR,
                MethodType.methodType(int.class),
                FunctionDescriptor.of(
                        C_INT,
                        C_POINTER,
                        C_INT,
                        C_POINTER,
                        C_INT
                )
        );
    }
}
