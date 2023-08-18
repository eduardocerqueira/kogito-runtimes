package org.kie.kogito.serverless.workflow.operationid;

import java.net.URI;
import java.util.Optional;
import java.util.Set;

import org.kie.kogito.serverless.workflow.parser.ParserContext;

import io.serverlessworkflow.api.Workflow;
import io.serverlessworkflow.api.functions.FunctionDefinition;

public class FunctionWorkflowOperationIdFactory extends AbstractWorkflowOperationIdFactory {
    public static final String FUNCTION_PROP_VALUE = "FUNCTION_NAME";

    @Override
    public String getFileName(Workflow workflow, FunctionDefinition function, Optional<ParserContext> context, URI uri, String operation, String service) {
        return workflow.getId() + '_' + function.getName();
    }

    @Override
    public Set<String> propertyValues() {
        return Set.of(FUNCTION_PROP_VALUE);
    }
}
