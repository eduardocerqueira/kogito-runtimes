package org.jbpm.process.instance.impl.demo;

import org.kie.kogito.internal.process.runtime.KogitoWorkItem;
import org.kie.kogito.internal.process.runtime.KogitoWorkItemHandler;
import org.kie.kogito.internal.process.runtime.KogitoWorkItemManager;

/**
 * 
 */
public class SystemOutWorkItemHandler implements KogitoWorkItemHandler {

    public void executeWorkItem(KogitoWorkItem workItem, KogitoWorkItemManager manager) {
        System.out.println("Executing work item " + workItem);
        manager.completeWorkItem(workItem.getStringId(), null);
    }

    public void abortWorkItem(KogitoWorkItem workItem, KogitoWorkItemManager manager) {
        System.out.println("Aborting work item " + workItem);
        manager.abortWorkItem(workItem.getStringId());
    }

}
