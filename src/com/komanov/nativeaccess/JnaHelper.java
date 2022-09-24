package com.komanov.nativeaccess;

import com.sun.jna.Native;
import com.sun.jna.platform.linux.LibC;

import static com.komanov.nativeaccess.Helper.ensureResultCode;

public abstract class JnaHelper {
    private JnaHelper() {
    }

    static {
        Native.register("c");
    }

    private native static int getloadavg(double[] r, int num);

    public static double getLoadAverage() {
        double[] r = new double[3];
        ensureResultCode(LibC.INSTANCE.getloadavg(r, r.length));
        return r[0];
    }

    public static double getLoadAverageDirect() {
        double[] r = new double[3];
        ensureResultCode(getloadavg(r, r.length));
        return r[0];
    }
}
