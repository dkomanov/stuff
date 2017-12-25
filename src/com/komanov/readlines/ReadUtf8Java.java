package com.komanov.readlines;

import java.util.Arrays;

public class ReadUtf8Java {

    public static String sequentialLoop(byte[] bytes) {
        MyCharBuffer buffer = new MyCharBuffer(bytes.length);
        int cp = 0;
        int bytesMore = 0;

        for (int c : bytes) {
            if (c > 0) {
                if (bytesMore != 0) {
                    buffer.putBadChar();
                    bytesMore = 0;
                }

                buffer.putSingle(c);
            } else {
                if (bytesMore == 0) {
                    if ((c & 0xE0) == 0xC0) {
                        cp = c & 0x1F;
                        bytesMore = 1;
                    } else if ((c & 0xF0) == 0xE0) {
                        cp = c & 0x0F;
                        bytesMore = 2;
                    } else if ((c & 0xF8) == 0xF0) {
                        cp = c & 0x07;
                        bytesMore = 3;
                    } else {
                        buffer.putBadChar();
                    }
                } else {
                    cp = (cp << 6) | (c & 0x3F);
                    if (--bytesMore == 0) {
                        buffer.putPoint(cp);
                        cp = 0;
                    }
                }
            }
        }

        if (cp != 0 || bytesMore != 0) {
            buffer.putBadChar();
        }

        return buffer.toString();
    }

    public static String changeIndexInsideLoop(byte[] bytes) {
        MyCharBuffer buffer = new MyCharBuffer(bytes.length);
        int i = 0;
        int length = bytes.length;
        while (i < length) {
            int c = bytes[i];

            if (c > 0) {
                buffer.putSingle(c);
                i += 1;
            } else {
                if ((c & 0xE0) == 0xC0) {
                    assert i + 1 < bytes.length;
                    int b1 = c & 0x1F;
                    int b2 = (b1 << 6) | ((int) bytes[i + 1] & 0x3F);
                    buffer.putSingle(b2);
                    i += 2;
                } else if ((c & 0xF0) == 0xE0) {
                    assert i + 2 < bytes.length;
                    int b1 = c & 0x0F;
                    int b2 = (b1 << 6) | ((int) bytes[i + 1] & 0x3F);
                    int b3 = (b2 << 6) | ((int) bytes[i + 2] & 0x3F);
                    buffer.putSingle(b3);
                    i += 3;
                } else if ((c & 0xF8) == 0xF0) {
                    assert i + 3 < bytes.length;
                    int b1 = c & 0x0F;
                    int b2 = (b1 << 6) | ((int) bytes[i + 1] & 0x3F);
                    int b3 = (b2 << 6) | ((int) bytes[i + 2] & 0x3F);
                    int b4 = (b3 << 6) | ((int) bytes[i + 3] & 0x3F);
                    buffer.putPoint(b4);
                    i += 4;
                } else {
                    buffer.putBadChar();
                    i += 1;
                }
            }
        }

        return buffer.toString();
    }

    public static String changeIndexInsideLoopByteMagic(byte[] bytes) {
        MyCharBuffer buffer = new MyCharBuffer(bytes.length);

        int i = 0;
        int length = bytes.length;
        while (i < length) {
            int b1 = bytes[i];

            if (b1 > 0) {
                buffer.putSingle(b1);
                i += 1;
            } else {
                if ((b1 >> 5) == -2 && (b1 & 0x1e) != 0) {
                    assert i + 1 < bytes.length;
                    int b2 = bytes[i + 1];
                    buffer.putSingle((char) (
                            ((b1 << 6) ^ b2) ^ (((byte) 0xC0 << 6) ^ ((byte) 0x80 << 0))
                    ));
                    i += 2;
                } else if ((b1 >> 4) == -2) {
                    assert i + 2 < bytes.length;
                    int b2 = bytes[i + 1];
                    int b3 = bytes[i + 2];
                    buffer.putSingle(
                            (char)
                                    ((b1 << 12) ^
                                            (b2 << 6) ^
                                            (b3 ^
                                                    (((byte) 0xE0 << 12) ^
                                                            ((byte) 0x80 << 6) ^
                                                            ((byte) 0x80 << 0))))
                    );
                    i += 3;
                } else if ((b1 >> 3) == -2) {
                    assert i + 3 < bytes.length;
                    int b2 = bytes[i + 1];
                    int b3 = bytes[i + 2];
                    int b4 = bytes[i + 3];
                    buffer.putDouble(
                            ((b1 << 18) ^
                                    (b2 << 12) ^
                                    (b3 << 6) ^
                                    (b4 ^
                                            (((byte) 0xF0 << 18) ^
                                                    ((byte) 0x80 << 12) ^
                                                    ((byte) 0x80 << 6) ^
                                                    ((byte) 0x80 << 0))))
                    );
                    i += 4;
                } else {
                    buffer.putBadChar();
                    i += 1;
                }
            }
        }

        return buffer.toString();
    }

    private static class MyCharBuffer {
        private char[] buffer;
        private int count = 0;

        public MyCharBuffer(int len) {
            this.buffer = new char[(int) (len * 1.1f)];
        }

        @Override
        public String toString() {
            return new String(buffer, 0, count);
        }

        public void putSingle(int codePoint) {
            ensureLength(1);
            buffer[count] = (char) codePoint;
            count += 1;
        }

        public void putDouble(int codePoint) {
            ensureLength(2);
            buffer[count + 0] = Character.highSurrogate(codePoint);
            buffer[count + 1] = Character.lowSurrogate(codePoint);
            count += 2;
        }

        public void putPoint(int codePoint) {
            if (Character.isBmpCodePoint(codePoint)) {
                putSingle(codePoint);
            } else if (Character.isValidCodePoint(codePoint)) {
                putDouble(codePoint);
            } else {
                throw new IllegalStateException();
            }
        }

        public void putBadChar() {
            putPoint(0xFFFD);
        }

        private void ensureLength(int len) {
            if (count + len > buffer.length) {
                buffer = Arrays.copyOf(buffer, buffer.length + 100);
            }
        }
    }

}
