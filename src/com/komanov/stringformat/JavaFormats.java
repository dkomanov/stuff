package com.komanov.stringformat;

import org.slf4j.helpers.MessageFormatter;

import java.text.MessageFormat;
import java.util.Locale;

public class JavaFormats {
    public static String concat(int value1, String value2, Object nullObject) {
        return value1 + "a" + value2 + "b" + value2 + nullObject;
    }

    public static String stringFormat(int value1, String value2, Object nullObject) {
        return String.format(Locale.ENGLISH, "%da%sb%s%s", value1, value2, value2, nullObject);
    }

    public static String messageFormat(int value1, String value2, Object nullObject) {
        return MessageFormat.format("{0,number,#}a{1}b{2}{3}", value1, value2, value2, nullObject);
    }

    public static String slf4j(int value1, String value2, Object nullObject) {
        return MessageFormatter
                .arrayFormat("{}a{}b{}{}", new Object[]{value1, value2, value2, nullObject})
                .getMessage();
    }
}
