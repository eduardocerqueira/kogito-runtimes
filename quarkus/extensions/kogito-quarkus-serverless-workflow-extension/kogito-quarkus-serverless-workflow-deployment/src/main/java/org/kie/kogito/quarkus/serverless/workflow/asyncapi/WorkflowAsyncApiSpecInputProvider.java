package org.kie.kogito.quarkus.serverless.workflow.asyncapi;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.kie.kogito.quarkus.serverless.workflow.WorkflowCodeGenUtils;

import io.quarkiverse.asyncapi.generator.input.AsyncAPISpecInput;
import io.quarkiverse.asyncapi.generator.input.AsyncApiSpecInputProvider;
import io.quarkus.deployment.CodeGenContext;
import io.serverlessworkflow.api.functions.FunctionDefinition.Type;

public class WorkflowAsyncApiSpecInputProvider implements AsyncApiSpecInputProvider {

    private static final String KOGITO_PACKAGE_PREFIX = "org.kie.kogito.asyncAPI";

    @Override
    public AsyncAPISpecInput read(CodeGenContext context) {
        Path inputDir = context.inputDir();
        while (!Files.exists(inputDir)) {
            inputDir = inputDir.getParent();
        }
        try (Stream<Path> workflowFiles = Files.walk(inputDir)) {
            return new AsyncAPISpecInput(WorkflowCodeGenUtils.operationResources(workflowFiles, f -> f.getType() == Type.ASYNCAPI, context)
                    .collect(Collectors.toMap(resource -> resource.getOperationId().getFileName(), AsyncInputStreamSupplier::new, (key1, key2) -> key1)), KOGITO_PACKAGE_PREFIX);
        } catch (IOException io) {
            throw new IllegalStateException(io);
        }
    }
}
