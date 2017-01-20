package com.komanov.uuid;

public class DigitResolver
{
    private static final int[] digits = calculateDigits();

    public static int digit(char ch)
    {
        return ch >= digits.length ? -1 : digits[ch];
    }

    private static int[] calculateDigits()
    {
        int[] result = new int[(int) 'f' + 1];
        for (int i = 0; i < result.length; ++i)
        {
            result[i] = -1;
        }

        for (char ch = '0'; ch <= '9'; ++ch)
        {
            result[ch] = ch - '0';
        }

        for (char ch = 'A'; ch <= 'F'; ++ch)
        {
            result[ch] = 10 + (ch - 'A');
        }

        for (char ch = 'a'; ch <= 'f'; ++ch)
        {
            result[ch] = 10 + (ch - 'a');
        }

        return result;
    }
}
