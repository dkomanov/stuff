use std;
use std::slice::{from_raw_parts, from_raw_parts_mut};

use base64;
use base64_simd;
use jni::JNIEnv;
use jni::objects::{JClass, ReleaseMode};
use jni::sys::jbyteArray;

/*
ENCODE
 */

#[no_mangle]
pub extern "system" fn Java_com_komanov_jwt_base64_jni_NativeBazel_encodeConfigUrlSafe(
    env: JNIEnv,
    _class: JClass,
    payload: jbyteArray,
) -> jbyteArray {
    let result = base64::encode_config(env.convert_byte_array(payload).unwrap(), base64::URL_SAFE_NO_PAD);
    return env.byte_array_from_slice(result.as_bytes()).unwrap();
}

#[no_mangle]
pub extern "system" fn Java_com_komanov_jwt_base64_jni_NativeBazel_encodeConfigSlice1(
    env: JNIEnv,
    _class: JClass,
    payload: jbyteArray,
    size: usize,
    output: *mut u8,
    output_size: usize,
) -> usize {
    let arr = env.get_byte_array_elements(payload, ReleaseMode::NoCopyBack).unwrap();
    let (input_slice, output_slice) = unsafe {
        (
            from_raw_parts(arr.as_ptr() as *const u8, size),
            from_raw_parts_mut(output, output_size)
        )
    };
    return base64::encode_config_slice(input_slice, base64::URL_SAFE_NO_PAD, output_slice);
}

#[no_mangle]
pub extern "system" fn Java_com_komanov_jwt_base64_jni_NativeBazel_encodeConfigSlice2(
    _env: JNIEnv,
    _class: JClass,
    input: *const u8,
    input_size: usize,
    output: *mut u8,
    output_size: usize,
) -> usize {
    let (input_slice, output_slice) = unsafe {
        (
            from_raw_parts(input, input_size),
            from_raw_parts_mut(output, output_size)
        )
    };
    return base64::encode_config_slice(input_slice, base64::URL_SAFE_NO_PAD, output_slice);
}

/*
DECODE
 */

#[no_mangle]
pub extern "system" fn Java_com_komanov_jwt_base64_jni_NativeBazel_decodeConfigUrlSafe1(
    env: JNIEnv,
    _class: JClass,
    payload: jbyteArray,
) -> jbyteArray {
    let result = base64::decode_config(env.convert_byte_array(payload).unwrap(), base64::URL_SAFE_NO_PAD).unwrap();
    return env.byte_array_from_slice(&result).unwrap();
}

#[no_mangle]
pub extern "system" fn Java_com_komanov_jwt_base64_jni_NativeBazel_decodeConfigUrlSafe2(
    env: JNIEnv,
    _class: JClass,
    payload: jbyteArray,
) -> jbyteArray {
    let arr = env.get_byte_array_elements(payload, ReleaseMode::NoCopyBack).unwrap();
    let sl = unsafe {
        from_raw_parts(arr.as_ptr() as *mut u8, arr.size().unwrap() as usize)
    };
    let result = base64::decode_config(sl, base64::URL_SAFE_NO_PAD).unwrap();
    return env.byte_array_from_slice(&result).unwrap();
}

#[no_mangle]
pub extern "system" fn Java_com_komanov_jwt_base64_jni_NativeBazel_decodeConfigUrlSafe3(
    env: JNIEnv,
    _class: JClass,
    payload: jbyteArray,
    size: usize,
) -> jbyteArray {
    let arr = env.get_byte_array_elements(payload, ReleaseMode::NoCopyBack).unwrap();
    let sl = unsafe {
        from_raw_parts(arr.as_ptr() as *mut u8, size)
    };
    let result = base64::decode_config(sl, base64::URL_SAFE_NO_PAD).unwrap();
    return env.byte_array_from_slice(&result).unwrap();
}

#[no_mangle]
pub extern "system" fn Java_com_komanov_jwt_base64_jni_NativeBazel_decodeConfigUrlSafe4(
    env: JNIEnv,
    _class: JClass,
    payload: jbyteArray,
    size: usize,
) -> jbyteArray {
    let arr = env.get_primitive_array_critical(payload, ReleaseMode::NoCopyBack).unwrap();
    let sl = unsafe {
        from_raw_parts(arr.as_ptr() as *mut u8, size)
    };
    let result = base64::decode_config(sl, base64::URL_SAFE_NO_PAD).unwrap();
    return env.byte_array_from_slice(&result).unwrap();
}

