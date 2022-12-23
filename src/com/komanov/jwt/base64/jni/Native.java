package com.komanov.jwt.base64.jni;

public interface Native {
    // base64::encode_config

    byte[] encodeConfigUrlSafe(byte[] payload);

    // base64::decode_config

    byte[] decodeConfigUrlSafe1(byte[] encoded);

    // get_byte_array_elements instead of convert_byte_array
    byte[] decodeConfigUrlSafe2(byte[] encoded);

    // pass size instead of JNI get array length
    byte[] decodeConfigUrlSafe3(byte[] encoded, int size);

    // get_primitive_array_critical instead of get_byte_array_elements
    byte[] decodeConfigUrlSafe4(byte[] encoded, int size);

    // base64::decode_config_slice

    byte[] decodeConfigSliceUrlSafe1(byte[] encoded, int size);

    byte[] decodeConfigSliceUrlSafe2NoCache(byte[] encoded);

    byte[] decodeConfigSliceUrlSafe2Cache(byte[] encoded);

    byte[] decodeConfigSliceUrlSafe3CacheInputOutput(byte[] encoded);

    // base64::encode_config_slice

    byte[] encodeConfigSlice1NoCache(byte[] payload);

    byte[] encodeConfigSlice1Cache(byte[] encoded);

    byte[] encodeConfigSlice2CacheInputOutput(byte[] encoded);

    // base64-simd

    byte[] encodeSimd(byte[] payload);

    byte[] decodeSimd(byte[] encoded);

    byte[] decodeSimdInPlace(byte[] encoded);

    byte[] encodeConfigSliceNalim(byte[] input);

    byte[] encodeSimdNalim(byte[] input);

    byte[] decodeConfigSliceNalim(byte[] input);

    byte[] decodeSimdNalim(byte[] input);

    byte[] decodeSimdInPlaceNalim(byte[] input);
}
