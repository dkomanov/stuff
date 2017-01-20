package com.komanov.uuid;

import java.util.UUID;

import static java.lang.Long.parseLong;

/**
 * Removed concatenation (used parseLong instead of decode).
 */
public class UuidJava2Utils
{
    public static UUID fromStringFast(String s)
    {
        int component1EndIndex = indexOfHyphen(s, 0);
        int component2EndIndex = indexOfHyphen(s, component1EndIndex + 1);
        int component3EndIndex = indexOfHyphen(s, component2EndIndex + 1);
        int component4EndIndex = indexOfHyphen(s, component3EndIndex + 1);
        if (s.indexOf('-', component4EndIndex + 1) != -1)
        {
            throw new IllegalArgumentException("Too much hyphens in a string: " + s);
        }

        // This is a copy-paste from UUID.fromString implementation
        long mostSigBits = substringAndParseLong(s, 0, component1EndIndex);
        mostSigBits <<= 16;
        mostSigBits |= substringAndParseLong(s, component1EndIndex + 1, component2EndIndex);
        mostSigBits <<= 16;
        mostSigBits |= substringAndParseLong(s, component2EndIndex + 1, component3EndIndex);

        long leastSigBits = substringAndParseLong(s, component3EndIndex + 1, component4EndIndex);
        leastSigBits <<= 48;
        leastSigBits |= substringAndParseLong(s, component4EndIndex + 1, s.length());

        return new UUID(mostSigBits, leastSigBits);
    }

    private static long substringAndParseLong(String s, int from, int to)
    {
        return parseLong(s.substring(from, to), 16);
    }

    private static int indexOfHyphen(String s, int from)
    {
        int index = s.indexOf('-', from);
        if (index == -1)
        {
            throw new IllegalArgumentException("Expected 4 hyphens (-) in a string: " + s);
        }
        return index;
    }
}
