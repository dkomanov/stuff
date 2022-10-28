package com.komanov.nativeaccess;

import one.nalim.Link;
import one.nalim.Linker;

import static com.komanov.nativeaccess.Helper.ensureResultCode;

public abstract class NalimHelper {
    public static double getLoadAverageDirect() {
        double[] r = new double[3];
        ensureResultCode(Libc.getloadavg(r, r.length));
        return r[0];
    }

    public static double getLoadAverageViaWrapper() {
        double[] r = new double[3];
        ensureResultCode(GetLoadAvgLib.raw_getloadavg(r, r.length));
        return r[0];
    }

    private static class Libc {
        @Link
        public static native int getloadavg(double[] result, int num);

        static {
            Linker.linkClass(Libc.class);
        }
    }

    private static class GetLoadAvgLib {
        @Link
        public static native int raw_getloadavg(double[] result, int num);

        static {
            System.loadLibrary("getloadavg_lib");
            Linker.linkClass(GetLoadAvgLib.class);
        }
    }
}
