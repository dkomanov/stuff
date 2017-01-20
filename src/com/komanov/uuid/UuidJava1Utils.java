package com.komanov.uuid;

import java.util.UUID;

/**
 * Removed String.split
 */
public class UuidJava1Utils
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

        long mostSigBits = decode(s, 0, component1EndIndex);
        mostSigBits <<= 16;
        mostSigBits |= decode(s, component1EndIndex + 1, component2EndIndex);
        mostSigBits <<= 16;
        mostSigBits |= decode(s, component2EndIndex + 1, component3EndIndex);

        long leastSigBits = decode(s, component3EndIndex + 1, component4EndIndex);
        leastSigBits <<= 48;
        leastSigBits |= decode(s, component4EndIndex + 1, s.length());

        return new UUID(mostSigBits, leastSigBits);
    }

    private static long decode(String s, int from, int to)
    {
        return Long.decode("0x" + s.substring(from, to));
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
