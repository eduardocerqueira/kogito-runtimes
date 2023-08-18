package org.jbpm.bpmn2.handler;

import org.kie.api.runtime.process.WorkItemHandler;
import org.kie.kogito.internal.process.runtime.KogitoWorkItem;
import org.kie.kogito.internal.process.runtime.KogitoWorkItemHandler;
import org.kie.kogito.internal.process.runtime.KogitoWorkItemManager;

public abstract class AbstractExceptionHandlingTaskHandler implements KogitoWorkItemHandler {

    private KogitoWorkItemHandler originalTaskHandler;

    public AbstractExceptionHandlingTaskHandler(KogitoWorkItemHandler originalTaskHandler) {
        this.originalTaskHandler = originalTaskHandler;
    }

    public AbstractExceptionHandlingTaskHandler(Class<? extends KogitoWorkItemHandler> originalTaskHandlerClass) {
        Class<?>[] clsParams = {};
        Object[] objParams = {};
        try {
            this.originalTaskHandler = originalTaskHandlerClass.getConstructor(clsParams).newInstance(objParams);
        } catch (Exception e) {
            throw new UnsupportedOperationException("The " + WorkItemHandler.class.getSimpleName() + " parameter must have a public no-argument constructor.");
        }
    }

    @Override
    public void executeWorkItem(KogitoWorkItem workItem, KogitoWorkItemManager manager) {
        try {
            originalTaskHandler.executeWorkItem(workItem, manager);
        } catch (Exception cause) {
            handleExecuteException(cause, workItem, manager);
        }
    }

    @Override
    public void abortWorkItem(KogitoWorkItem workItem, KogitoWorkItemManager manager) {
        try {
            originalTaskHandler.abortWorkItem(workItem, manager);
        } catch (RuntimeException re) {
            handleAbortException(re, workItem, manager);
        }
    }

    public KogitoWorkItemHandler getOriginalTaskHandler() {
        return originalTaskHandler;
    }

    public abstract void handleExecuteException(Throwable cause, KogitoWorkItem workItem, KogitoWorkItemManager manager);

    public abstract void handleAbortException(Throwable cause, KogitoWorkItem workItem, KogitoWorkItemManager manager);

}
