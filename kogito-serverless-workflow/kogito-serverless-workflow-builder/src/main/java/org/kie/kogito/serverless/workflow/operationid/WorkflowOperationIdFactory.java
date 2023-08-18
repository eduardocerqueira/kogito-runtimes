package org.kie.kogito.serverless.workflow.operationid;

import java.util.Optional;
import java.util.Set;

import org.kie.kogito.serverless.workflow.parser.ParserContext;

import io.serverlessworkflow.api.Workflow;
import io.serverlessworkflow.api.functions.FunctionDefinition;

public interface WorkflowOperationIdFactory {
    WorkflowOperationId from(Workflow workflow, FunctionDefinition function, Optional<ParserContext> parserContext);

    Set<String> propertyValues();
}
