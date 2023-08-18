package org.kie.kogito.quarkus.serverless.workflow.deployment.livereload;

import io.quarkiverse.asyncapi.generator.input.AsyncApiGeneratorStreamCodeGen;

/**
 * Wrapper for {@link AsyncApiGeneratorStreamCodeGen} that implements the {@link LiveReloadableCodeGenProvider} Service Provider Interface.
 */
public class LiveReloadableAsyncApiGeneratorStreamCodeGen extends LiveReloadableCodeGenProviderBase<AsyncApiGeneratorStreamCodeGen> {

    public LiveReloadableAsyncApiGeneratorStreamCodeGen() {
        super(new AsyncApiGeneratorStreamCodeGen());
    }
}
