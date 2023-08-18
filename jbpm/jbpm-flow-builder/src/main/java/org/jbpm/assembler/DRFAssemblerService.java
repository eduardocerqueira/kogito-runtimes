package org.jbpm.assembler;

import org.drools.compiler.builder.impl.KnowledgeBuilderImpl;
import org.kie.api.io.ResourceType;

public class DRFAssemblerService extends AbstractProcessAssembler {

    @Override
    public ResourceType getResourceType() {
        return ResourceType.DRF;
    }

    @Override
    protected void configurePackageBuilder(KnowledgeBuilderImpl kb) {
    }
}
