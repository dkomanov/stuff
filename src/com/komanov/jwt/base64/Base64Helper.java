package com.komanov.jwt.base64;

import static org.apache.commons.codec.binary.BaseNCodec.MIME_CHUNK_SIZE;

public abstract class Base64Helper {
    private Base64Helper() {
    }

    public static final class Jdk {
        private Jdk() {
        }

        private static final java.util.Base64.Decoder regularDecoder = java.util.Base64.getDecoder();
        private static final java.util.Base64.Decoder urlSafeDecoder = java.util.Base64.getUrlDecoder();

        private static final java.util.Base64.Encoder regularEncoder = java.util.Base64.getEncoder();
        private static final java.util.Base64.Encoder urlSafeEncoder = java.util.Base64.getUrlEncoder().withoutPadding();

        public static byte[] encode(byte[] data) {
            return regularEncoder.encode(data);
        }

        public static byte[] encodeUrlSafe(byte[] data) {
            return urlSafeEncoder.encode(data);
        }

        public static byte[] decode(byte[] data) {
            return regularDecoder.decode(data);
        }

        public static byte[] decodeUrlSafe(byte[] data) {
            return urlSafeDecoder.decode(data);
        }
    }

    public static class Commons {
        private Commons() {
        }

        private static final org.apache.commons.codec.binary.Base64 regular = new org.apache.commons.codec.binary.Base64(MIME_CHUNK_SIZE, null, false);
        private static final org.apache.commons.codec.binary.Base64 urlSafe = new org.apache.commons.codec.binary.Base64(MIME_CHUNK_SIZE, null, true);

        public static byte[] encode(byte[] data) {
            return regular.encode(data);
        }

        public static byte[] encodeUrlSafe(byte[] data) {
            return urlSafe.encode(data);
        }

        public static byte[] decode(byte[] data) {
            return regular.decode(data);
        }

        public static byte[] decodeUrlSafe(byte[] data) {
            return urlSafe.decode(data);
        }
    }
}
