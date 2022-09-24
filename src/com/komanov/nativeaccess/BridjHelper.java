package com.komanov.nativeaccess;

import org.bridj.BridJ;
import org.bridj.CRuntime;
import org.bridj.Pointer;
import org.bridj.ann.Library;
import org.bridj.ann.Name;
import org.bridj.ann.Runtime;

import static com.komanov.nativeaccess.Helper.ensureResultCode;

public abstract class BridjHelper {
    private BridjHelper() {
    }

    private static final LibC libC = new LibC();

    public static double getLoadAverage() {
        Pointer<Double> p = Pointer.allocateDoubles(3);
        ensureResultCode(libC.getloadavg(p, 3));
        return p.getDouble();
    }

    @Library("c")
    @Runtime(CRuntime.class)
    private static class LibC {
        static {
            BridJ.register();
        }

        @Name("getloadavg")
        public native int getloadavg(Pointer<Double> r, int num);
    }
}
