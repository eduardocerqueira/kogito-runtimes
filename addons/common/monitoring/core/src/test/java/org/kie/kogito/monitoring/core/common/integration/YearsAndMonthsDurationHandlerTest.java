package org.kie.kogito.monitoring.core.common.integration;

import java.time.Period;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kie.kogito.KogitoGAV;
import org.kie.kogito.monitoring.core.common.system.metrics.dmnhandlers.DecisionConstants;
import org.kie.kogito.monitoring.core.common.system.metrics.dmnhandlers.YearsAndMonthsDurationHandler;

import io.micrometer.core.instrument.simple.SimpleMeterRegistry;

import static org.assertj.core.api.Assertions.assertThat;

public class YearsAndMonthsDurationHandlerTest extends AbstractQuantilesTest<YearsAndMonthsDurationHandler> {

    @BeforeEach
    public void setUp() {
        registry = new SimpleMeterRegistry();
        handler = new YearsAndMonthsDurationHandler("hello", KogitoGAV.EMPTY_GAV, registry);
    }

    @AfterEach
    public void destroy() {
        registry.clear();
    }

    @Test
    public void givenYearsAndMonthsMetricsWhenMetricsAreStoredThenTheQuantilesAreCorrect() {
        // Arrange
        Period period = Period.ofMonths(12);
        Double[] quantiles = new Double[] { 0.1, 0.25, 0.5, 0.75, 0.9, 0.99 };

        // Act
        handler.record("decision", ENDPOINT_NAME, period);

        // Assert
        assertThat(registry.find(ENDPOINT_NAME + DecisionConstants.DECISIONS_NAME_SUFFIX)
                .summary().mean()).isGreaterThanOrEqualTo(5);
    }
}
