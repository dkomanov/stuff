use jni::JNIEnv;
use jni::objects::JClass;
use jni::sys::{jint, jlong};
use libc::getloadavg;

#[no_mangle]
pub extern "system" fn Java_com_komanov_nativeaccess_JniHelper_getloadavg(_env: JNIEnv,
                                                                          _class: JClass,
                                                                          input: *mut f64,
                                                                          num: jint)
                                                                          -> jint {
    unsafe {
        return getloadavg(input, num);
    }
}