#[no_mangle]
pub extern "system" fn Java_com_komanov_jwt_base64_jni_NativeBazel_decodeConfigSliceUrlSafe1(
    env: JNIEnv,
    _class: JClass,
    payload: jbyteArray,
    size: usize,
) -> jbyteArray {
    let mut buffer = Vec::<u8>::with_capacity(size * 3 / 4);
    let arr = env.get_byte_array_elements(payload, ReleaseMode::NoCopyBack).unwrap();
    let (input_slice, mut output_slice) = unsafe {
        (
            from_raw_parts(arr.as_ptr() as *const u8, size),
            from_raw_parts_mut(buffer.as_mut_ptr(), buffer.capacity()),
        )
    };

    let size = base64::decode_config_slice(input_slice, base64::URL_SAFE_NO_PAD, &mut output_slice).unwrap();
    assert_eq!(size, buffer.capacity());

    unsafe {
        buffer.set_len(size);
    }

    return env.byte_array_from_slice(&buffer).unwrap();
}

#[no_mangle]
pub extern "system" fn Java_com_komanov_jwt_base64_jni_NativeBazel_decodeConfigSliceUrlSafe2(
    env: JNIEnv,
    _class: JClass,
    payload: jbyteArray,
    size: usize,
    output: *mut u8,
    output_size: usize,
) -> usize {
    let arr = env.get_byte_array_elements(payload, ReleaseMode::NoCopyBack).unwrap();
    let (input_slice, output_slice) = unsafe {
        (
            from_raw_parts(arr.as_ptr() as *const u8, size),
            from_raw_parts_mut(output, output_size),
        )
    };
    return base64::decode_config_slice(input_slice, base64::URL_SAFE_NO_PAD, output_slice).unwrap();
}

#[no_mangle]
pub extern "system" fn Java_com_komanov_jwt_base64_jni_NativeBazel_decodeConfigSliceUrlSafe3(
    _env: JNIEnv,
    _class: JClass,
    input: *const u8,
    input_size: usize,
    output: *mut u8,
    output_size: usize,
) -> usize {
    let (input_slice, output_slice) = unsafe {
        (
            from_raw_parts(input, input_size),
            from_raw_parts_mut(output, output_size),
        )
    };
    return base64::decode_config_slice(input_slice, base64::URL_SAFE_NO_PAD, output_slice).unwrap();
}

#[no_mangle]
pub extern "system" fn Java_com_komanov_jwt_base64_jni_NativeBazel_encodeSimdNative(
    _env: JNIEnv,
    _class: JClass,
    input: *const u8,
    input_size: usize,
    output: *mut u8,
    output_size: usize,
) -> usize {
    let (input_slice, output_slice) = unsafe {
        (
            from_raw_parts(input, input_size),
            from_raw_parts_mut(output, output_size)
        )
    };
    let result = base64_simd::URL_SAFE_NO_PAD.encode(input_slice, base64_simd::OutRef::from_slice(output_slice));
    result.len()
}

#[no_mangle]
pub extern "system" fn Java_com_komanov_jwt_base64_jni_NativeBazel_decodeSimdNative(
    _env: JNIEnv,
    _class: JClass,
    input: *const u8,
    input_size: usize,
    output: *mut u8,
    output_size: usize,
) -> usize {
    let (input_slice, output_slice) = unsafe {
        (
            from_raw_parts(input, input_size),
            from_raw_parts_mut(output, output_size)
        )
    };
    let result = base64_simd::URL_SAFE_NO_PAD.decode(input_slice, base64_simd::OutRef::from_slice(output_slice)).unwrap();
    result.len()
}

#[no_mangle]
pub extern "system" fn Java_com_komanov_jwt_base64_jni_NativeBazel_decodeSimdInPlaceNative(
    _env: JNIEnv,
    _class: JClass,
    output: *mut u8,
    output_size: usize,
) -> usize {
    let output_slice = unsafe {
        from_raw_parts_mut(output, output_size)
    };
    let result = base64_simd::URL_SAFE_NO_PAD.decode_inplace(output_slice).unwrap();
    result.len()
}
