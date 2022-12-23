use base64;
use base64_simd;
use std;
use std::slice::{from_raw_parts, from_raw_parts_mut};

/*
ENCODE
 */

#[no_mangle]
pub extern "system" fn encodeConfigSlice_NativeBazel(
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

#[no_mangle]
pub extern "system" fn encodeSimd_NativeBazel(
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

/*
DECODE
 */

#[no_mangle]
pub extern "system" fn decodeConfigSlice_NativeBazel(
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
pub extern "system" fn decodeSimd_NativeBazel(
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
pub extern "system" fn decodeSimdInPlace_NativeBazel(
    input: *mut u8,
    input_size: usize,
) -> usize {
    let output_slice = unsafe {
        from_raw_parts_mut(input, input_size)
    };
    let result = base64_simd::URL_SAFE_NO_PAD.decode_inplace(output_slice).unwrap();
    result.len()
}
