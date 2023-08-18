package org.jbpm.util;

import org.jbpm.process.instance.KogitoProcessContextImpl;
import org.jbpm.process.instance.ProcessInstance;
import org.kie.kogito.internal.process.runtime.KogitoNodeInstance;
import org.kie.kogito.internal.process.runtime.KogitoWorkItem;

public class ContextFactory {

    private ContextFactory() {
    }

    public static KogitoProcessContextImpl fromItem(KogitoWorkItem workItem) {
        return fromNode(workItem.getNodeInstance());
    }

    public static KogitoProcessContextImpl fromNode(KogitoNodeInstance instance) {
        KogitoProcessContextImpl context = new KogitoProcessContextImpl(((ProcessInstance) instance.getProcessInstance()).getKnowledgeRuntime());
        context.setNodeInstance(instance);
        context.setProcessInstance(instance.getProcessInstance());
        return context;
    }

}
