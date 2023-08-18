package org.jbpm.integrationtests.handler;

import org.kie.kogito.internal.process.runtime.KogitoWorkItem;
import org.kie.kogito.internal.process.runtime.KogitoWorkItemHandler;
import org.kie.kogito.internal.process.runtime.KogitoWorkItemManager;

public class TestWorkItemHandler implements KogitoWorkItemHandler {
    private KogitoWorkItem workItem;
    private boolean aborted = false;

    public void executeWorkItem(KogitoWorkItem workItem, KogitoWorkItemManager manager) {
        this.workItem = workItem;
    }

    public void abortWorkItem(KogitoWorkItem workItem, KogitoWorkItemManager manager) {
        aborted = true;
    }

    public KogitoWorkItem getWorkItem() {
        return workItem;
    }

    public boolean isAborted() {
        return aborted;
    }

    public void reset() {
        workItem = null;
    }
}
