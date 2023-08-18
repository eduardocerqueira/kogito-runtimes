package org.jbpm.test;

import org.drools.core.common.InternalKnowledgeRuntime;
import org.drools.core.process.WorkItemManagerFactory;
import org.jbpm.process.instance.InternalProcessRuntime;
import org.kie.kogito.process.workitems.InternalKogitoWorkItemManager;
import org.kie.kogito.process.workitems.impl.KogitoDefaultWorkItemManager;

public class TestWorkItemManagerFactory implements WorkItemManagerFactory {

    public InternalKogitoWorkItemManager createWorkItemManager(InternalKnowledgeRuntime kruntime) {
        return new KogitoDefaultWorkItemManager(InternalProcessRuntime.asKogitoProcessRuntime(kruntime));
    }

}
