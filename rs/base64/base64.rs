use base64;
use jni::JNIEnv;
use jni::objects::{JClass, JObject, ReleaseMode};
use jni::sys::jbyteArray;
use std;

/*
ENCODE
 */

#[no_mangle]
pub extern "system" fn Java_com_komanov_jwt_base64_jni_Native_encodeConfigUrlSafe(
    env: JNIEnv,
    _class: JClass,
    payload: jbyteArray,
) -> jbyteArray {
    // gives ~2 microseconds for 1K input
    // let input = env.convert_byte_array(payload).unwrap();
    // let config = base64::URL_SAFE_NO_PAD;
    //
    // let capacity = input.len() * 4 / 3 + 4;
    // let mut buf = std::vec::Vec::with_capacity(capacity);
    // unsafe {
    //     let mut output_slice = std::slice::from_raw_parts_mut(buf.as_mut_ptr(), capacity);
    //     let size = base64::encode_config_slice(input, config, &mut output_slice);
    //
    //     let slice = std::slice::from_raw_parts(buf.as_ptr(), size);
    //     return env.byte_array_from_slice(&slice).unwrap();
    // }

    let result = base64::encode_config(env.convert_byte_array(payload).unwrap(), base64::URL_SAFE_NO_PAD);
    return env.byte_array_from_slice(result.as_bytes()).unwrap();
}

#[no_mangle]
pub extern "system" fn Java_com_komanov_jwt_base64_jni_Native_encodeConfigSlice1(
    env: JNIEnv,
    _class: JClass,
    payload: jbyteArray,
    size: usize,
    output: *mut u8,
    output_size: usize,
) -> usize {
    unsafe {
        let arr = env.get_byte_array_elements(payload, ReleaseMode::NoCopyBack).unwrap();
        let input_slice = std::slice::from_raw_parts(arr.as_ptr() as *const u8, size);
        let output_slice = std::slice::from_raw_parts_mut(output, output_size);
        return base64::encode_config_slice(input_slice, base64::URL_SAFE_NO_PAD, output_slice);
    }
}

#[no_mangle]
pub extern "system" fn Java_com_komanov_jwt_base64_jni_Native_encodeConfigSlice2(
    _env: JNIEnv,
    _class: JClass,
    input: *const u8,
    input_size: usize,
    output: *mut u8,
    output_size: usize,
) -> usize {
    unsafe {
        let input_slice = std::slice::from_raw_parts(input, input_size);
        let output_slice = std::slice::from_raw_parts_mut(output, output_size);
        return base64::encode_config_slice(input_slice, base64::URL_SAFE_NO_PAD, output_slice);
    }
}

/*
DECODE
 */

#[no_mangle]
pub extern "system" fn Java_com_komanov_jwt_base64_jni_Native_decodeConfigUrlSafe1(
    env: JNIEnv,
    _class: JClass,
    payload: jbyteArray,
) -> jbyteArray {
    let result = base64::decode_config(env.convert_byte_array(payload).unwrap(), base64::URL_SAFE_NO_PAD).unwrap();
    return env.byte_array_from_slice(&result).unwrap();
}

#[no_mangle]
pub extern "system" fn Java_com_komanov_jwt_base64_jni_Native_decodeConfigUrlSafe2(
    env: JNIEnv,
    _class: JClass,
    payload: jbyteArray,
) -> jbyteArray {
    let result = unsafe {
        let arr = env.get_byte_array_elements(payload, ReleaseMode::NoCopyBack).unwrap();
        let sl = std::slice::from_raw_parts(arr.as_ptr() as *mut u8, arr.size().unwrap() as usize);
        base64::decode_config(sl, base64::URL_SAFE_NO_PAD).unwrap()
    };
    return env.byte_array_from_slice(&result).unwrap();
}

#[no_mangle]
pub extern "system" fn Java_com_komanov_jwt_base64_jni_Native_decodeConfigUrlSafe3(
    env: JNIEnv,
    _class: JClass,
    payload: jbyteArray,
    size: usize,
) -> jbyteArray {
    let result = unsafe {
        let arr = env.get_byte_array_elements(payload, ReleaseMode::NoCopyBack).unwrap();
        let sl = std::slice::from_raw_parts(arr.as_ptr() as *mut u8, size);
        base64::decode_config(sl, base64::URL_SAFE_NO_PAD).unwrap()
    };
    return env.byte_array_from_slice(&result).unwrap();
}

#[no_mangle]
pub extern "system" fn Java_com_komanov_jwt_base64_jni_Native_decodeConfigUrlSafe4(
    env: JNIEnv,
    _class: JClass,
    payload: jbyteArray,
    size: usize,
) -> jbyteArray {
    let result = unsafe {
        let arr = env.get_primitive_array_critical(payload, ReleaseMode::NoCopyBack).unwrap();
        let sl = std::slice::from_raw_parts(arr.as_ptr() as *mut u8, size);
        base64::decode_config(sl, base64::URL_SAFE_NO_PAD).unwrap()
    };
    return env.byte_array_from_slice(&result).unwrap();
}

#[no_mangle]
pub extern "system" fn Java_com_komanov_jwt_base64_jni_Native_decodeConfigSliceUrlSafe1(
    env: JNIEnv,
    _class: JClass,
    payload: jbyteArray,
    size: usize,
) -> jbyteArray {
    let mut buffer = Vec::<u8>::with_capacity(size * 3 / 4);
    unsafe {
        let arr = env.get_byte_array_elements(payload, ReleaseMode::NoCopyBack).unwrap();
        let input_slice = std::slice::from_raw_parts(arr.as_ptr() as *const u8, size);
        let mut output_slice = std::slice::from_raw_parts_mut(buffer.as_mut_ptr(), buffer.capacity());
        let size = base64::decode_config_slice(input_slice, base64::URL_SAFE_NO_PAD, &mut output_slice).unwrap();
        assert_eq!(size, buffer.capacity());
        buffer.set_len(size);
    }
    return env.byte_array_from_slice(&buffer).unwrap();
}

#[no_mangle]
pub extern "system" fn Java_com_komanov_jwt_base64_jni_Native_decodeConfigSliceUrlSafe2(
    env: JNIEnv,
    _class: JClass,
    payload: jbyteArray,
    size: usize,
    output: *mut u8,
    output_size: usize,
) -> usize {
    unsafe {
        let arr = env.get_byte_array_elements(payload, ReleaseMode::NoCopyBack).unwrap();
        let input_slice = std::slice::from_raw_parts(arr.as_ptr() as *const u8, size);
        let output_slice = std::slice::from_raw_parts_mut(output, output_size);
        return base64::decode_config_slice(input_slice, base64::URL_SAFE_NO_PAD, output_slice).unwrap();
    }
}

#[no_mangle]
pub extern "system" fn Java_com_komanov_jwt_base64_jni_Native_decodeConfigSliceUrlSafe3(
    _env: JNIEnv,
    _class: JClass,
    input: *const u8,
    input_size: usize,
    output: *mut u8,
    output_size: usize,
) -> usize {
    unsafe {
        let input_slice = std::slice::from_raw_parts(input, input_size);
        let output_slice = std::slice::from_raw_parts_mut(output, output_size);
        return base64::decode_config_slice(input_slice, base64::URL_SAFE_NO_PAD, output_slice).unwrap();
    }
}
