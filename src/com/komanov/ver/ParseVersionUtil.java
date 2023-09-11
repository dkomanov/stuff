package com.komanov.ver;

public abstract class ParseVersionUtil {

    private ParseVersionUtil() {
    }

    public static final int MaxVersionSize = Version$.MODULE$.MaxVersionSize();

    public static Version parse(String v) {
        final int len = v.length();
        int major = -1;
        int minor = -1;
        int lastDotIndex = -1;

        for (int i = 0; i < len; ++i) {
            char ch = v.charAt(i);
            switch (ch) {
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    if (i - (lastDotIndex + 1) > 5) {
                        return null; // longer than MaxVersionSize
                    }
                    break;

                case '.':
                    if (major == -1) {
                        int parsed = parseIntSafeEmpty(v, 0, i);
                        if (parsed == -1 || parsed > MaxVersionSize) {
                            return null;
                        }
                        major = parsed;
                    } else if (minor == -1) {
                        int parsed = parseIntSafeEmpty(v, lastDotIndex + 1, i);
                        if (parsed == -1 || parsed > MaxVersionSize) {
                            return null;
                        }
                        minor = parsed;
                    } else {
                        return null; // 3rd dot
                    }
                    lastDotIndex = i;
                    break;

                default:
                    return null; // invalid character
            }
        }

        if (major != -1 && minor != -1) {
            int parsed = parseIntSafeEmpty(v, lastDotIndex + 1, len);
            if (parsed == -1 || parsed > MaxVersionSize) {
                return null;
            }
            return new Version(major, minor, parsed);
        } else {
            return null;
        }
    }

    public static Version parseNoSwitch(String v) {
        final int len = v.length();
        int major = -1;
        int minor = -1;
        int lastDotIndex = -1;

        for (int i = 0; i < len; ++i) {
            char ch = v.charAt(i);
            if (ch >= '0' && ch <= '9') {
                if (i - (lastDotIndex + 1) > 5) {
                    return null; // longer than MaxVersionSize
                }
            } else if (ch == '.') {
                if (major == -1) {
                    int parsed = parseIntSafeEmpty(v, 0, i);
                    if (parsed == -1 || parsed > MaxVersionSize) {
                        return null;
                    }
                    major = parsed;
                } else if (minor == -1) {
                    int parsed = parseIntSafeEmpty(v, lastDotIndex + 1, i);
                    if (parsed == -1 || parsed > MaxVersionSize) {
                        return null;
                    }
                    minor = parsed;
                } else {
                    return null; // 3rd dot
                }
                lastDotIndex = i;
            } else {
                return null; // invalid character
            }
        }

        if (major != -1 && minor != -1) {
            int parsed = parseIntSafeEmpty(v, lastDotIndex + 1, len);
            if (parsed == -1 || parsed > MaxVersionSize) {
                return null;
            }
            return new Version(major, minor, parsed);
        } else {
            return null;
        }
    }

    public static Version parseHardCore(String v) {
        final int len = v.length();
        int major = 0;
        int minor = 0;
        int fix = 0;
        int lastDotIndex = -1;
        int currentPart = 0;

        for (int i = 0; i < len; ++i) {
            char ch = v.charAt(i);
            switch (ch) {
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    int currentDigit = (int) ch - '0';
                    switch (currentPart) {
                        case 0:
                            major = major * 10 + currentDigit;
                            if (major > MaxVersionSize) {
                                return null;
                            }
                            break;
                        case 1:
                            minor = minor * 10 + currentDigit;
                            if (minor > MaxVersionSize) {
                                return null;
                            }
                            break;
                        case 2:
                            fix = fix * 10 + currentDigit;
                            if (fix > MaxVersionSize) {
                                return null;
                            }
                            break;

                        default:
                            throw new IllegalStateException("should never happen");
                    }

                    if (i - (lastDotIndex + 1) > 5) {
                        return null; // longer than MaxVersionSize
                    }
                    break;

                case '.':
                    if (lastDotIndex + 1 == i) {
                        return null; // ..
                    }

                    ++currentPart;
                    if (currentPart > 2) {
                        return null; // extra dot
                    }

                    lastDotIndex = i;
                    break;

                default:
                    return null; // invalid character
            }
        }

        return currentPart == 2 && lastDotIndex != len - 1 ? new Version(major, minor, fix) : null;
    }

