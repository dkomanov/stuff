package com.komanov.mysql.blob;

import java.io.ByteArrayOutputStream;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import static com.komanov.mysql.blob.IntUtils.readInt;
import static com.komanov.mysql.blob.IntUtils.writeInt;
import static com.komanov.mysql.blob.TlBuffer.getBuffer;

public class Mysql {
    public static String URL = "jdbc:mysql://localhost:3306/blob_db?cacheServerConfiguration=true&createDatabaseIfNotExist=false";
    public static String USER = "debian-sys-maint";
    public static String PASSWORD = "";

    // Inspired by https://stackoverflow.com/questions/39460906/java-emulation-of-mysql-comress-decompress-functions
    // https://dev.mysql.com/doc/refman/8.0/en/encryption-functions.html#function_compress

    public static byte[] mysqlCompress(byte[] arr) {
        if (arr.length == 0) {
            return arr;
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream(arr.length);

        writeInt(arr.length, baos);

        Deflater deflater = new Deflater();
        deflater.setInput(arr);
        deflater.finish();

        byte[] buffer = getBuffer(8 * 1024);
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            baos.write(buffer, 0, count);
        }

        return baos.toByteArray();
    }

    public static byte[] mysqlDecompress(byte[] arr) throws DataFormatException {
        if (arr.length == 0) {
            return arr;
        }

        int len = readInt(arr);

        byte[] result = new byte[len];
        Inflater inflater = new Inflater();
        inflater.setInput(arr, 4, arr.length - 4);
        int offset = 0;
        byte[] buffer = getBuffer(8 * 1024);
        while (!inflater.finished()) {
            int count = inflater.inflate(buffer);
            System.arraycopy(buffer, 0, result, offset, count);
            offset += count;
        }
        if (offset != len) {
            throw new IllegalStateException("Expected len " + len + " != " + offset);
        }
        return result;
    }
}
