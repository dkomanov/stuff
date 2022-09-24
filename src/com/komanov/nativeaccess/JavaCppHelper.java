package com.komanov.nativeaccess;

import org.bytedeco.javacpp.linux;

import static com.komanov.nativeaccess.Helper.ensureResultCode;

public abstract class JavaCppHelper {
    private JavaCppHelper() {
    }

    public static double getLoadAverage() {
        double[] r = new double[3];
        ensureResultCode(linux.getloadavg(r, r.length));
        return r[0];
    }
}