    private static int parseNumberUntilDotOrLength(final String s, final int fromIndex, final int len) {
        int value = 0;
        int i = fromIndex;
        final int endIndex = Math.min(fromIndex + 1 + 5 + 1, len);
        while (i < endIndex) {
            char ch = s.charAt(i);
            int digit = ch - 48; // 48 == (int) '0'
            if (digit == -2) { // dot
                if (i == fromIndex) {
                    return -1;
                }

                if (value > MaxVersionSize) {
                    return -1;
                }

                return i + (value << 8);
            } else if (digit < 0 || digit > 9) {
                return -1;
            } else {
                value = value * 10 + digit;
                ++i;
            }
        }

        return i == fromIndex || value > MaxVersionSize ? -1 : i + (value << 8);
    }

    public static Version parseHardCore2(String v) {
        final int len = v.length();

        int majorValue = parseNumberUntilDotOrLength(v, 0, len);
        if (majorValue == -1) {
            return null;
        }

        int minorValue = parseNumberUntilDotOrLength(v, (majorValue & 0xff) + 1, len);
        if (minorValue == -1) {
            return null;
        }

        int fixValue = parseNumberUntilDotOrLength(v, (minorValue & 0xff) + 1, len);
        if (fixValue == -1) {
            return null;
        }

        if ((fixValue & 0xff) != len) {
            return null;
        }

        return new Version(majorValue >> 8, minorValue >> 8, fixValue >> 8);
    }

    public static Version parseHardCore3SingleLoop(String v) {
        final int len = v.length();

        int major = 0;
        int minor = 0;

        int current = 0;
        int dots = 0;
        int lastDotIndex = -1;

        for (int i = 0; i < len; ++i) {
            final char ch = v.charAt(i);
            int digit = ch - 48;
            if (digit == -2) {
                if (lastDotIndex + 1 == i) {
                    return null;
                }

                if (current < 0 || current > MaxVersionSize) {
                    return null;
                }

                switch (dots) {
                    case 0:
                        major = current;
                        dots = 1;
                        break;

                    case 1:
                        minor = current;
                        dots = 2;
                        break;

                    default:
                        return null;
                }

                lastDotIndex = i;
                current = 0;
            } else if (digit < 0 || digit > 9) {
                return null;
            } else {
                // overflow is possible!
                current = current * 10 + digit;
            }
        }

        if (dots != 2 || lastDotIndex == len - 1) {
            return null;
        }
        if (current < 0 || current > MaxVersionSize) {
            return null;
        }

        return new Version(major, minor, current);
    }

    public static int parseIntSafeEmpty(String s, int from, int to) {
        return from >= to ? -1 : Integer.parseUnsignedInt(s, from, to, 10);
    }

    /*value*/ public static class ValueVersion {
        private final long value;

        public static final ValueVersion INVALID = new ValueVersion(1L << (3 * 14 + 1));

        private ValueVersion(long value) {
            this.value = value;
        }

        public ValueVersion(int major, int minor, int fix) {
            this(asValue(major, minor, fix));
        }

        public static ValueVersion fromString(String v) {
            return new ValueVersion(VersionNoAlloc.parseOptimized6(v));
        }

        public boolean isInvalid() {
            return value == INVALID.value;
        }

        public int major() {
            return (int) ((value >> 28) & 16383);
        }

        public int minor() {
            return (int) ((value >> 14) & 16383);
        }

        public int fix() {
            return (int) (value & 16383);
        }

        private static long asValue(int major, int minor, int fix) {
            return (((long) (major & 16383)) << 28) |
                    (((long) (minor & 16383)) << 14) |
                    ((long) (fix & 16383));
        }
    }
}
