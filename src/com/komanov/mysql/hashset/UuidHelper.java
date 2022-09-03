package com.komanov.mysql.hashset;

import java.nio.ByteBuffer;
import java.util.UUID;

public abstract class UuidHelper {
    private UuidHelper() {
    }

    public static byte[] toBytes(UUID value) {
        byte[] array = new byte[16];
        ByteBuffer bb = ByteBuffer.wrap(array);
        bb.putLong(value.getMostSignificantBits());
        bb.putLong(value.getLeastSignificantBits());
        return array;
    }

    public static UUID fromBytes(byte[] array) {
        ByteBuffer bb = ByteBuffer.wrap(array);
        return new UUID(bb.getLong(), bb.getLong());
    }
}
