package org.jbpm.assembler;

import org.kie.api.internal.utils.KieService;
import org.kie.internal.builder.KnowledgeBuilder;

public interface BPMN2ProcessProvider extends KieService {

    void configurePackageBuilder(KnowledgeBuilder packageBuilder);

}
