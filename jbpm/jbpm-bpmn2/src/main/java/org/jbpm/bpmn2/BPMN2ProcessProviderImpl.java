package org.jbpm.bpmn2;

import org.drools.compiler.builder.impl.KnowledgeBuilderImpl;
import org.jbpm.assembler.BPMN2ProcessProvider;
import org.jbpm.bpmn2.xml.BPMNDISemanticModule;
import org.jbpm.bpmn2.xml.BPMNExtensionsSemanticModule;
import org.jbpm.bpmn2.xml.BPMNSemanticModule;
import org.jbpm.compiler.xml.compiler.SemanticKnowledgeBuilderConfigurationImpl;
import org.kie.internal.builder.KnowledgeBuilder;

public class BPMN2ProcessProviderImpl implements BPMN2ProcessProvider {

    public void configurePackageBuilder(KnowledgeBuilder knowledgeBuilder) {
        SemanticKnowledgeBuilderConfigurationImpl conf = (SemanticKnowledgeBuilderConfigurationImpl) ((KnowledgeBuilderImpl) knowledgeBuilder).getBuilderConfiguration();
        if (conf.getSemanticModules().getSemanticModule(BPMNSemanticModule.BPMN2_URI) == null) {
            conf.addSemanticModule(new BPMNSemanticModule());
            conf.addSemanticModule(new BPMNDISemanticModule());
            conf.addSemanticModule(new BPMNExtensionsSemanticModule());
        }
    }

}
