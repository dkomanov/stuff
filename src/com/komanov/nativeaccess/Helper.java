package com.komanov.nativeaccess;

class Helper {
    public static void ensureResultCode(int code) {
        if (code < 1) {
            throw new IllegalStateException("getloadavg returned: " + code);
        }
    }
}
