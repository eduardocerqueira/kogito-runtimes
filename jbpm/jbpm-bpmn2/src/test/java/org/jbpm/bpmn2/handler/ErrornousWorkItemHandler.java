package org.jbpm.bpmn2.handler;

import org.kie.api.runtime.process.ProcessWorkItemHandlerException;
import org.kie.api.runtime.process.ProcessWorkItemHandlerException.HandlingStrategy;
import org.kie.api.runtime.process.WorkItem;
import org.kie.kogito.internal.process.runtime.KogitoWorkItem;
import org.kie.kogito.internal.process.runtime.KogitoWorkItemHandler;
import org.kie.kogito.internal.process.runtime.KogitoWorkItemManager;

public class ErrornousWorkItemHandler implements KogitoWorkItemHandler {

    private String processId;
    private HandlingStrategy strategy;

    private WorkItem workItem;

    public ErrornousWorkItemHandler(String processId, HandlingStrategy strategy) {
        super();
        this.processId = processId;
        this.strategy = strategy;
    }

    @Override
    public void executeWorkItem(KogitoWorkItem workItem, KogitoWorkItemManager manager) {
        this.workItem = workItem;
        if (processId != null && strategy != null) {

            if (workItem.getParameter("isCheckedCheckbox") != null) {
                manager.completeWorkItem(workItem.getStringId(), workItem.getParameters());
            } else {

                throw new ProcessWorkItemHandlerException(processId, strategy, new RuntimeException("On purpose"));
            }
        }

        manager.completeWorkItem(workItem.getStringId(), null);
    }

    @Override
    public void abortWorkItem(KogitoWorkItem workItem, KogitoWorkItemManager manager) {
        this.workItem = workItem;

    }

    public WorkItem getWorkItem() {
        return workItem;
    }
}
