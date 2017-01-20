package com.komanov.uuid;

import java.util.UUID;

/* a version by Noam */
public class UuidJavaEpic2Utils
{
    public static UUID fromStringFast(String s)
    {
        validate(s);
        final int component3EndIndex = 18;
        final long mostSigBits = parseHex(s, 0, component3EndIndex);
        final long leastSigBits = parseHex(s, component3EndIndex + 1, s.length());
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
            char ch = s.charAt(i);
            if (ch == '-') continue;
            final int digit = Character.digit(ch, 16);
            if (digit < 0)
                throw new NumberFormatException(s.substring(from, to));
            result = result * 16 + digit;
        }

        return result;
    }
}
