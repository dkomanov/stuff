package com.komanov.stringformat;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public class FastStringFactory {

    private static final MethodHandle stringBuilderValueGetter = getFieldHandler(getValueField());
    private static final MethodHandle stringBuilderCoderGetter = getFieldHandler(getCoderField());
    private static final MethodHandle newString = getNewStringHandler();

    public static String fastNewString(StringBuilder sb) throws Throwable {
        if (sb.capacity() != sb.length()) {
            throw new IllegalArgumentException("Expected filled StringBuilder!");
        }

        return fastNewString(
                (byte[]) stringBuilderValueGetter.invoke(sb),
                (byte) stringBuilderCoderGetter.invoke(sb)
        );
    }

    public static String fastNewString(byte[] value, byte coder) throws Throwable {
        return (String) newString.invokeExact(value, coder);
    }

    private static MethodHandle getFieldHandler(Field field) {
        try {
            return MethodHandles.lookup().unreflectGetter(field);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Field getValueField() {
        try {
            Field field = StringBuilder.class.getSuperclass().getDeclaredField("value");
            field.setAccessible(true);
            return field;
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    private static Field getCoderField() {
        try {
            Field field = StringBuilder.class.getSuperclass().getDeclaredField("coder");
            field.setAccessible(true);
            return field;
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    private static MethodHandle getNewStringHandler() {
        try {
            Constructor<String> constructor = String.class.getDeclaredConstructor(byte[].class, byte.class);
            constructor.setAccessible(true);
            return MethodHandles.lookup().unreflectConstructor(constructor);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
