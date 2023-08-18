package org.kie.kogito.serverless.workflow.parser.handlers.validation;

import io.serverlessworkflow.api.Workflow;

import static org.kie.kogito.internal.utils.ConversionUtils.isEmpty;

public class WorkflowValidator {

    private WorkflowValidator() {
    }

    public static void validateStart(Workflow workflow) {
        if (workflow.getStart() == null || isEmpty(workflow.getStart().getStateName())) {
            throw new IllegalArgumentException("Workflow does not define a starting state");
        }
    }
}
