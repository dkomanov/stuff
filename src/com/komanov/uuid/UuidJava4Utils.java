package com.komanov.uuid;

import java.util.UUID;

/**
 * Replaced Character.digit
 */
public class UuidJava4Utils
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
        long mostSigBits = parseLong(s, 0, component1EndIndex);
        mostSigBits <<= 16;
        mostSigBits |= parseLong(s, component1EndIndex + 1, component2EndIndex);
        mostSigBits <<= 16;
        mostSigBits |= parseLong(s, component2EndIndex + 1, component3EndIndex);

        long leastSigBits = parseLong(s, component3EndIndex + 1, component4EndIndex);
        leastSigBits <<= 48;
        leastSigBits |= parseLong(s, component4EndIndex + 1, s.length());

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

    private static long parseLong(String s, final int from, final int to) throws NumberFormatException
    {
        if (s == null)
        {
            throw new NumberFormatException("null");
        }

        int radix = 16;

        long result = 0;
        boolean negative = false;
        int i = from;
        long limit = -Long.MAX_VALUE;
        long multmin;
        int digit;

        if (from >= to)
        {
            throw new NumberFormatException(s.substring(from, to));
        }

        char firstChar = s.charAt(from);
        if (firstChar < '0')
        { // Possible leading "+" or "-"
            if (firstChar == '-')
            {
                negative = true;
                limit = Long.MIN_VALUE;
            }
            else if (firstChar != '+')
                throw new NumberFormatException(s.substring(from, to));

            if (to == 1) // Cannot have lone "+" or "-"
                throw new NumberFormatException(s.substring(from, to));
            i++;
        }
        multmin = limit / radix;
        while (i < to)
        {
            // Accumulating negatively avoids surprises near MAX_VALUE
            digit = DigitResolver.digit(s.charAt(i++));
            if (digit < 0)
            {
                throw new NumberFormatException(s.substring(from, to));
            }
            if (result < multmin)
            {
                throw new NumberFormatException(s.substring(from, to));
            }
            result *= radix;
            if (result < limit + digit)
            {
                throw new NumberFormatException(s.substring(from, to));
            }
            result -= digit;
        }

        return negative ? result : -result;
    }
}
