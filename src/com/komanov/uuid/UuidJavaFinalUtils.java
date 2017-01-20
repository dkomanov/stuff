package com.komanov.uuid;

import java.util.UUID;

/**
 * Optimized parseLong and own Character.digit.
 */
public class UuidJavaFinalUtils
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
        long mostSigBits = parseHex(s, 0, component1EndIndex);
        mostSigBits <<= 16;
        mostSigBits |= parseHex(s, component1EndIndex + 1, component2EndIndex);
        mostSigBits <<= 16;
        mostSigBits |= parseHex(s, component2EndIndex + 1, component3EndIndex);

        long leastSigBits = parseHex(s, component3EndIndex + 1, component4EndIndex);
        leastSigBits <<= 48;
        leastSigBits |= parseHex(s, component4EndIndex + 1, s.length());

        return new UUID(mostSigBits, leastSigBits);
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

    private static long parseHex(final String s, final int from, final int to)
            throws NumberFormatException
    {
        if (to <= from)
        {
            // empty string
            throw new NumberFormatException(s.substring(from, to));
        }

        if (to - from > 16)
        {
            // too big value
            throw new NumberFormatException(s.substring(from, to));
        }

        int i = from;
        long result = 0;

        while (i < to)
        {
            // Accumulating negatively avoids surprises near MAX_VALUE
            char ch = s.charAt(i++);
            final int digit = DigitResolver.digit(ch);
            if (digit < 0)
            {
                if (i == from && ch == '+')
                {
                    // plus sign is allowed for backward compatibility
                    continue;
                }

                throw new NumberFormatException(s.substring(from, to));
            }

            result = result * 16 + digit;
        }

        return result;
    }
}
