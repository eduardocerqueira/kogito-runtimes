package org.kie.kogito.monitoring.core.common.integration;

import java.math.BigDecimal;
import java.math.MathContext;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kie.kogito.KogitoGAV;
import org.kie.kogito.monitoring.core.common.system.metrics.dmnhandlers.BigDecimalHandler;
import org.kie.kogito.monitoring.core.common.system.metrics.dmnhandlers.DecisionConstants;

import io.micrometer.core.instrument.simple.SimpleMeterRegistry;

import ch.obermuhlner.math.big.stream.BigDecimalStream;

import static org.assertj.core.api.Assertions.assertThat;

public class BigDecimalHandlerTest extends AbstractQuantilesTest<BigDecimalHandler> {
    @BeforeEach
    public void setUp() {
        dmnType = "bigdecimal";
        registry = new SimpleMeterRegistry();
        handler = new BigDecimalHandler(dmnType, KogitoGAV.EMPTY_GAV, registry);
    }

    @AfterEach
    public void destroy() {
        registry.clear();
    }

    @Test
    public void givenSomeSamplesWhenQuantilesAreCalculatedThenTheQuantilesAreCorrect() {
        // Act
        BigDecimalStream.range(BigDecimal.valueOf(1), BigDecimal.valueOf(10001), BigDecimal.ONE, MathContext.DECIMAL64).forEach(x -> handler.record("decision", ENDPOINT_NAME, x));
        assertThat(registry.find(dmnType + DecisionConstants.DECISIONS_NAME_SUFFIX).summary().max()).isGreaterThanOrEqualTo(10000);
        assertThat(registry.find(dmnType + DecisionConstants.DECISIONS_NAME_SUFFIX).summary().mean()).isPositive();
    }
}
