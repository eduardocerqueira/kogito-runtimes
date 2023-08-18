package org.kie.kogito.quarkus.serverless.workflow;

import java.util.Collection;

import org.jboss.jandex.IndexView;
import org.kie.kogito.codegen.api.context.KogitoBuildContext;

public interface WorkflowHandlerGenerator {

    Collection<WorkflowHandlerGeneratedFile> generateHandlerClasses(KogitoBuildContext context, IndexView index);
}
