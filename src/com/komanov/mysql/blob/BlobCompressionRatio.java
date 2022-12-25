package com.komanov.mysql.blob;

import java.nio.charset.StandardCharsets;
import java.util.Random;

public enum BlobCompressionRatio {
    LOW_COMPRESSION_1_3 {
        public byte[] generateBlob(int length) {
            return generateImpl(COMP_1_3, length);
        }
    },

    MEDIUM_COMPRESSION_2_1 {
        public byte[] generateBlob(int length) {
            return generateImpl(COMP_2_1, length);
        }
    },

    HIGH_COMPRESSION_3_4 {
        public byte[] generateBlob(int length) {
            return generateImpl(COMP_3_4, length);
        }
    },

    EXTRA_HIGH_COMPRESSION_6_2 {
        public byte[] generateBlob(int length) {
            return generateImpl(COMP_6_2, length);
        }
    },
    ;

    public abstract byte[] generateBlob(int length);

    private static final Random rnd = new Random();
    private static final byte[] COMP_6_2 = "01".getBytes(StandardCharsets.US_ASCII);
    private static final byte[] COMP_3_4 = "0123".getBytes(StandardCharsets.US_ASCII);
    private static final byte[] COMP_2_1 = "0123456789".getBytes(StandardCharsets.US_ASCII);
    private static final byte[] COMP_1_3 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".getBytes(StandardCharsets.US_ASCII);

    private static byte[] generateImpl(byte[] alphabet, int length) {
        byte[] result = new byte[length];
        for (int i = 0; i < result.length; i++) {
            result[i] = alphabet[rnd.nextInt(alphabet.length)];
        }
        return result;
    }
}
