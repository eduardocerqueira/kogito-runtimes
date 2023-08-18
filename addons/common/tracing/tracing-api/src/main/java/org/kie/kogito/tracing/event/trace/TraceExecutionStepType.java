package org.kie.kogito.tracing.event.trace;

/**
 * Enum meant to contain all the possible event step-types (decisions, processes, predictions...)
 */
public enum TraceExecutionStepType {
    DMN_BKM_EVALUATION,
    DMN_BKM_INVOCATION,
    DMN_CONTEXT_ENTRY,
    DMN_DECISION,
    DMN_DECISION_SERVICE,
    DMN_DECISION_TABLE
}
