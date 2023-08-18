package org.kie.kogito.quarkus.serverless.workflow.deployment.livereload;

import java.nio.file.Path;

import org.eclipse.microprofile.config.Config;

import io.quarkus.bootstrap.prebuild.CodeGenException;
import io.quarkus.deployment.CodeGenContext;
import io.quarkus.deployment.CodeGenProvider;

abstract class LiveReloadableCodeGenProviderBase<T extends CodeGenProvider> implements LiveReloadableCodeGenProvider {

    private final T delegate;

    LiveReloadableCodeGenProviderBase(T delegate) {
        this.delegate = delegate;
    }

    @Override
    public final boolean trigger(CodeGenContext context) throws CodeGenException {
        return delegate.trigger(context);
    }

    @Override
    public String inputDirectory() {
        return delegate.inputDirectory();
    }

    @Override
    public String providerId() {
        return delegate.providerId();
    }

    @Override
    public boolean shouldRun(Path sourceDir, Config config) {
        return delegate.shouldRun(sourceDir, config);
    }
}
