package org.kie.kogito.quarkus.serverless.workflow.deployment.livereload;

import io.quarkiverse.openapi.generator.deployment.codegen.OpenApiGeneratorStreamCodeGen;

/**
 * Wrapper for {@link OpenApiGeneratorStreamCodeGen} that implements the {@link LiveReloadableCodeGenProvider} Service Provider Interface.
 */
public class LiveReloadableOpenApiGeneratorStreamCodeGen extends LiveReloadableCodeGenProviderBase<OpenApiGeneratorStreamCodeGen> {

    public LiveReloadableOpenApiGeneratorStreamCodeGen() {
        super(new OpenApiGeneratorStreamCodeGen());
    }
}
