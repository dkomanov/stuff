package com.komanov.compression;

import org.apache.commons.compress.compressors.CompressorInputStream;
import org.apache.commons.compress.compressors.CompressorOutputStream;
import org.apache.commons.compress.compressors.CompressorStreamFactory;
import org.apache.commons.compress.utils.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class CommonsCompression implements CompressionAlgorithm {
    private final String algorithm;

    private CommonsCompression(String algorithm) {
        this.algorithm = algorithm;
    }

    public static final CommonsCompression GZIP = new CommonsCompression(CompressorStreamFactory.GZIP);
    public static final CommonsCompression DEFLATE = new CommonsCompression(CompressorStreamFactory.DEFLATE);
    public static final CommonsCompression ZSTANDARD = new CommonsCompression(CompressorStreamFactory.ZSTANDARD);
    // It doesn't work! decode on large inputs (64K+) may just return empty array!!!
    public static final CommonsCompression SNAPPY = new CommonsCompression(CompressorStreamFactory.SNAPPY_FRAMED);

    @Override
    public byte[] encode(byte[] data) throws Throwable {
        ByteArrayOutputStream baos = new ByteArrayOutputStream(data.length / 2);
        try (CompressorOutputStream compressor = CompressorStreamFactory.getSingleton().createCompressorOutputStream(algorithm, baos)) {
            compressor.write(data);
            compressor.close();
            return baos.toByteArray();
        }
    }

    @Override
    public byte[] decode(byte[] encoded) throws Throwable {
        try (CompressorInputStream s = CompressorStreamFactory.getSingleton().createCompressorInputStream(algorithm, new ByteArrayInputStream(encoded))) {
            return IOUtils.toByteArray(s);
        }
    }
}
