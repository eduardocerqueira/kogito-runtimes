package org.kie.kogito.quarkus.serverless.workflow.deployment;

import java.util.Set;

import org.kie.kogito.internal.SupportedExtensions;
import org.kie.kogito.quarkus.common.deployment.KogitoCompilationProvider;

public class ServerlessWorkflowCompilationProvider extends KogitoCompilationProvider {

    @Override
    public Set<String> handledExtensions() {
        return SupportedExtensions.getSWFExtensions();
    }
}
