package org.kie.kogito.tracing.decision;

import org.junit.jupiter.api.Test;
import org.kie.kogito.Application;
import org.kie.kogito.config.ConfigBean;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

class SpringBootDecisionTracingConfigurationTest {

    @Test
    void testCollector() {
        SpringBootTraceEventEmitter mockedEmitter = mock(SpringBootTraceEventEmitter.class);
        ConfigBean mockedConfigBean = mock(ConfigBean.class);
        Application mockedApplication = mock(Application.class);

        SpringBootDecisionTracingConfiguration config = new SpringBootDecisionTracingConfiguration("localhost:9092", "kogito-tracing-decision", 1, (short) 1);

        SpringBootDecisionTracingCollector asyncCollector = config.collector(mockedEmitter, mockedConfigBean, mockedApplication, true);
        assertTrue(asyncCollector instanceof SpringBootDecisionTracingCollectorAsync);

        SpringBootDecisionTracingCollector syncCollector = config.collector(mockedEmitter, mockedConfigBean, mockedApplication, false);
        assertFalse(syncCollector instanceof SpringBootDecisionTracingCollectorAsync);
    }
}
