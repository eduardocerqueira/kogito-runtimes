package org.kie.kogito.serverless.workflow.operationid;

import java.net.URI;
import java.nio.file.Path;
import java.util.Optional;
import java.util.Set;

import org.kie.kogito.serverless.workflow.parser.ParserContext;

import io.serverlessworkflow.api.Workflow;
import io.serverlessworkflow.api.functions.FunctionDefinition;

public class FileNameWorkflowOperationIdFactory extends AbstractWorkflowOperationIdFactory {

    public static final String FILE_PROP_VALUE = "FILE_NAME";

    @Override
    public String getFileName(Workflow workflow, FunctionDefinition function, Optional<ParserContext> context, URI uri, String operation, String service) {
        return Path.of(uri.getPath()).getFileName().toString();
    }

    @Override
    public Set<String> propertyValues() {
        return Set.of(FILE_PROP_VALUE);
    }
}
