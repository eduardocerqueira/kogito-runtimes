package org.kie.kogito.monitoring.core.common.integration;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kie.kogito.KogitoGAV;
import org.kie.kogito.monitoring.core.common.system.metrics.dmnhandlers.DaysAndTimeDurationHandler;
import org.kie.kogito.monitoring.core.common.system.metrics.dmnhandlers.DecisionConstants;

import io.micrometer.core.instrument.simple.SimpleMeterRegistry;

import static org.assertj.core.api.Assertions.assertThat;

public class DaysAndTimeDurationHandlerTest extends AbstractQuantilesTest<DaysAndTimeDurationHandler> {

    @BeforeEach
    public void setUp() {
        dmnType = "daysandtimeduration";
        registry = new SimpleMeterRegistry();
        handler = new DaysAndTimeDurationHandler(dmnType, KogitoGAV.EMPTY_GAV, registry);
    }

    @AfterEach
    public void destroy() {
        registry.clear();
    }

    @Test
    public void givenDurationMetricsWhenMetricsAreStoredThenTheQuantilesAreCorrect() {
        // Arrange
        Duration duration = Duration.ofMillis(10000);

        // Act
        handler.record("decision", ENDPOINT_NAME, duration);

        // Assert
        assertThat(registry.find(dmnType + DecisionConstants.DECISIONS_NAME_SUFFIX).summary().max()).isGreaterThanOrEqualTo(5);
        assertThat(registry.find(dmnType + DecisionConstants.DECISIONS_NAME_SUFFIX).summary().mean()).isGreaterThanOrEqualTo(2);
    }
}
