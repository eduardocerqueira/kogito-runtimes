package org.kie.kogito.codegen.decision;

import java.util.Collection;

import org.kie.kogito.codegen.api.Generator;
import org.kie.kogito.codegen.api.GeneratorFactory;
import org.kie.kogito.codegen.api.context.KogitoBuildContext;
import org.kie.kogito.codegen.api.io.CollectedResource;

public class DecisionCodegenFactory implements GeneratorFactory {

    @Override
    public Generator create(KogitoBuildContext context, Collection<CollectedResource> collectedResources) {
        return DecisionCodegen.ofCollectedResources(context, collectedResources);
    }
}
