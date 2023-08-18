package org.kie.kogito.event.correlation;

import java.util.Set;

import org.junit.jupiter.api.Test;
import org.kie.kogito.correlation.CompositeCorrelation;
import org.kie.kogito.correlation.Correlation;
import org.kie.kogito.correlation.SimpleCorrelation;

import static org.assertj.core.api.Assertions.assertThat;

class MD5CorrelationEncoderTest {

    private MD5CorrelationEncoder encoder = new MD5CorrelationEncoder();

    @Test
    public void testEncodeWithSimpleCorrelation() {
        Correlation<String> simpleCorrelation = new SimpleCorrelation<>("aaaa", "bbbb");
        String encode = encoder.encode(simpleCorrelation);
        assertThat(encode).isEqualTo("c818ccd6be2b10823eb7208d162879d0");//md5(aaaa|bbbb)
    }

    @Test
    public void testEncodeWithCompositeCorrelation() {
        Correlation<String> correlation1 = new SimpleCorrelation<>("aaaa", "bbbb");
        Correlation<String> correlation2 = new SimpleCorrelation<>("cccc", "dddd");
        Correlation<String> correlation3 = new SimpleCorrelation<>("eeee", "ffff");
        String encode = encoder.encode(new CompositeCorrelation(Set.of(correlation1, correlation2, correlation3)));
        assertThat(encode).isEqualTo("fc5428e42b043a7089d7a15d65558ca2");//md5(aaaa|bbbb|cccc|dddd|eeee|ffff)
    }
}
