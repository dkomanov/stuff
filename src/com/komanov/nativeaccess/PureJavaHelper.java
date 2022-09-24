package com.komanov.nativeaccess;

import java.lang.management.ManagementFactory;

public abstract class PureJavaHelper {
    private PureJavaHelper() {
    }

    public static double getLoadAverage() {
        return ManagementFactory.getOperatingSystemMXBean().getSystemLoadAverage();
    }

}
