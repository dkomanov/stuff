package com.komanov.uuid;

import java.util.UUID;

/**
 * Backward incompatible version that allows strictly fixed format which is returned by UUID.toString
 */
public class UuidJavaEpicUtils
{
    public static UUID fromStringFast(String s)
    {
        validate(s);

        int component1EndIndex = 8;
        int component2EndIndex = 13;
        int component3EndIndex = 18;
        int component4EndIndex = 23;

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

    private static void validate(String s)
    {
        if (s.length() != 36)
        {
            throw new IllegalArgumentException("Illegal UUID " + s);
        }
        if (s.charAt(8) != '-' && s.charAt(13) != '-' && s.charAt(18) != '-' && s.charAt(23) != '-')
        {
            throw new IllegalArgumentException("Expected 4 hyphens (-) in a string: " + s);
        }
    }

    private static long parseHex(final String s, final int from, final int to)
    {
        long result = 0;
        for (int i = from; i < to; i++)
        {
            // Accumulating negatively avoids surprises near MAX_VALUE
            char ch = s.charAt(i);
            final int digit = DigitResolver.digit(ch);
            if (digit < 0)
            {
                throw new NumberFormatException(s.substring(from, to));
            }

            result = result * 16 + digit;
        }

        return result;
    }
}
