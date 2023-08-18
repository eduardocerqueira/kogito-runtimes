package org.kie.kogito.quarkus.serverless.workflow.deployment.livereload;

import java.nio.file.Path;

import org.eclipse.microprofile.config.Config;

import io.quarkus.bootstrap.prebuild.CodeGenException;
import io.quarkus.deployment.CodeGenContext;

/**
 * Service Provider Interface for {@link io.quarkus.deployment.CodeGenProvider} objects that need to be invoked on live reloads.
 */
interface LiveReloadableCodeGenProvider {

    boolean trigger(CodeGenContext context) throws CodeGenException;

    String providerId();

    String inputDirectory();

    boolean shouldRun(Path sourceDir, Config config);
}
