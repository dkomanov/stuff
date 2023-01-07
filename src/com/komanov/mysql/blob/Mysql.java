package com.komanov.mysql.blob;

import com.komanov.compression.DeflatePlusSize;

import java.util.zip.DataFormatException;

public class Mysql {
    public static String URL = "jdbc:mysql://localhost:3306/blob_db?cacheServerConfiguration=true&createDatabaseIfNotExist=false";
    public static String USER = "debian-sys-maint";
    public static String PASSWORD = "";

    public static byte[] mysqlCompress(byte[] arr) {
        try {
            return DeflatePlusSize.INSTANCE.encode(arr);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] mysqlDecompress(byte[] arr) throws DataFormatException {
        try {
            return DeflatePlusSize.INSTANCE.decode(arr);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
