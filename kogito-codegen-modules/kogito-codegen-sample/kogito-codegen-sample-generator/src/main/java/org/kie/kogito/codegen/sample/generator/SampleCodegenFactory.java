package org.kie.kogito.codegen.sample.generator;

import java.util.Collection;

import org.kie.kogito.codegen.api.Generator;
import org.kie.kogito.codegen.api.GeneratorFactory;
import org.kie.kogito.codegen.api.context.KogitoBuildContext;
import org.kie.kogito.codegen.api.io.CollectedResource;

public class SampleCodegenFactory implements GeneratorFactory {

    @Override
    public Generator create(KogitoBuildContext context, Collection<CollectedResource> collectedResources) {
        return SampleCodegen.ofCollectedResources(context, collectedResources);
    }
}
