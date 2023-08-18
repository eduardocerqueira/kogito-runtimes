package org.kie.kogito.codegen.api;

import java.util.Collection;

import org.kie.kogito.codegen.api.context.KogitoBuildContext;
import org.kie.kogito.codegen.api.io.CollectedResource;

public interface GeneratorFactory {

    Generator create(KogitoBuildContext context, Collection<CollectedResource> collectedResources);
}
