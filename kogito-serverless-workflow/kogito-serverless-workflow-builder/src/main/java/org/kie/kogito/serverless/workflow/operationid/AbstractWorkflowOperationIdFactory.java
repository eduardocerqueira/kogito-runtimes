package org.kie.kogito.serverless.workflow.operationid;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URI;
import java.util.Optional;

import org.kie.kogito.jackson.utils.ObjectMapperFactory;
import org.kie.kogito.serverless.workflow.extensions.URIDefinitions;
import org.kie.kogito.serverless.workflow.parser.ParserContext;
import org.kie.kogito.serverless.workflow.parser.handlers.ActionResource;
import org.kie.kogito.serverless.workflow.parser.handlers.ActionResourceFactory;
import org.kie.kogito.serverless.workflow.utils.ServerlessWorkflowUtils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.NullNode;

import io.serverlessworkflow.api.Workflow;
import io.serverlessworkflow.api.functions.FunctionDefinition;

import static java.lang.String.format;
import static org.kie.kogito.serverless.workflow.utils.ServerlessWorkflowUtils.onlyChars;
import static org.kie.kogito.serverless.workflow.utils.ServerlessWorkflowUtils.removeExt;

public abstract class AbstractWorkflowOperationIdFactory implements WorkflowOperationIdFactory {

    @Override
    public WorkflowOperationId from(Workflow workflow, FunctionDefinition function, Optional<ParserContext> context) {
        ActionResource actionResource = ActionResourceFactory.getActionResource(function);
        Optional<String> convertedUri = convertURI(workflow, context, actionResource.getUri());
        final URI uri;
        final String fileName;
        if (convertedUri.isPresent()) {
            uri = URI.create(convertedUri.get());
            fileName = actionResource.getUri();
        } else {
            uri = URI.create(actionResource.getUri());
            fileName = getFileName(workflow, function, context, uri, actionResource.getOperation(), actionResource.getService());
        }
        if (fileName == null || fileName.isBlank()) {
            throw new IllegalArgumentException(
                    format("Empty file name for function '%s', please review uri '%s' or consider using a different strategy defined in the kogito.sw.operationIdStrategy property",
                            function.getName(), uri));
        }
        String packageName = onlyChars(removeExt(fileName.toLowerCase()));
        if (packageName.isBlank()) {
            throw new IllegalArgumentException(
                    format("Empty package for file '%s'. A file name should contain at least one letter which is not part of the extension", fileName));
        }
        return new WorkflowOperationId(uri, actionResource.getOperation(), actionResource.getService(), fileName, packageName);
    }

    private Optional<String> convertURI(Workflow workflow, Optional<ParserContext> context, String uri) {
        return ServerlessWorkflowUtils.getExtension(workflow, URIDefinitions.class)
                .map(def -> getUriDefinitions(workflow, context, def)).filter(node -> node.has(uri)).map(node -> node.get(uri).asText());
    }

    private JsonNode getUriDefinitions(Workflow workflow, Optional<ParserContext> context, URIDefinitions uriDefinitions) {
        JsonNode definitions = uriDefinitions.getDefinitions();
        if (definitions == null || definitions.isNull()) {
            String uri = uriDefinitions.getURI();
            definitions = uri == null ? NullNode.instance : ServerlessWorkflowUtils.loadResourceFile(uri, Optional.of(workflow), context, null).map(bytes -> {
                try {
                    return ObjectMapperFactory.get().readTree(bytes);
                } catch (IOException e) {
                    throw new UncheckedIOException(e);
                }
            }).orElse(NullNode.instance);
            uriDefinitions.setDefinitions(definitions);
        }
        return definitions;
    }

    protected abstract String getFileName(Workflow workflow, FunctionDefinition function, Optional<ParserContext> context, URI uri, String operation, String service);
}
