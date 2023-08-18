package org.kie.kogito.serverless.workflow.operationid;

import java.net.URI;
import java.util.Optional;
import java.util.Set;

import org.kie.kogito.serverless.workflow.parser.ParserContext;
import org.kie.kogito.serverless.workflow.utils.OpenAPIFactory;

import io.serverlessworkflow.api.Workflow;
import io.serverlessworkflow.api.functions.FunctionDefinition;

public class SpecWorkflowOperationIdFactory extends AbstractWorkflowOperationIdFactory {
    public static final String SPEC_PROP_VALUE = "SPEC_TITLE";

    @Override
    public String getFileName(Workflow workflow, FunctionDefinition function, Optional<ParserContext> context, URI uri, String operation, String service) {
        return OpenAPIFactory.getOpenAPI(uri, workflow, function, context.map(c -> c.getContext().getClassLoader()).orElse(Thread.currentThread().getContextClassLoader())).getInfo()
                .getTitle();
    }

    @Override
    public Set<String> propertyValues() {
        return Set.of(SPEC_PROP_VALUE);
    }
}
