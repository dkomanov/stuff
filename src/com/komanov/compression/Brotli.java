package com.komanov.compression;

import com.aayushatharva.brotli4j.Brotli4jLoader;
import com.aayushatharva.brotli4j.decoder.Decoder;
import com.aayushatharva.brotli4j.decoder.DecoderJNI;
import com.aayushatharva.brotli4j.decoder.DirectDecompress;
import com.aayushatharva.brotli4j.encoder.Encoder;

public class Brotli implements CompressionAlgorithm {
    static {
        Brotli4jLoader.ensureAvailability();
    }

    private final Encoder.Parameters parameters;

    public static Brotli BR_0 = new Brotli(new Encoder.Parameters().setQuality(0));
    public static Brotli BR_6 = new Brotli(new Encoder.Parameters().setQuality(6));
    public static Brotli BR_11 = new Brotli(new Encoder.Parameters().setQuality(11));

    private Brotli(Encoder.Parameters parameters) {
        this.parameters = parameters;
    }

    public byte[] encode(byte[] data) throws Throwable {
        return com.aayushatharva.brotli4j.encoder.Encoder.compress(data, parameters);
    }

    public byte[] decode(byte[] encoded) throws Throwable {
        DirectDecompress result = Decoder.decompress(encoded);
        assert result.getResultStatus() == DecoderJNI.Status.DONE;
        return result.getDecompressedData();
    }
}
