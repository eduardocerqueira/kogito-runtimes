package org.jbpm.assembler;

import org.drools.compiler.builder.impl.KnowledgeBuilderImpl;
import org.kie.api.io.ResourceType;

public class BPMN2AssemblerService extends AbstractProcessAssembler {

    @Override
    public ResourceType getResourceType() {
        return ResourceType.BPMN2;
    }

    @Override
    protected void configurePackageBuilder(KnowledgeBuilderImpl kb) {
        BPMN2ProcessFactory.configurePackageBuilder(kb);
    }
}
