package org.kie.kogito.monitoring.core.common.integration;

import org.kie.kogito.monitoring.core.common.system.metrics.dmnhandlers.DecisionConstants;
import org.kie.kogito.monitoring.core.common.system.metrics.dmnhandlers.TypeHandler;

import io.micrometer.core.instrument.simple.SimpleMeterRegistry;

public abstract class AbstractQuantilesTest<T extends TypeHandler> {

    public static final String[] INTERNAL_PROMETHEUS_LABELS =
            new String[] {
                    DecisionConstants.DECISION_ENDPOINT_IDENTIFIER_LABELS[0],
                    DecisionConstants.DECISION_ENDPOINT_IDENTIFIER_LABELS[1],
                    "quantile"
            };
    protected static final String ENDPOINT_NAME = "hello";
    protected SimpleMeterRegistry registry;
    protected T handler;
    protected String dmnType;
}
