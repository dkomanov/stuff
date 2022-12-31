package com.komanov.compression;

public enum CompressionAlgorithms {
    gzip(CommonsCompression.GZIP),
    deflate(CommonsCompression.DEFLATE),

    // MySQL COMPRESS/UNCOMPRESS style: 4 bytes of uncompressed size + deflated.
    deflateWithSize(DeflatePlusSize.INSTANCE) {
        public boolean isOptimization() {
            return true;
        }
    },

    zstd(CommonsCompression.ZSTANDARD),
    snappy(SnappyCompressionAlgorithm.INSTANCE),

    // https://github.com/hyperxpro/Brotli4j
    brotli_0(Brotli.BR_0),
    brotli_6(Brotli.BR_6),
    brotli_11(Brotli.BR_11),

    // https://github.com/lz4/lz4-java
    lz4_fast(Lz4.FAST),
    lz4_high9(Lz4.HIGH_9),
    lz4_high17(Lz4.HIGH_17),
    ;

    public final CompressionAlgorithm algorithm;

    CompressionAlgorithms(CompressionAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

    public byte[] encode(byte[] data) throws Throwable {
        return algorithm.encode(data);
    }

    public byte[] decode(byte[] encoded) throws Throwable {
        return algorithm.decode(encoded);
    }

    public boolean isOptimization() {
        return false;
    }
}
