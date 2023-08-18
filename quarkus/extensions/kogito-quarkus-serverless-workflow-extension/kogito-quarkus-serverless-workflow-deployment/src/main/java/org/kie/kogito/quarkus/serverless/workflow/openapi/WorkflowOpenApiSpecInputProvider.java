package org.kie.kogito.quarkus.serverless.workflow.openapi;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.kie.kogito.quarkus.serverless.workflow.WorkflowCodeGenUtils;
import org.kie.kogito.quarkus.serverless.workflow.WorkflowOperationResource;
import org.kie.kogito.serverless.workflow.utils.OpenAPIWorkflowUtils;

import io.quarkiverse.openapi.generator.deployment.codegen.OpenApiSpecInputProvider;
import io.quarkiverse.openapi.generator.deployment.codegen.SpecInputModel;
import io.quarkus.deployment.CodeGenContext;

public class WorkflowOpenApiSpecInputProvider implements OpenApiSpecInputProvider {

    private static final String KOGITO_PACKAGE_PREFIX = "org.kie.kogito.openapi.";

    @Override
    public List<SpecInputModel> read(CodeGenContext context) {
        Path inputDir = context.inputDir();
        while (!Files.exists(inputDir)) {
            inputDir = inputDir.getParent();
        }
        try (Stream<Path> openApiFilesPaths = Files.walk(inputDir)) {
            return WorkflowCodeGenUtils.operationResources(openApiFilesPaths, OpenAPIWorkflowUtils::isOpenApiOperation, context).map(this::getSpecInput).collect(Collectors.toList());
        } catch (IOException io) {
            throw new IllegalStateException(io);
        }
    }

    private SpecInputModel getSpecInput(WorkflowOperationResource resource) {
        return new SpecInputModel(resource.getOperationId().getFileName(), resource.getContentLoader().getInputStream(), KOGITO_PACKAGE_PREFIX + resource.getOperationId().getPackageName());
    }
}
