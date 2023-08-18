package org.kie.kogito.monitoring.core.common.system.metrics.dmnhandlers;

import java.time.Duration;

public class DecisionConstants {

    public static final String DECISIONS_NAME_SUFFIX = "_dmn_result";
    public static final String DECISIONS_HELP = "Decision output.";
    public static final double[] SUMMARY_PERCENTILES = new double[] { 0.1, 0.25, 0.5, 0.75, 0.9, 0.99 };
    public static final Duration SUMMARY_EXPIRATION = Duration.ofMinutes(3);

    /**
     * Array of label names for a prometheus object that needs an handler and an identifier.
     */
    public static final String[] DECISION_ENDPOINT_IDENTIFIER_LABELS = new String[] { "decision", "endpoint", "identifier", "artifactId", "version" };
    /**
     * Array of label names for a prometheus object that needs only the handler.
     */
    public static final String[] DECISION_ENDPOINT_LABELS = new String[] { "decision", "endpoint" };

    private DecisionConstants() {
    }
}
