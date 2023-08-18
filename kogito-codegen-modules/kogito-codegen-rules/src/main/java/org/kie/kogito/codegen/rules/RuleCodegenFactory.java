package org.kie.kogito.codegen.rules;

import java.util.Collection;

import org.kie.kogito.codegen.api.Generator;
import org.kie.kogito.codegen.api.GeneratorFactory;
import org.kie.kogito.codegen.api.context.KogitoBuildContext;
import org.kie.kogito.codegen.api.io.CollectedResource;

public class RuleCodegenFactory implements GeneratorFactory {

    @Override
    public Generator create(KogitoBuildContext context, Collection<CollectedResource> collectedResources) {
        return RuleCodegen.ofCollectedResources(context, collectedResources);
    }
}
