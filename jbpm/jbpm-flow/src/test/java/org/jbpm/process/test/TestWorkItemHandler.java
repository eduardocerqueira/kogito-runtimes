package org.jbpm.process.test;

import java.util.Deque;
import java.util.LinkedList;

import org.kie.kogito.internal.process.runtime.KogitoWorkItem;
import org.kie.kogito.internal.process.runtime.KogitoWorkItemHandler;
import org.kie.kogito.internal.process.runtime.KogitoWorkItemManager;

/**
 * 
 */
public class TestWorkItemHandler implements KogitoWorkItemHandler {

    public Deque<KogitoWorkItem> workItems = new LinkedList<>();

    @Override
    public void executeWorkItem(KogitoWorkItem workItem, KogitoWorkItemManager manager) {
        this.workItems.add(workItem);
    }

    @Override
    public void abortWorkItem(KogitoWorkItem workItem, KogitoWorkItemManager manager) {
        this.workItems.add(workItem);
    }

    public Deque<KogitoWorkItem> getWorkItems() {
        return this.workItems;
    }
}
