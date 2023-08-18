package org.kie.kogito.monitoring.core.common.integration;

import java.util.stream.IntStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kie.kogito.KogitoGAV;
import org.kie.kogito.monitoring.core.common.system.metrics.dmnhandlers.DecisionConstants;
import org.kie.kogito.monitoring.core.common.system.metrics.dmnhandlers.StringHandler;

import io.micrometer.core.instrument.simple.SimpleMeterRegistry;

import static org.assertj.core.api.Assertions.assertThat;

public class StringHandlerTest {

    private static final String ENDPOINT_NAME = "hello";
    private static final String DECISION_NAME = "decision";
    SimpleMeterRegistry registry;
    StringHandler handler;
    String dmnType;

    @BeforeEach
    public void setUp() {
        dmnType = "string";
        registry = new SimpleMeterRegistry();
        handler = new StringHandler(dmnType, KogitoGAV.EMPTY_GAV, registry);
    }

    @AfterEach
    public void destroy() {
        registry.clear();
    }

    @Test
    public void givenSomeStringMetricsWhenMetricsAreStoredThenTheCountIsCorrect() {
        // Arrange
        Double expectedCountStringA = 3.0;
        Double expectedCountStringB = 2.0;
        Double expectedCountStringC = 5.0;

        // Act
        IntStream.rangeClosed(1, 3).forEach(x -> handler.record(DECISION_NAME, ENDPOINT_NAME, "A"));
        IntStream.rangeClosed(1, 2).forEach(x -> handler.record(DECISION_NAME, ENDPOINT_NAME, "B"));
        IntStream.rangeClosed(1, 5).forEach(x -> handler.record(DECISION_NAME, ENDPOINT_NAME, "C"));

        // Assert
        assertThat(registry.find(dmnType + DecisionConstants.DECISIONS_NAME_SUFFIX)
                .tag("identifier", "A")
                .counter().count()).isEqualTo(expectedCountStringA);

        assertThat(registry.find(dmnType + DecisionConstants.DECISIONS_NAME_SUFFIX)
                .tag("identifier", "B")
                .counter().count()).isEqualTo(expectedCountStringB);

        assertThat(registry.find(dmnType + DecisionConstants.DECISIONS_NAME_SUFFIX)
                .tag("identifier", "C")
                .counter().count()).isEqualTo(expectedCountStringC);
    }
}
