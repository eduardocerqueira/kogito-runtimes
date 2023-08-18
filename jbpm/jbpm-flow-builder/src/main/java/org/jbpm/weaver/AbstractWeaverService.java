package org.jbpm.weaver;

import org.drools.base.definitions.InternalKnowledgePackage;
import org.drools.base.definitions.ProcessPackage;
import org.kie.api.definition.KiePackage;
import org.kie.api.internal.weaver.KieWeaverService;

public abstract class AbstractWeaverService implements KieWeaverService<ProcessPackage> {

    @Override
    public void merge(KiePackage kiePkg, ProcessPackage processPkg) {
        ProcessPackage existing =
                ProcessPackage.getOrCreate(((InternalKnowledgePackage) kiePkg).getResourceTypePackages());
        existing.getRuleFlows().putAll(processPkg.getRuleFlows());
    }

    @Override
    public void weave(KiePackage kiePackage, ProcessPackage processPackage) {

    }

}
