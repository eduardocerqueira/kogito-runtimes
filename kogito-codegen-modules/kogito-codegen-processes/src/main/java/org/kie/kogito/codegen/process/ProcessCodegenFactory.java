package org.kie.kogito.codegen.process;

import java.util.Collection;

import org.kie.kogito.codegen.api.Generator;
import org.kie.kogito.codegen.api.GeneratorFactory;
import org.kie.kogito.codegen.api.context.KogitoBuildContext;
import org.kie.kogito.codegen.api.io.CollectedResource;

public class ProcessCodegenFactory implements GeneratorFactory {

    @Override
    public Generator create(KogitoBuildContext context, Collection<CollectedResource> collectedResources) {
        return ProcessCodegen.ofCollectedResources(context, collectedResources);
    }
}
