package com.komanov.compression;

import java.io.ByteArrayOutputStream;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import static org.apache.commons.lang3.Conversion.byteArrayToInt;
import static org.apache.commons.lang3.Conversion.intToByteArray;

class DeflatePlusSize implements CompressionAlgorithm {
    public static final DeflatePlusSize INSTANCE = new DeflatePlusSize();
    private static final byte[] EMPTY = new byte[0];

    private DeflatePlusSize() {
    }

    @Override
    public byte[] encode(byte[] data) {
        if (data.length == 0) {
            // Inspired by https://stackoverflow.com/questions/39460906/java-emulation-of-mysql-comress-decompress-functions
            // https://dev.mysql.com/doc/refman/8.0/en/encryption-functions.html#function_compress
            return EMPTY;
        }

        Deflater deflater = new Deflater();
        try {
            deflater.setInput(data);
            deflater.finish();

            int bufferSize = 8 * 1024;
            byte[] buffer = LocalBuffer.getBuffer(bufferSize);

            ByteArrayOutputStream baos = new ByteArrayOutputStream(data.length);
            intToByteArray(data.length, 0, buffer, 0, 4);
            baos.write(buffer, 0, 4);

            while (!deflater.finished()) {
                int count = deflater.deflate(buffer, 0, bufferSize);
                baos.write(buffer, 0, count);
            }

            return baos.toByteArray();
        } finally {
            deflater.end();
        }
    }

    @Override
    public byte[] decode(byte[] encoded) throws Throwable {
        if (encoded.length == 0) {
            return EMPTY;
        }

        int size = byteArrayToInt(encoded, 0, 0, 0, 4);
        byte[] result = new byte[size];

        Inflater inflater = new Inflater();
        try {
            inflater.setInput(encoded, 4, encoded.length - 4);
            int offset = 0;
            final int chunkSize = Math.min(size, 8 * 1024);
            while (!inflater.finished()) {
                int count = inflater.inflate(result, offset, Math.min(size - offset, chunkSize));
                offset += count;
            }

            assert offset == size;

            return result;
        } finally {
            inflater.end();
        }
    }
}
