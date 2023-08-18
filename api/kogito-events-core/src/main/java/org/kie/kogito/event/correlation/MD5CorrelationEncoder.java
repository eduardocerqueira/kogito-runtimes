package org.kie.kogito.event.correlation;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.StringJoiner;

import org.kie.kogito.correlation.CompositeCorrelation;
import org.kie.kogito.correlation.Correlation;
import org.kie.kogito.correlation.CorrelationEncoder;

import static java.util.stream.Collectors.joining;

public class MD5CorrelationEncoder implements CorrelationEncoder {

    @Override
    public String encode(Correlation<?> correlation) {
        try {
            String rawCorrelationString = encodeCorrelation(correlation);
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(rawCorrelationString.getBytes());
            return bytesToHex(md.digest());
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }
    }

    private String encodeCorrelation(Correlation<?> correlation) {
        if (correlation instanceof CompositeCorrelation) {
            CompositeCorrelation compositeCorrelation = (CompositeCorrelation) correlation;
            return compositeCorrelation.getValue().stream().map(this::encodeCorrelation).sorted().collect(joining("|"));
        }
        return new StringJoiner("|").add(correlation.getKey()).add(correlation.asString()).toString();
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
