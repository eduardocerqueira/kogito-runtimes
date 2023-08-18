package org.kie.kogito.serverless.workflow.operationid;

import java.net.URI;
import java.nio.file.Path;
import java.util.Optional;
import java.util.Set;

import org.kie.kogito.serverless.workflow.parser.ParserContext;

import io.serverlessworkflow.api.Workflow;
import io.serverlessworkflow.api.functions.FunctionDefinition;

public class URIWorkflowOperationIdFactory extends AbstractWorkflowOperationIdFactory {

    public static final String URI_PROP_VALUE = "FULL_URI";

    @Override
    public String getFileName(Workflow workflow, FunctionDefinition function, Optional<ParserContext> context, URI uri, String operation, String service) {
        return Path.of(uri.getPath()).toString();
    }

    @Override
    public Set<String> propertyValues() {
        return Set.of(URI_PROP_VALUE);
    }
}
