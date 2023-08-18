package org.kie.kogito.correlation;

public interface CorrelationEncoder {

    String encode(Correlation<?> correlation);
}
